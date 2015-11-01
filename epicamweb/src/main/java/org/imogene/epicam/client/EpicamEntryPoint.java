package org.imogene.epicam.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.imogene.admin.client.AdminRenderer;
import org.imogene.admin.client.event.create.CreateCardEntityEvent;
import org.imogene.admin.client.event.create.CreateFieldGroupEvent;
import org.imogene.admin.client.event.create.CreateNotificationEvent;
import org.imogene.admin.client.event.create.CreateProfileEvent;
import org.imogene.admin.client.event.list.ListCardEntityEvent;
import org.imogene.admin.client.event.list.ListFieldGroupEvent;
import org.imogene.admin.client.event.list.ListNotificationEvent;
import org.imogene.admin.client.event.list.ListProfileEvent;
import org.imogene.admin.client.event.view.ViewCardEntityEvent;
import org.imogene.admin.client.event.view.ViewFieldGroupEvent;
import org.imogene.admin.client.event.view.ViewNotificationEvent;
import org.imogene.admin.client.event.view.ViewProfileEvent;
import org.imogene.admin.client.ui.table.panel.CardEntityListPanel;
import org.imogene.admin.client.ui.table.panel.DynamicFieldTemplateListPanel;
import org.imogene.admin.client.ui.table.panel.FieldGroupListPanel;
import org.imogene.admin.client.ui.table.panel.NotificationListPanel;
import org.imogene.admin.client.ui.table.panel.ProfileListPanel;
import org.imogene.admin.client.ui.workflow.panel.CardEntityFormPanel;
import org.imogene.admin.client.ui.workflow.panel.FieldGroupFormPanel;
import org.imogene.admin.client.ui.workflow.panel.NotificationFormPanel;
import org.imogene.admin.client.ui.workflow.panel.ProfileFormPanel;
import org.imogene.web.client.dynamicfields.ui.renderer.DynFieldsRenderer;
import org.imogene.web.client.dynamicfields.ui.workflow.panel.DynamicFieldTemplateFormPanel;
import org.imogene.web.client.event.CreateDynamicFieldTemplateEvent;
import org.imogene.web.client.event.GoHomeEvent;
import org.imogene.web.client.event.HistoryBackEvent;
import org.imogene.web.client.event.ListDynamicFieldTemplateEvent;
import org.imogene.web.client.event.LogoutEvent;
import org.imogene.web.client.event.ViewDynamicFieldTemplateEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.panel.GlassPanel;
import org.imogene.web.client.ui.panel.MainContentPanel;
import org.imogene.web.client.util.ProfileUtil;
import org.imogene.web.client.util.LocalSession;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.web.client.util.TokenHelper.EntityToken;
import org.imogene.web.shared.proxy.ImogActorProxy;
import org.imogene.web.shared.proxy.SessionInfoProxy;
import org.imogene.web.shared.request.SessionRequest;
import org.imogene.epicam.client.event.create.*;
import org.imogene.epicam.client.event.list.*;
import org.imogene.epicam.client.event.view.*;
import org.imogene.epicam.client.ui.workflow.panel.*;
import org.imogene.epicam.client.ui.table.panel.*;
import org.imogene.epicam.client.ui.panel.TopBannerPanel;
import org.imogene.epicam.client.ui.panel.HomePanel;
import org.imogene.epicam.shared.EpicamRequestFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.imogene.epicam.client.ui.maps.EpicamMap;

/**
 * Entry point of the application on the client side
 * @author Medes-IMPS
 */
public class EpicamEntryPoint implements EntryPoint, ValueChangeHandler<String> {

	interface Binder extends UiBinder<Widget, EpicamEntryPoint> {
	}

	private static final Logger log = Logger.getLogger(EpicamEntryPoint.class
			.getName());

	private static List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private static String APPLICATION_VERSION = "0.0.1";

	private static final int LOGOUT_TIMEOUT = 333;

	public static GlassPanel GP = new GlassPanel();

	/* session cookie with a validation period of 2 days */
	private static String COOKIE_NAME = "imogene-epicam";
	private final long COOKIE_DURATION = 1000 * 60 * 60 * 24 * 2;

	protected final EpicamRequestFactory requestFactory = GWT
			.create(EpicamRequestFactory.class);
	protected EventBus eventBus = new SimpleEventBus();

	/* timer to check session validity */
	private Timer sessionTimer;

	private int historyCount = 0;

	@UiField
	VerticalPanel mainPanel;
	@UiField
	MainContentPanel content;
	@UiField(provided = true)
	TopBannerPanel topPanel;

	/**
	 * This is the entry point method
	 */
	public void onModuleLoad() {

		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}
		});

		requestFactory.initialize(eventBus);

		// get session info and set info in local session
		SessionRequest request = requestFactory.sessionInfoRequest();
		Request<SessionInfoProxy> sessionInfoRequest = request.getSessionInfo();
		sessionInfoRequest.with("actor.profiles");
		sessionInfoRequest.with("actor.profiles.entityProfiles");
		sessionInfoRequest.with("actor.profiles.entityProfiles.entity");
		sessionInfoRequest.with("actor.profiles.fieldGroupProfiles");
		sessionInfoRequest.with("actor.profiles.fieldGroupProfiles.fieldGroup");
		sessionInfoRequest.to(new Receiver<SessionInfoProxy>() {

			@Override
			public void onFailure(ServerFailure error) {
				Window.alert(BaseNLS.constants().connexion_error());
			}

			@Override
			public void onSuccess(SessionInfoProxy sessionInfo) {
				if (sessionInfo != null && sessionInfo.getActor() != null) {
					ImogActorProxy actor = sessionInfo.getActor();
					LocalSession.get().setCurrentUser(actor);
					LocalSession.get().setVersionNumber(APPLICATION_VERSION);
					displayApplication(actor);
					initializeCookie(sessionInfo.getSessionId());
				} else
					logout();
			}
		}).fire();
	}

	/**
	 * Initialize the cookie
	 */
	private void initializeCookie(String sessionId) {
		String cookieValue = Cookies.getCookie(COOKIE_NAME);
		if (cookieValue == null || !sessionId.equals(cookieValue)) {
			Cookies.setCookie(COOKIE_NAME, sessionId,
					new Date(System.currentTimeMillis() + COOKIE_DURATION));
		}
		launchSessionTimer();
	}

	/**
	 * Create the user interface
	 */
	protected void displayApplication(ImogActorProxy actor) {

		registrations.add(History.addValueChangeHandler(this));
		//History.fireCurrentHistoryState();

		RootPanel.get("loadingWrapper").setVisible(false);

		// construct layout
		topPanel = new TopBannerPanel(eventBus, actor);

		// get parent panel
		RootPanel root = RootPanel.get("root");
		root.add(GWT.<Binder> create(Binder.class).createAndBindUi(this));

		// add first level panel		
		setContentPanel(getHomePanel());
		topPanel.hideTitle();

		// add handlers
		setHandlers();

		// throw the current history token
		History.fireCurrentHistoryState();
	}

	/**
	 * Gets the module Home panel
	 * @return
	 */
	protected Composite getHomePanel() {
		return new HomePanel(eventBus);
	}

	/**
	 * Sets the module content panel
	 * @param c the panel to be displayed in the module content panel
	 */
	public void setContentPanel(Composite c) {
		content.setContent(c);
	}

	/**
	 * Logouts the user from the application
	 */
	private void logout() {

		/* stop timer */
		if (sessionTimer != null) {
			sessionTimer.cancel();
			sessionTimer = null;
		}

		Cookies.removeCookie(COOKIE_NAME);

		/* clean local data */
		LocalSession.get().setCurrentUser(null);

		// WARNING -> Workaround to the re-connection problem
		Timer logoutTimer = new Timer() {
			public void run() {
				/* disconnect from the server */
				SessionRequest request = requestFactory.sessionInfoRequest();
				Request<Void> disconnectRequest = request.disconnect();
				disconnectRequest.to(new Receiver<Void>() {

					@Override
					public void onFailure(ServerFailure error) {
						clearModule();
					}

					@Override
					public void onSuccess(Void result) {
						clearModule();
					}
				}).fire();
			}
		};
		logoutTimer.schedule(LOGOUT_TIMEOUT);
	}

	/**
	 * Launch a timer that checks every 5s that the current user 
	 * session is valid. If it isn't (because the session expired),
	 * then it displays the login form.
	 */
	private void launchSessionTimer() {

		/* set timer to check session validity and disconnect if not valid */
		if (sessionTimer != null) {
			//Window.alert("Session timer is not null");
		} else {
			sessionTimer = new Timer() {
				public void run() {

					/* check session */
					SessionRequest request = requestFactory
							.sessionInfoRequest();
					Request<Boolean> validateSessionRequest = request
							.validateSession(Cookies.getCookie(COOKIE_NAME));
					validateSessionRequest.to(new Receiver<Boolean>() {

						@Override
						public void onFailure(ServerFailure error) {
							//logout();
						}

						@Override
						public void onSuccess(Boolean result) {
							if (result == null || !result) {
								logout();
							}
						}
					}).fire();
				}
			};
		}
		sessionTimer.cancel();
		sessionTimer.scheduleRepeating(5000);
	}

	/**
	 * Clears the module from its panels
	 */
	private void clearModule() {
		RootPanel.get().clear();
		topPanel = null;
		content = null;
		mainPanel = null;
		removeHandlers();
		String href = GWT.getHostPageBaseURL() + "jsp/Login.jsp";
		Window.Location.assign(href);
	}

	/**
	 * 
	 * @param panel
	 */
	private void displayWrapperPanel(Widget panel) {
		topPanel.showTitle();
		topPanel.showLocaleList(false);
		content.setContent(panel);
	}

	/**
	 * Remove the application level handlers
	 */
	protected static void removeHandlers() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
	}

	/**
	 *
	 */
	protected <T extends EventHandler> void registerHandler(Type<T> type,
			T eventHandler) {
		registrations.add(eventBus.addHandler(type, eventHandler));
	}

	/**
	 * Set the application level handlers
	 */
	private void setHandlers() {

		/* Dispaley map	 */
		registrations.add(eventBus.addHandler(ViewEpicamMapEvent.TYPE,
				new ViewEpicamMapEvent.Handler() {
					public void viewMap() {
						EpicamMap map = new EpicamMap(requestFactory);
						map.setAutoHideEnabled(true);
						map.setPopupPosition(80, 20);
						map.show();
					}
				}));

		/* Handler to go to the application home page */
		registrations.add(eventBus.addHandler(GoHomeEvent.TYPE,
				new GoHomeEvent.Handler() {
					public void goHome() {
						content.setContent(getHomePanel());
						topPanel.hideTitle();
						topPanel.showLocaleList(true);
					}
				}));

		/* Handler to logout the application */
		registrations.add(eventBus.addHandler(LogoutEvent.TYPE,
				new LogoutEvent.Handler() {
					public void onLogout() {
						logout();
					}
				}));

		registrations.add(eventBus.addHandler(HistoryBackEvent.TYPE,
				new HistoryBackEvent.Handler() {

					@Override
					public void onHistoryBackRequest() {
						if (historyCount > 1) {
							History.back();
							historyCount--;
						} else {
							History.newItem("");
						}
					}
				}));

		/** 
		 * Handlers for entity Patient 
		 * */

		/* Create Patient Handler */
		registrations.add(eventBus.addHandler(CreatePatientEvent.TYPE,
				new CreatePatientEvent.Handler() {
					public void createNewPatient(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreatePatient()
								&& AccessManager.canEditPatient()) {
							PatientFormPanel patientForm = new PatientFormPanel(
									requestFactory, null);
							patientForm.setCloseEvent(closeEvent);
							displayWrapperPanel(patientForm);
						}
					}
				}));

		/* View Patient Handler */
		registrations.add(eventBus.addHandler(ViewPatientEvent.TYPE,
				new ViewPatientEvent.Handler() {
					public void viewPatient(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadPatient()) {
							PatientFormPanel patientForm = new PatientFormPanel(
									requestFactory, entityId);
							patientForm.setCloseEvent(closeEvent);
							displayWrapperPanel(patientForm);
						}
					}
				}));

		/* List Patient Handler	 */
		registrations.add(eventBus.addHandler(ListPatientEvent.TYPE,
				new ListPatientEvent.Handler() {
					public void listPatient() {
						if (AccessManager.canDirectAccessPatient()
								&& AccessManager.canReadPatient())
							displayWrapperPanel(new PatientListPanel(
									requestFactory));
					}
					public void listPatient(String searchText) {
						if (AccessManager.canDirectAccessPatient()
								&& AccessManager.canReadPatient())
							displayWrapperPanel(new PatientListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity CasIndex 
		 * */

		/* Create CasIndex Handler */
		registrations.add(eventBus.addHandler(CreateCasIndexEvent.TYPE,
				new CreateCasIndexEvent.Handler() {
					public void createNewCasIndex(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateCasIndex()
								&& AccessManager.canEditCasIndex()) {
							CasIndexFormPanel casindexForm = new CasIndexFormPanel(
									requestFactory, null);
							casindexForm.setCloseEvent(closeEvent);
							displayWrapperPanel(casindexForm);
						}
					}
				}));

		/* View CasIndex Handler */
		registrations.add(eventBus.addHandler(ViewCasIndexEvent.TYPE,
				new ViewCasIndexEvent.Handler() {
					public void viewCasIndex(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadCasIndex()) {
							CasIndexFormPanel casindexForm = new CasIndexFormPanel(
									requestFactory, entityId);
							casindexForm.setCloseEvent(closeEvent);
							displayWrapperPanel(casindexForm);
						}
					}
				}));

		/* List CasIndex Handler	 */
		registrations.add(eventBus.addHandler(ListCasIndexEvent.TYPE,
				new ListCasIndexEvent.Handler() {
					public void listCasIndex() {
						if (AccessManager.canDirectAccessCasIndex()
								&& AccessManager.canReadCasIndex())
							displayWrapperPanel(new CasIndexListPanel(
									requestFactory));
					}
					public void listCasIndex(String searchText) {
						if (AccessManager.canDirectAccessCasIndex()
								&& AccessManager.canReadCasIndex())
							displayWrapperPanel(new CasIndexListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity CasTuberculose 
		 * */

		/* Create CasTuberculose Handler */
		registrations.add(eventBus.addHandler(CreateCasTuberculoseEvent.TYPE,
				new CreateCasTuberculoseEvent.Handler() {
					public void createNewCasTuberculose(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateCasTuberculose()
								&& AccessManager.canEditCasTuberculose()) {
							CasTuberculoseFormPanel castuberculoseForm = new CasTuberculoseFormPanel(
									requestFactory, null);
							castuberculoseForm.setCloseEvent(closeEvent);
							displayWrapperPanel(castuberculoseForm);
						}
					}
				}));

		/* View CasTuberculose Handler */
		registrations.add(eventBus.addHandler(ViewCasTuberculoseEvent.TYPE,
				new ViewCasTuberculoseEvent.Handler() {
					public void viewCasTuberculose(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadCasTuberculose()) {
							CasTuberculoseFormPanel castuberculoseForm = new CasTuberculoseFormPanel(
									requestFactory, entityId);
							castuberculoseForm.setCloseEvent(closeEvent);
							displayWrapperPanel(castuberculoseForm);
						}
					}
				}));

		/* List CasTuberculose Handler	 */
		registrations.add(eventBus.addHandler(ListCasTuberculoseEvent.TYPE,
				new ListCasTuberculoseEvent.Handler() {
					public void listCasTuberculose() {
						if (AccessManager.canDirectAccessCasTuberculose()
								&& AccessManager.canReadCasTuberculose())
							displayWrapperPanel(new CasTuberculoseListPanel(
									requestFactory));
					}
					public void listCasTuberculose(String searchText) {
						if (AccessManager.canDirectAccessCasTuberculose()
								&& AccessManager.canReadCasTuberculose())
							displayWrapperPanel(new CasTuberculoseListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity ExamenSerologie 
		 * */

		/* Create ExamenSerologie Handler */
		registrations.add(eventBus.addHandler(CreateExamenSerologieEvent.TYPE,
				new CreateExamenSerologieEvent.Handler() {
					public void createNewExamenSerologie(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateExamenSerologie()
								&& AccessManager.canEditExamenSerologie()) {
							ExamenSerologieFormPanel examenserologieForm = new ExamenSerologieFormPanel(
									requestFactory, null);
							examenserologieForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenserologieForm);
						}
					}
				}));

		/* View ExamenSerologie Handler */
		registrations.add(eventBus.addHandler(ViewExamenSerologieEvent.TYPE,
				new ViewExamenSerologieEvent.Handler() {
					public void viewExamenSerologie(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadExamenSerologie()) {
							ExamenSerologieFormPanel examenserologieForm = new ExamenSerologieFormPanel(
									requestFactory, entityId);
							examenserologieForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenserologieForm);
						}
					}
				}));

		/* List ExamenSerologie Handler	 */
		registrations.add(eventBus.addHandler(ListExamenSerologieEvent.TYPE,
				new ListExamenSerologieEvent.Handler() {
					public void listExamenSerologie() {
						if (AccessManager.canDirectAccessExamenSerologie()
								&& AccessManager.canReadExamenSerologie())
							displayWrapperPanel(new ExamenSerologieListPanel(
									requestFactory));
					}
					public void listExamenSerologie(String searchText) {
						if (AccessManager.canDirectAccessExamenSerologie()
								&& AccessManager.canReadExamenSerologie())
							displayWrapperPanel(new ExamenSerologieListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity ExamenBiologique 
		 * */

		/* Create ExamenBiologique Handler */
		registrations.add(eventBus.addHandler(CreateExamenBiologiqueEvent.TYPE,
				new CreateExamenBiologiqueEvent.Handler() {
					public void createNewExamenBiologique(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateExamenBiologique()
								&& AccessManager.canEditExamenBiologique()) {
							ExamenBiologiqueFormPanel examenbiologiqueForm = new ExamenBiologiqueFormPanel(
									requestFactory, null);
							examenbiologiqueForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenbiologiqueForm);
						}
					}
				}));

		/* View ExamenBiologique Handler */
		registrations.add(eventBus.addHandler(ViewExamenBiologiqueEvent.TYPE,
				new ViewExamenBiologiqueEvent.Handler() {
					public void viewExamenBiologique(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadExamenBiologique()) {
							ExamenBiologiqueFormPanel examenbiologiqueForm = new ExamenBiologiqueFormPanel(
									requestFactory, entityId);
							examenbiologiqueForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenbiologiqueForm);
						}
					}
				}));

		/* List ExamenBiologique Handler	 */
		registrations.add(eventBus.addHandler(ListExamenBiologiqueEvent.TYPE,
				new ListExamenBiologiqueEvent.Handler() {
					public void listExamenBiologique() {
						if (AccessManager.canDirectAccessExamenBiologique()
								&& AccessManager.canReadExamenBiologique())
							displayWrapperPanel(new ExamenBiologiqueListPanel(
									requestFactory));
					}
					public void listExamenBiologique(String searchText) {
						if (AccessManager.canDirectAccessExamenBiologique()
								&& AccessManager.canReadExamenBiologique())
							displayWrapperPanel(new ExamenBiologiqueListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity ExamenMicroscopie 
		 * */

		/* Create ExamenMicroscopie Handler */
		registrations.add(eventBus.addHandler(
				CreateExamenMicroscopieEvent.TYPE,
				new CreateExamenMicroscopieEvent.Handler() {
					public void createNewExamenMicroscopie(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateExamenMicroscopie()
								&& AccessManager.canEditExamenMicroscopie()) {
							ExamenMicroscopieFormPanel examenmicroscopieForm = new ExamenMicroscopieFormPanel(
									requestFactory, null);
							examenmicroscopieForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenmicroscopieForm);
						}
					}
				}));

		/* View ExamenMicroscopie Handler */
		registrations.add(eventBus.addHandler(ViewExamenMicroscopieEvent.TYPE,
				new ViewExamenMicroscopieEvent.Handler() {
					public void viewExamenMicroscopie(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadExamenMicroscopie()) {
							ExamenMicroscopieFormPanel examenmicroscopieForm = new ExamenMicroscopieFormPanel(
									requestFactory, entityId);
							examenmicroscopieForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenmicroscopieForm);
						}
					}
				}));

		/* List ExamenMicroscopie Handler	 */
		registrations.add(eventBus.addHandler(ListExamenMicroscopieEvent.TYPE,
				new ListExamenMicroscopieEvent.Handler() {
					public void listExamenMicroscopie() {
						if (AccessManager.canDirectAccessExamenMicroscopie()
								&& AccessManager.canReadExamenMicroscopie())
							displayWrapperPanel(new ExamenMicroscopieListPanel(
									requestFactory));
					}
					public void listExamenMicroscopie(String searchText) {
						if (AccessManager.canDirectAccessExamenMicroscopie()
								&& AccessManager.canReadExamenMicroscopie())
							displayWrapperPanel(new ExamenMicroscopieListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity ExamenATB 
		 * */

		/* Create ExamenATB Handler */
		registrations.add(eventBus.addHandler(CreateExamenATBEvent.TYPE,
				new CreateExamenATBEvent.Handler() {
					public void createNewExamenATB(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateExamenATB()
								&& AccessManager.canEditExamenATB()) {
							ExamenATBFormPanel examenatbForm = new ExamenATBFormPanel(
									requestFactory, null);
							examenatbForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenatbForm);
						}
					}
				}));

		/* View ExamenATB Handler */
		registrations.add(eventBus.addHandler(ViewExamenATBEvent.TYPE,
				new ViewExamenATBEvent.Handler() {
					public void viewExamenATB(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadExamenATB()) {
							ExamenATBFormPanel examenatbForm = new ExamenATBFormPanel(
									requestFactory, entityId);
							examenatbForm.setCloseEvent(closeEvent);
							displayWrapperPanel(examenatbForm);
						}
					}
				}));

		/* List ExamenATB Handler	 */
		registrations.add(eventBus.addHandler(ListExamenATBEvent.TYPE,
				new ListExamenATBEvent.Handler() {
					public void listExamenATB() {
						if (AccessManager.canDirectAccessExamenATB()
								&& AccessManager.canReadExamenATB())
							displayWrapperPanel(new ExamenATBListPanel(
									requestFactory));
					}
					public void listExamenATB(String searchText) {
						if (AccessManager.canDirectAccessExamenATB()
								&& AccessManager.canReadExamenATB())
							displayWrapperPanel(new ExamenATBListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity PriseMedicamenteuse 
		 * */

		/* Create PriseMedicamenteuse Handler */
		registrations.add(eventBus.addHandler(
				CreatePriseMedicamenteuseEvent.TYPE,
				new CreatePriseMedicamenteuseEvent.Handler() {
					public void createNewPriseMedicamenteuse(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreatePriseMedicamenteuse()
								&& AccessManager.canEditPriseMedicamenteuse()) {
							PriseMedicamenteuseFormPanel prisemedicamenteuseForm = new PriseMedicamenteuseFormPanel(
									requestFactory, null);
							prisemedicamenteuseForm.setCloseEvent(closeEvent);
							displayWrapperPanel(prisemedicamenteuseForm);
						}
					}
				}));

		/* View PriseMedicamenteuse Handler */
		registrations.add(eventBus.addHandler(
				ViewPriseMedicamenteuseEvent.TYPE,
				new ViewPriseMedicamenteuseEvent.Handler() {
					public void viewPriseMedicamenteuse(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadPriseMedicamenteuse()) {
							PriseMedicamenteuseFormPanel prisemedicamenteuseForm = new PriseMedicamenteuseFormPanel(
									requestFactory, entityId);
							prisemedicamenteuseForm.setCloseEvent(closeEvent);
							displayWrapperPanel(prisemedicamenteuseForm);
						}
					}
				}));

		/* List PriseMedicamenteuse Handler	 */
		registrations.add(eventBus.addHandler(
				ListPriseMedicamenteuseEvent.TYPE,
				new ListPriseMedicamenteuseEvent.Handler() {
					public void listPriseMedicamenteuse() {
						if (AccessManager.canDirectAccessPriseMedicamenteuse()
								&& AccessManager.canReadPriseMedicamenteuse())
							displayWrapperPanel(new PriseMedicamenteuseListPanel(
									requestFactory));
					}
					public void listPriseMedicamenteuse(String searchText) {
						if (AccessManager.canDirectAccessPriseMedicamenteuse()
								&& AccessManager.canReadPriseMedicamenteuse())
							displayWrapperPanel(new PriseMedicamenteuseListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity RendezVous 
		 * */

		/* Create RendezVous Handler */
		registrations.add(eventBus.addHandler(CreateRendezVousEvent.TYPE,
				new CreateRendezVousEvent.Handler() {
					public void createNewRendezVous(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateRendezVous()
								&& AccessManager.canEditRendezVous()) {
							RendezVousFormPanel rendezvousForm = new RendezVousFormPanel(
									requestFactory, null);
							rendezvousForm.setCloseEvent(closeEvent);
							displayWrapperPanel(rendezvousForm);
						}
					}
				}));

		/* View RendezVous Handler */
		registrations.add(eventBus.addHandler(ViewRendezVousEvent.TYPE,
				new ViewRendezVousEvent.Handler() {
					public void viewRendezVous(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadRendezVous()) {
							RendezVousFormPanel rendezvousForm = new RendezVousFormPanel(
									requestFactory, entityId);
							rendezvousForm.setCloseEvent(closeEvent);
							displayWrapperPanel(rendezvousForm);
						}
					}
				}));

		/* List RendezVous Handler	 */
		registrations.add(eventBus.addHandler(ListRendezVousEvent.TYPE,
				new ListRendezVousEvent.Handler() {
					public void listRendezVous() {
						if (AccessManager.canDirectAccessRendezVous()
								&& AccessManager.canReadRendezVous())
							displayWrapperPanel(new RendezVousListPanel(
									requestFactory));
					}
					public void listRendezVous(String searchText) {
						if (AccessManager.canDirectAccessRendezVous()
								&& AccessManager.canReadRendezVous())
							displayWrapperPanel(new RendezVousListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity TransfertReference 
		 * */

		/* Create TransfertReference Handler */
		registrations.add(eventBus.addHandler(
				CreateTransfertReferenceEvent.TYPE,
				new CreateTransfertReferenceEvent.Handler() {
					public void createNewTransfertReference(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateTransfertReference()
								&& AccessManager.canEditTransfertReference()) {
							TransfertReferenceFormPanel transfertreferenceForm = new TransfertReferenceFormPanel(
									requestFactory, null);
							transfertreferenceForm.setCloseEvent(closeEvent);
							displayWrapperPanel(transfertreferenceForm);
						}
					}
				}));

		/* View TransfertReference Handler */
		registrations.add(eventBus.addHandler(ViewTransfertReferenceEvent.TYPE,
				new ViewTransfertReferenceEvent.Handler() {
					public void viewTransfertReference(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadTransfertReference()) {
							TransfertReferenceFormPanel transfertreferenceForm = new TransfertReferenceFormPanel(
									requestFactory, entityId);
							transfertreferenceForm.setCloseEvent(closeEvent);
							displayWrapperPanel(transfertreferenceForm);
						}
					}
				}));

		/* List TransfertReference Handler	 */
		registrations.add(eventBus.addHandler(ListTransfertReferenceEvent.TYPE,
				new ListTransfertReferenceEvent.Handler() {
					public void listTransfertReference() {
						if (AccessManager.canDirectAccessTransfertReference()
								&& AccessManager.canReadTransfertReference())
							displayWrapperPanel(new TransfertReferenceListPanel(
									requestFactory));
					}
					public void listTransfertReference(String searchText) {
						if (AccessManager.canDirectAccessTransfertReference()
								&& AccessManager.canReadTransfertReference())
							displayWrapperPanel(new TransfertReferenceListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Lot 
		 * */

		/* Create Lot Handler */
		registrations.add(eventBus.addHandler(CreateLotEvent.TYPE,
				new CreateLotEvent.Handler() {
					public void createNewLot(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateLot()
								&& AccessManager.canEditLot()) {
							LotFormPanel lotForm = new LotFormPanel(
									requestFactory, null);
							lotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(lotForm);
						}
					}
				}));

		/* View Lot Handler */
		registrations.add(eventBus.addHandler(ViewLotEvent.TYPE,
				new ViewLotEvent.Handler() {
					public void viewLot(String entityId, GwtEvent<?> closeEvent) {
						if (AccessManager.canReadLot()) {
							LotFormPanel lotForm = new LotFormPanel(
									requestFactory, entityId);
							lotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(lotForm);
						}
					}
				}));

		/* List Lot Handler	 */
		registrations.add(eventBus.addHandler(ListLotEvent.TYPE,
				new ListLotEvent.Handler() {
					public void listLot() {
						if (AccessManager.canDirectAccessLot()
								&& AccessManager.canReadLot())
							displayWrapperPanel(new LotListPanel(requestFactory));
					}
					public void listLot(String searchText) {
						if (AccessManager.canDirectAccessLot()
								&& AccessManager.canReadLot())
							displayWrapperPanel(new LotListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity HorsUsage 
		 * */

		/* Create HorsUsage Handler */
		registrations.add(eventBus.addHandler(CreateHorsUsageEvent.TYPE,
				new CreateHorsUsageEvent.Handler() {
					public void createNewHorsUsage(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateHorsUsage()
								&& AccessManager.canEditHorsUsage()) {
							HorsUsageFormPanel horsusageForm = new HorsUsageFormPanel(
									requestFactory, null);
							horsusageForm.setCloseEvent(closeEvent);
							displayWrapperPanel(horsusageForm);
						}
					}
				}));

		/* View HorsUsage Handler */
		registrations.add(eventBus.addHandler(ViewHorsUsageEvent.TYPE,
				new ViewHorsUsageEvent.Handler() {
					public void viewHorsUsage(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadHorsUsage()) {
							HorsUsageFormPanel horsusageForm = new HorsUsageFormPanel(
									requestFactory, entityId);
							horsusageForm.setCloseEvent(closeEvent);
							displayWrapperPanel(horsusageForm);
						}
					}
				}));

		/* List HorsUsage Handler	 */
		registrations.add(eventBus.addHandler(ListHorsUsageEvent.TYPE,
				new ListHorsUsageEvent.Handler() {
					public void listHorsUsage() {
						if (AccessManager.canDirectAccessHorsUsage()
								&& AccessManager.canReadHorsUsage())
							displayWrapperPanel(new HorsUsageListPanel(
									requestFactory));
					}
					public void listHorsUsage(String searchText) {
						if (AccessManager.canDirectAccessHorsUsage()
								&& AccessManager.canReadHorsUsage())
							displayWrapperPanel(new HorsUsageListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity EntreeLot 
		 * */

		/* Create EntreeLot Handler */
		registrations.add(eventBus.addHandler(CreateEntreeLotEvent.TYPE,
				new CreateEntreeLotEvent.Handler() {
					public void createNewEntreeLot(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateEntreeLot()
								&& AccessManager.canEditEntreeLot()) {
							EntreeLotFormPanel entreelotForm = new EntreeLotFormPanel(
									requestFactory, null);
							entreelotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(entreelotForm);
						}
					}
				}));

		/* View EntreeLot Handler */
		registrations.add(eventBus.addHandler(ViewEntreeLotEvent.TYPE,
				new ViewEntreeLotEvent.Handler() {
					public void viewEntreeLot(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadEntreeLot()) {
							EntreeLotFormPanel entreelotForm = new EntreeLotFormPanel(
									requestFactory, entityId);
							entreelotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(entreelotForm);
						}
					}
				}));

		/* List EntreeLot Handler	 */
		registrations.add(eventBus.addHandler(ListEntreeLotEvent.TYPE,
				new ListEntreeLotEvent.Handler() {
					public void listEntreeLot() {
						if (AccessManager.canDirectAccessEntreeLot()
								&& AccessManager.canReadEntreeLot())
							displayWrapperPanel(new EntreeLotListPanel(
									requestFactory));
					}
					public void listEntreeLot(String searchText) {
						if (AccessManager.canDirectAccessEntreeLot()
								&& AccessManager.canReadEntreeLot())
							displayWrapperPanel(new EntreeLotListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity SortieLot 
		 * */

		/* Create SortieLot Handler */
		registrations.add(eventBus.addHandler(CreateSortieLotEvent.TYPE,
				new CreateSortieLotEvent.Handler() {
					public void createNewSortieLot(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateSortieLot()
								&& AccessManager.canEditSortieLot()) {
							SortieLotFormPanel sortielotForm = new SortieLotFormPanel(
									requestFactory, null);
							sortielotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(sortielotForm);
						}
					}
				}));

		/* View SortieLot Handler */
		registrations.add(eventBus.addHandler(ViewSortieLotEvent.TYPE,
				new ViewSortieLotEvent.Handler() {
					public void viewSortieLot(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadSortieLot()) {
							SortieLotFormPanel sortielotForm = new SortieLotFormPanel(
									requestFactory, entityId);
							sortielotForm.setCloseEvent(closeEvent);
							displayWrapperPanel(sortielotForm);
						}
					}
				}));

		/* List SortieLot Handler	 */
		registrations.add(eventBus.addHandler(ListSortieLotEvent.TYPE,
				new ListSortieLotEvent.Handler() {
					public void listSortieLot() {
						if (AccessManager.canDirectAccessSortieLot()
								&& AccessManager.canReadSortieLot())
							displayWrapperPanel(new SortieLotListPanel(
									requestFactory));
					}
					public void listSortieLot(String searchText) {
						if (AccessManager.canDirectAccessSortieLot()
								&& AccessManager.canReadSortieLot())
							displayWrapperPanel(new SortieLotListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Commande 
		 * */

		/* Create Commande Handler */
		registrations.add(eventBus.addHandler(CreateCommandeEvent.TYPE,
				new CreateCommandeEvent.Handler() {
					public void createNewCommande(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateCommande()
								&& AccessManager.canEditCommande()) {
							CommandeFormPanel commandeForm = new CommandeFormPanel(
									requestFactory, null);
							commandeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(commandeForm);
						}
					}
				}));

		/* View Commande Handler */
		registrations.add(eventBus.addHandler(ViewCommandeEvent.TYPE,
				new ViewCommandeEvent.Handler() {
					public void viewCommande(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadCommande()) {
							CommandeFormPanel commandeForm = new CommandeFormPanel(
									requestFactory, entityId);
							commandeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(commandeForm);
						}
					}
				}));

		/* List Commande Handler	 */
		registrations.add(eventBus.addHandler(ListCommandeEvent.TYPE,
				new ListCommandeEvent.Handler() {
					public void listCommande() {
						if (AccessManager.canDirectAccessCommande()
								&& AccessManager.canReadCommande())
							displayWrapperPanel(new CommandeListPanel(
									requestFactory));
					}
					public void listCommande(String searchText) {
						if (AccessManager.canDirectAccessCommande()
								&& AccessManager.canReadCommande())
							displayWrapperPanel(new CommandeListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailCommandeMedicament 
		 * */

		/* Create DetailCommandeMedicament Handler */
		registrations.add(eventBus.addHandler(
				CreateDetailCommandeMedicamentEvent.TYPE,
				new CreateDetailCommandeMedicamentEvent.Handler() {
					public void createNewDetailCommandeMedicament(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailCommandeMedicament()
								&& AccessManager
										.canEditDetailCommandeMedicament()) {
							DetailCommandeMedicamentFormPanel detailcommandemedicamentForm = new DetailCommandeMedicamentFormPanel(
									requestFactory, null);
							detailcommandemedicamentForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailcommandemedicamentForm);
						}
					}
				}));

		/* View DetailCommandeMedicament Handler */
		registrations.add(eventBus.addHandler(
				ViewDetailCommandeMedicamentEvent.TYPE,
				new ViewDetailCommandeMedicamentEvent.Handler() {
					public void viewDetailCommandeMedicament(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailCommandeMedicament()) {
							DetailCommandeMedicamentFormPanel detailcommandemedicamentForm = new DetailCommandeMedicamentFormPanel(
									requestFactory, entityId);
							detailcommandemedicamentForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailcommandemedicamentForm);
						}
					}
				}));

		/* List DetailCommandeMedicament Handler	 */
		registrations.add(eventBus.addHandler(
				ListDetailCommandeMedicamentEvent.TYPE,
				new ListDetailCommandeMedicamentEvent.Handler() {
					public void listDetailCommandeMedicament() {
						if (AccessManager
								.canDirectAccessDetailCommandeMedicament()
								&& AccessManager
										.canReadDetailCommandeMedicament())
							displayWrapperPanel(new DetailCommandeMedicamentListPanel(
									requestFactory));
					}
					public void listDetailCommandeMedicament(String searchText) {
						if (AccessManager
								.canDirectAccessDetailCommandeMedicament()
								&& AccessManager
										.canReadDetailCommandeMedicament())
							displayWrapperPanel(new DetailCommandeMedicamentListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailCommandeIntrant 
		 * */

		/* Create DetailCommandeIntrant Handler */
		registrations.add(eventBus.addHandler(
				CreateDetailCommandeIntrantEvent.TYPE,
				new CreateDetailCommandeIntrantEvent.Handler() {
					public void createNewDetailCommandeIntrant(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailCommandeIntrant()
								&& AccessManager.canEditDetailCommandeIntrant()) {
							DetailCommandeIntrantFormPanel detailcommandeintrantForm = new DetailCommandeIntrantFormPanel(
									requestFactory, null);
							detailcommandeintrantForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailcommandeintrantForm);
						}
					}
				}));

		/* View DetailCommandeIntrant Handler */
		registrations.add(eventBus.addHandler(
				ViewDetailCommandeIntrantEvent.TYPE,
				new ViewDetailCommandeIntrantEvent.Handler() {
					public void viewDetailCommandeIntrant(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailCommandeIntrant()) {
							DetailCommandeIntrantFormPanel detailcommandeintrantForm = new DetailCommandeIntrantFormPanel(
									requestFactory, entityId);
							detailcommandeintrantForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailcommandeintrantForm);
						}
					}
				}));

		/* List DetailCommandeIntrant Handler	 */
		registrations.add(eventBus.addHandler(
				ListDetailCommandeIntrantEvent.TYPE,
				new ListDetailCommandeIntrantEvent.Handler() {
					public void listDetailCommandeIntrant() {
						if (AccessManager
								.canDirectAccessDetailCommandeIntrant()
								&& AccessManager.canReadDetailCommandeIntrant())
							displayWrapperPanel(new DetailCommandeIntrantListPanel(
									requestFactory));
					}
					public void listDetailCommandeIntrant(String searchText) {
						if (AccessManager
								.canDirectAccessDetailCommandeIntrant()
								&& AccessManager.canReadDetailCommandeIntrant())
							displayWrapperPanel(new DetailCommandeIntrantListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Reception 
		 * */

		/* Create Reception Handler */
		registrations.add(eventBus.addHandler(CreateReceptionEvent.TYPE,
				new CreateReceptionEvent.Handler() {
					public void createNewReception(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateReception()
								&& AccessManager.canEditReception()) {
							ReceptionFormPanel receptionForm = new ReceptionFormPanel(
									requestFactory, null);
							receptionForm.setCloseEvent(closeEvent);
							displayWrapperPanel(receptionForm);
						}
					}
				}));

		/* View Reception Handler */
		registrations.add(eventBus.addHandler(ViewReceptionEvent.TYPE,
				new ViewReceptionEvent.Handler() {
					public void viewReception(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadReception()) {
							ReceptionFormPanel receptionForm = new ReceptionFormPanel(
									requestFactory, entityId);
							receptionForm.setCloseEvent(closeEvent);
							displayWrapperPanel(receptionForm);
						}
					}
				}));

		/* List Reception Handler	 */
		registrations.add(eventBus.addHandler(ListReceptionEvent.TYPE,
				new ListReceptionEvent.Handler() {
					public void listReception() {
						if (AccessManager.canDirectAccessReception()
								&& AccessManager.canReadReception())
							displayWrapperPanel(new ReceptionListPanel(
									requestFactory));
					}
					public void listReception(String searchText) {
						if (AccessManager.canDirectAccessReception()
								&& AccessManager.canReadReception())
							displayWrapperPanel(new ReceptionListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailReceptionMedicament 
		 * */

		/* Create DetailReceptionMedicament Handler */
		registrations.add(eventBus.addHandler(
				CreateDetailReceptionMedicamentEvent.TYPE,
				new CreateDetailReceptionMedicamentEvent.Handler() {
					public void createNewDetailReceptionMedicament(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailReceptionMedicament()
								&& AccessManager
										.canEditDetailReceptionMedicament()) {
							DetailReceptionMedicamentFormPanel detailreceptionmedicamentForm = new DetailReceptionMedicamentFormPanel(
									requestFactory, null);
							detailreceptionmedicamentForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailreceptionmedicamentForm);
						}
					}
				}));

		/* View DetailReceptionMedicament Handler */
		registrations.add(eventBus.addHandler(
				ViewDetailReceptionMedicamentEvent.TYPE,
				new ViewDetailReceptionMedicamentEvent.Handler() {
					public void viewDetailReceptionMedicament(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailReceptionMedicament()) {
							DetailReceptionMedicamentFormPanel detailreceptionmedicamentForm = new DetailReceptionMedicamentFormPanel(
									requestFactory, entityId);
							detailreceptionmedicamentForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailreceptionmedicamentForm);
						}
					}
				}));

		/* List DetailReceptionMedicament Handler	 */
		registrations.add(eventBus.addHandler(
				ListDetailReceptionMedicamentEvent.TYPE,
				new ListDetailReceptionMedicamentEvent.Handler() {
					public void listDetailReceptionMedicament() {
						if (AccessManager
								.canDirectAccessDetailReceptionMedicament()
								&& AccessManager
										.canReadDetailReceptionMedicament())
							displayWrapperPanel(new DetailReceptionMedicamentListPanel(
									requestFactory));
					}
					public void listDetailReceptionMedicament(String searchText) {
						if (AccessManager
								.canDirectAccessDetailReceptionMedicament()
								&& AccessManager
										.canReadDetailReceptionMedicament())
							displayWrapperPanel(new DetailReceptionMedicamentListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailReceptionIntrant 
		 * */

		/* Create DetailReceptionIntrant Handler */
		registrations.add(eventBus.addHandler(
				CreateDetailReceptionIntrantEvent.TYPE,
				new CreateDetailReceptionIntrantEvent.Handler() {
					public void createNewDetailReceptionIntrant(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailReceptionIntrant()
								&& AccessManager
										.canEditDetailReceptionIntrant()) {
							DetailReceptionIntrantFormPanel detailreceptionintrantForm = new DetailReceptionIntrantFormPanel(
									requestFactory, null);
							detailreceptionintrantForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailreceptionintrantForm);
						}
					}
				}));

		/* View DetailReceptionIntrant Handler */
		registrations.add(eventBus.addHandler(
				ViewDetailReceptionIntrantEvent.TYPE,
				new ViewDetailReceptionIntrantEvent.Handler() {
					public void viewDetailReceptionIntrant(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailReceptionIntrant()) {
							DetailReceptionIntrantFormPanel detailreceptionintrantForm = new DetailReceptionIntrantFormPanel(
									requestFactory, entityId);
							detailreceptionintrantForm
									.setCloseEvent(closeEvent);
							displayWrapperPanel(detailreceptionintrantForm);
						}
					}
				}));

		/* List DetailReceptionIntrant Handler	 */
		registrations.add(eventBus.addHandler(
				ListDetailReceptionIntrantEvent.TYPE,
				new ListDetailReceptionIntrantEvent.Handler() {
					public void listDetailReceptionIntrant() {
						if (AccessManager
								.canDirectAccessDetailReceptionIntrant()
								&& AccessManager
										.canReadDetailReceptionIntrant())
							displayWrapperPanel(new DetailReceptionIntrantListPanel(
									requestFactory));
					}
					public void listDetailReceptionIntrant(String searchText) {
						if (AccessManager
								.canDirectAccessDetailReceptionIntrant()
								&& AccessManager
										.canReadDetailReceptionIntrant())
							displayWrapperPanel(new DetailReceptionIntrantListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Ravitaillement 
		 * */

		/* Create Ravitaillement Handler */
		registrations.add(eventBus.addHandler(CreateRavitaillementEvent.TYPE,
				new CreateRavitaillementEvent.Handler() {
					public void createNewRavitaillement(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateRavitaillement()
								&& AccessManager.canEditRavitaillement()) {
							RavitaillementFormPanel ravitaillementForm = new RavitaillementFormPanel(
									requestFactory, null);
							ravitaillementForm.setCloseEvent(closeEvent);
							displayWrapperPanel(ravitaillementForm);
						}
					}
				}));

		/* View Ravitaillement Handler */
		registrations.add(eventBus.addHandler(ViewRavitaillementEvent.TYPE,
				new ViewRavitaillementEvent.Handler() {
					public void viewRavitaillement(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadRavitaillement()) {
							RavitaillementFormPanel ravitaillementForm = new RavitaillementFormPanel(
									requestFactory, entityId);
							ravitaillementForm.setCloseEvent(closeEvent);
							displayWrapperPanel(ravitaillementForm);
						}
					}
				}));

		/* List Ravitaillement Handler	 */
		registrations.add(eventBus.addHandler(ListRavitaillementEvent.TYPE,
				new ListRavitaillementEvent.Handler() {
					public void listRavitaillement() {
						if (AccessManager.canDirectAccessRavitaillement()
								&& AccessManager.canReadRavitaillement())
							displayWrapperPanel(new RavitaillementListPanel(
									requestFactory));
					}
					public void listRavitaillement(String searchText) {
						if (AccessManager.canDirectAccessRavitaillement()
								&& AccessManager.canReadRavitaillement())
							displayWrapperPanel(new RavitaillementListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailRavitaillement 
		 * */

		/* Create DetailRavitaillement Handler */
		registrations.add(eventBus.addHandler(
				CreateDetailRavitaillementEvent.TYPE,
				new CreateDetailRavitaillementEvent.Handler() {
					public void createNewDetailRavitaillement(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailRavitaillement()
								&& AccessManager.canEditDetailRavitaillement()) {
							DetailRavitaillementFormPanel detailravitaillementForm = new DetailRavitaillementFormPanel(
									requestFactory, null);
							detailravitaillementForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailravitaillementForm);
						}
					}
				}));

		/* View DetailRavitaillement Handler */
		registrations.add(eventBus.addHandler(
				ViewDetailRavitaillementEvent.TYPE,
				new ViewDetailRavitaillementEvent.Handler() {
					public void viewDetailRavitaillement(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailRavitaillement()) {
							DetailRavitaillementFormPanel detailravitaillementForm = new DetailRavitaillementFormPanel(
									requestFactory, entityId);
							detailravitaillementForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailravitaillementForm);
						}
					}
				}));

		/* List DetailRavitaillement Handler	 */
		registrations.add(eventBus.addHandler(
				ListDetailRavitaillementEvent.TYPE,
				new ListDetailRavitaillementEvent.Handler() {
					public void listDetailRavitaillement() {
						if (AccessManager.canDirectAccessDetailRavitaillement()
								&& AccessManager.canReadDetailRavitaillement())
							displayWrapperPanel(new DetailRavitaillementListPanel(
									requestFactory));
					}
					public void listDetailRavitaillement(String searchText) {
						if (AccessManager.canDirectAccessDetailRavitaillement()
								&& AccessManager.canReadDetailRavitaillement())
							displayWrapperPanel(new DetailRavitaillementListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Inventaire 
		 * */

		/* Create Inventaire Handler */
		registrations.add(eventBus.addHandler(CreateInventaireEvent.TYPE,
				new CreateInventaireEvent.Handler() {
					public void createNewInventaire(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateInventaire()
								&& AccessManager.canEditInventaire()) {
							InventaireFormPanel inventaireForm = new InventaireFormPanel(
									requestFactory, null);
							inventaireForm.setCloseEvent(closeEvent);
							displayWrapperPanel(inventaireForm);
						}
					}
				}));

		/* View Inventaire Handler */
		registrations.add(eventBus.addHandler(ViewInventaireEvent.TYPE,
				new ViewInventaireEvent.Handler() {
					public void viewInventaire(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadInventaire()) {
							InventaireFormPanel inventaireForm = new InventaireFormPanel(
									requestFactory, entityId);
							inventaireForm.setCloseEvent(closeEvent);
							displayWrapperPanel(inventaireForm);
						}
					}
				}));

		/* List Inventaire Handler	 */
		registrations.add(eventBus.addHandler(ListInventaireEvent.TYPE,
				new ListInventaireEvent.Handler() {
					public void listInventaire() {
						if (AccessManager.canDirectAccessInventaire()
								&& AccessManager.canReadInventaire())
							displayWrapperPanel(new InventaireListPanel(
									requestFactory));
					}
					public void listInventaire(String searchText) {
						if (AccessManager.canDirectAccessInventaire()
								&& AccessManager.canReadInventaire())
							displayWrapperPanel(new InventaireListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DetailInventaire 
		 * */

		/* Create DetailInventaire Handler */
		registrations.add(eventBus.addHandler(CreateDetailInventaireEvent.TYPE,
				new CreateDetailInventaireEvent.Handler() {
					public void createNewDetailInventaire(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDetailInventaire()
								&& AccessManager.canEditDetailInventaire()) {
							DetailInventaireFormPanel detailinventaireForm = new DetailInventaireFormPanel(
									requestFactory, null);
							detailinventaireForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailinventaireForm);
						}
					}
				}));

		/* View DetailInventaire Handler */
		registrations.add(eventBus.addHandler(ViewDetailInventaireEvent.TYPE,
				new ViewDetailInventaireEvent.Handler() {
					public void viewDetailInventaire(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDetailInventaire()) {
							DetailInventaireFormPanel detailinventaireForm = new DetailInventaireFormPanel(
									requestFactory, entityId);
							detailinventaireForm.setCloseEvent(closeEvent);
							displayWrapperPanel(detailinventaireForm);
						}
					}
				}));

		/* List DetailInventaire Handler	 */
		registrations.add(eventBus.addHandler(ListDetailInventaireEvent.TYPE,
				new ListDetailInventaireEvent.Handler() {
					public void listDetailInventaire() {
						if (AccessManager.canDirectAccessDetailInventaire()
								&& AccessManager.canReadDetailInventaire())
							displayWrapperPanel(new DetailInventaireListPanel(
									requestFactory));
					}
					public void listDetailInventaire(String searchText) {
						if (AccessManager.canDirectAccessDetailInventaire()
								&& AccessManager.canReadDetailInventaire())
							displayWrapperPanel(new DetailInventaireListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Personnel 
		 * */

		/* Create Personnel Handler */
		registrations.add(eventBus.addHandler(CreatePersonnelEvent.TYPE,
				new CreatePersonnelEvent.Handler() {
					public void createNewPersonnel(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreatePersonnel()
								&& AccessManager.canEditPersonnel()) {
							PersonnelFormPanel personnelForm = new PersonnelFormPanel(
									requestFactory, null);
							personnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(personnelForm);
						}
					}
				}));

		/* View Personnel Handler */
		registrations.add(eventBus.addHandler(ViewPersonnelEvent.TYPE,
				new ViewPersonnelEvent.Handler() {
					public void viewPersonnel(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadPersonnel()) {
							PersonnelFormPanel personnelForm = new PersonnelFormPanel(
									requestFactory, entityId);
							personnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(personnelForm);
						}
					}
				}));

		/* List Personnel Handler	 */
		registrations.add(eventBus.addHandler(ListPersonnelEvent.TYPE,
				new ListPersonnelEvent.Handler() {
					public void listPersonnel() {
						if (AccessManager.canDirectAccessPersonnel()
								&& AccessManager.canReadPersonnel())
							displayWrapperPanel(new PersonnelListPanel(
									requestFactory));
					}
					public void listPersonnel(String searchText) {
						if (AccessManager.canDirectAccessPersonnel()
								&& AccessManager.canReadPersonnel())
							displayWrapperPanel(new PersonnelListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DepartPersonnel 
		 * */

		/* Create DepartPersonnel Handler */
		registrations.add(eventBus.addHandler(CreateDepartPersonnelEvent.TYPE,
				new CreateDepartPersonnelEvent.Handler() {
					public void createNewDepartPersonnel(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDepartPersonnel()
								&& AccessManager.canEditDepartPersonnel()) {
							DepartPersonnelFormPanel departpersonnelForm = new DepartPersonnelFormPanel(
									requestFactory, null);
							departpersonnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(departpersonnelForm);
						}
					}
				}));

		/* View DepartPersonnel Handler */
		registrations.add(eventBus.addHandler(ViewDepartPersonnelEvent.TYPE,
				new ViewDepartPersonnelEvent.Handler() {
					public void viewDepartPersonnel(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDepartPersonnel()) {
							DepartPersonnelFormPanel departpersonnelForm = new DepartPersonnelFormPanel(
									requestFactory, entityId);
							departpersonnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(departpersonnelForm);
						}
					}
				}));

		/* List DepartPersonnel Handler	 */
		registrations.add(eventBus.addHandler(ListDepartPersonnelEvent.TYPE,
				new ListDepartPersonnelEvent.Handler() {
					public void listDepartPersonnel() {
						if (AccessManager.canDirectAccessDepartPersonnel()
								&& AccessManager.canReadDepartPersonnel())
							displayWrapperPanel(new DepartPersonnelListPanel(
									requestFactory));
					}
					public void listDepartPersonnel(String searchText) {
						if (AccessManager.canDirectAccessDepartPersonnel()
								&& AccessManager.canReadDepartPersonnel())
							displayWrapperPanel(new DepartPersonnelListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity ArriveePersonnel 
		 * */

		/* Create ArriveePersonnel Handler */
		registrations.add(eventBus.addHandler(CreateArriveePersonnelEvent.TYPE,
				new CreateArriveePersonnelEvent.Handler() {
					public void createNewArriveePersonnel(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateArriveePersonnel()
								&& AccessManager.canEditArriveePersonnel()) {
							ArriveePersonnelFormPanel arriveepersonnelForm = new ArriveePersonnelFormPanel(
									requestFactory, null);
							arriveepersonnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(arriveepersonnelForm);
						}
					}
				}));

		/* View ArriveePersonnel Handler */
		registrations.add(eventBus.addHandler(ViewArriveePersonnelEvent.TYPE,
				new ViewArriveePersonnelEvent.Handler() {
					public void viewArriveePersonnel(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadArriveePersonnel()) {
							ArriveePersonnelFormPanel arriveepersonnelForm = new ArriveePersonnelFormPanel(
									requestFactory, entityId);
							arriveepersonnelForm.setCloseEvent(closeEvent);
							displayWrapperPanel(arriveepersonnelForm);
						}
					}
				}));

		/* List ArriveePersonnel Handler	 */
		registrations.add(eventBus.addHandler(ListArriveePersonnelEvent.TYPE,
				new ListArriveePersonnelEvent.Handler() {
					public void listArriveePersonnel() {
						if (AccessManager.canDirectAccessArriveePersonnel()
								&& AccessManager.canReadArriveePersonnel())
							displayWrapperPanel(new ArriveePersonnelListPanel(
									requestFactory));
					}
					public void listArriveePersonnel(String searchText) {
						if (AccessManager.canDirectAccessArriveePersonnel()
								&& AccessManager.canReadArriveePersonnel())
							displayWrapperPanel(new ArriveePersonnelListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Region 
		 * */

		/* Create Region Handler */
		registrations.add(eventBus.addHandler(CreateRegionEvent.TYPE,
				new CreateRegionEvent.Handler() {
					public void createNewRegion(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateRegion()
								&& AccessManager.canEditRegion()) {
							RegionFormPanel regionForm = new RegionFormPanel(
									requestFactory, null);
							regionForm.setCloseEvent(closeEvent);
							displayWrapperPanel(regionForm);
						}
					}
				}));

		/* View Region Handler */
		registrations.add(eventBus.addHandler(ViewRegionEvent.TYPE,
				new ViewRegionEvent.Handler() {
					public void viewRegion(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadRegion()) {
							RegionFormPanel regionForm = new RegionFormPanel(
									requestFactory, entityId);
							regionForm.setCloseEvent(closeEvent);
							displayWrapperPanel(regionForm);
						}
					}
				}));

		/* List Region Handler	 */
		registrations.add(eventBus.addHandler(ListRegionEvent.TYPE,
				new ListRegionEvent.Handler() {
					public void listRegion() {
						if (AccessManager.canDirectAccessRegion()
								&& AccessManager.canReadRegion())
							displayWrapperPanel(new RegionListPanel(
									requestFactory));
					}
					public void listRegion(String searchText) {
						if (AccessManager.canDirectAccessRegion()
								&& AccessManager.canReadRegion())
							displayWrapperPanel(new RegionListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity DistrictSante 
		 * */

		/* Create DistrictSante Handler */
		registrations.add(eventBus.addHandler(CreateDistrictSanteEvent.TYPE,
				new CreateDistrictSanteEvent.Handler() {
					public void createNewDistrictSante(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateDistrictSante()
								&& AccessManager.canEditDistrictSante()) {
							DistrictSanteFormPanel districtsanteForm = new DistrictSanteFormPanel(
									requestFactory, null);
							districtsanteForm.setCloseEvent(closeEvent);
							displayWrapperPanel(districtsanteForm);
						}
					}
				}));

		/* View DistrictSante Handler */
		registrations.add(eventBus.addHandler(ViewDistrictSanteEvent.TYPE,
				new ViewDistrictSanteEvent.Handler() {
					public void viewDistrictSante(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadDistrictSante()) {
							DistrictSanteFormPanel districtsanteForm = new DistrictSanteFormPanel(
									requestFactory, entityId);
							districtsanteForm.setCloseEvent(closeEvent);
							displayWrapperPanel(districtsanteForm);
						}
					}
				}));

		/* List DistrictSante Handler	 */
		registrations.add(eventBus.addHandler(ListDistrictSanteEvent.TYPE,
				new ListDistrictSanteEvent.Handler() {
					public void listDistrictSante() {
						if (AccessManager.canDirectAccessDistrictSante()
								&& AccessManager.canReadDistrictSante())
							displayWrapperPanel(new DistrictSanteListPanel(
									requestFactory));
					}
					public void listDistrictSante(String searchText) {
						if (AccessManager.canDirectAccessDistrictSante()
								&& AccessManager.canReadDistrictSante())
							displayWrapperPanel(new DistrictSanteListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity CentreDiagTrait 
		 * */

		/* Create CentreDiagTrait Handler */
		registrations.add(eventBus.addHandler(CreateCentreDiagTraitEvent.TYPE,
				new CreateCentreDiagTraitEvent.Handler() {
					public void createNewCentreDiagTrait(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateCentreDiagTrait()
								&& AccessManager.canEditCentreDiagTrait()) {
							CentreDiagTraitFormPanel centrediagtraitForm = new CentreDiagTraitFormPanel(
									requestFactory, null);
							centrediagtraitForm.setCloseEvent(closeEvent);
							displayWrapperPanel(centrediagtraitForm);
						}
					}
				}));

		/* View CentreDiagTrait Handler */
		registrations.add(eventBus.addHandler(ViewCentreDiagTraitEvent.TYPE,
				new ViewCentreDiagTraitEvent.Handler() {
					public void viewCentreDiagTrait(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadCentreDiagTrait()) {
							CentreDiagTraitFormPanel centrediagtraitForm = new CentreDiagTraitFormPanel(
									requestFactory, entityId);
							centrediagtraitForm.setCloseEvent(closeEvent);
							displayWrapperPanel(centrediagtraitForm);
						}
					}
				}));

		/* List CentreDiagTrait Handler	 */
		registrations.add(eventBus.addHandler(ListCentreDiagTraitEvent.TYPE,
				new ListCentreDiagTraitEvent.Handler() {
					public void listCentreDiagTrait() {
						if (AccessManager.canDirectAccessCentreDiagTrait()
								&& AccessManager.canReadCentreDiagTrait())
							displayWrapperPanel(new CentreDiagTraitListPanel(
									requestFactory));
					}
					public void listCentreDiagTrait(String searchText) {
						if (AccessManager.canDirectAccessCentreDiagTrait()
								&& AccessManager.canReadCentreDiagTrait())
							displayWrapperPanel(new CentreDiagTraitListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity LaboratoireReference 
		 * */

		/* Create LaboratoireReference Handler */
		registrations.add(eventBus.addHandler(
				CreateLaboratoireReferenceEvent.TYPE,
				new CreateLaboratoireReferenceEvent.Handler() {
					public void createNewLaboratoireReference(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateLaboratoireReference()
								&& AccessManager.canEditLaboratoireReference()) {
							LaboratoireReferenceFormPanel laboratoirereferenceForm = new LaboratoireReferenceFormPanel(
									requestFactory, null);
							laboratoirereferenceForm.setCloseEvent(closeEvent);
							displayWrapperPanel(laboratoirereferenceForm);
						}
					}
				}));

		/* View LaboratoireReference Handler */
		registrations.add(eventBus.addHandler(
				ViewLaboratoireReferenceEvent.TYPE,
				new ViewLaboratoireReferenceEvent.Handler() {
					public void viewLaboratoireReference(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadLaboratoireReference()) {
							LaboratoireReferenceFormPanel laboratoirereferenceForm = new LaboratoireReferenceFormPanel(
									requestFactory, entityId);
							laboratoirereferenceForm.setCloseEvent(closeEvent);
							displayWrapperPanel(laboratoirereferenceForm);
						}
					}
				}));

		/* List LaboratoireReference Handler	 */
		registrations.add(eventBus.addHandler(
				ListLaboratoireReferenceEvent.TYPE,
				new ListLaboratoireReferenceEvent.Handler() {
					public void listLaboratoireReference() {
						if (AccessManager.canDirectAccessLaboratoireReference()
								&& AccessManager.canReadLaboratoireReference())
							displayWrapperPanel(new LaboratoireReferenceListPanel(
									requestFactory));
					}
					public void listLaboratoireReference(String searchText) {
						if (AccessManager.canDirectAccessLaboratoireReference()
								&& AccessManager.canReadLaboratoireReference())
							displayWrapperPanel(new LaboratoireReferenceListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity LieuDit 
		 * */

		/* Create LieuDit Handler */
		registrations.add(eventBus.addHandler(CreateLieuDitEvent.TYPE,
				new CreateLieuDitEvent.Handler() {
					public void createNewLieuDit(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateLieuDit()
								&& AccessManager.canEditLieuDit()) {
							LieuDitFormPanel lieuditForm = new LieuDitFormPanel(
									requestFactory, null);
							lieuditForm.setCloseEvent(closeEvent);
							displayWrapperPanel(lieuditForm);
						}
					}
				}));

		/* View LieuDit Handler */
		registrations.add(eventBus.addHandler(ViewLieuDitEvent.TYPE,
				new ViewLieuDitEvent.Handler() {
					public void viewLieuDit(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadLieuDit()) {
							LieuDitFormPanel lieuditForm = new LieuDitFormPanel(
									requestFactory, entityId);
							lieuditForm.setCloseEvent(closeEvent);
							displayWrapperPanel(lieuditForm);
						}
					}
				}));

		/* List LieuDit Handler	 */
		registrations.add(eventBus.addHandler(ListLieuDitEvent.TYPE,
				new ListLieuDitEvent.Handler() {
					public void listLieuDit() {
						if (AccessManager.canDirectAccessLieuDit()
								&& AccessManager.canReadLieuDit())
							displayWrapperPanel(new LieuDitListPanel(
									requestFactory));
					}
					public void listLieuDit(String searchText) {
						if (AccessManager.canDirectAccessLieuDit()
								&& AccessManager.canReadLieuDit())
							displayWrapperPanel(new LieuDitListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Regime 
		 * */

		/* Create Regime Handler */
		registrations.add(eventBus.addHandler(CreateRegimeEvent.TYPE,
				new CreateRegimeEvent.Handler() {
					public void createNewRegime(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateRegime()
								&& AccessManager.canEditRegime()) {
							RegimeFormPanel regimeForm = new RegimeFormPanel(
									requestFactory, null);
							regimeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(regimeForm);
						}
					}
				}));

		/* View Regime Handler */
		registrations.add(eventBus.addHandler(ViewRegimeEvent.TYPE,
				new ViewRegimeEvent.Handler() {
					public void viewRegime(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadRegime()) {
							RegimeFormPanel regimeForm = new RegimeFormPanel(
									requestFactory, entityId);
							regimeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(regimeForm);
						}
					}
				}));

		/* List Regime Handler	 */
		registrations.add(eventBus.addHandler(ListRegimeEvent.TYPE,
				new ListRegimeEvent.Handler() {
					public void listRegime() {
						if (AccessManager.canDirectAccessRegime()
								&& AccessManager.canReadRegime())
							displayWrapperPanel(new RegimeListPanel(
									requestFactory));
					}
					public void listRegime(String searchText) {
						if (AccessManager.canDirectAccessRegime()
								&& AccessManager.canReadRegime())
							displayWrapperPanel(new RegimeListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity PriseMedicamentRegime 
		 * */

		/* Create PriseMedicamentRegime Handler */
		registrations.add(eventBus.addHandler(
				CreatePriseMedicamentRegimeEvent.TYPE,
				new CreatePriseMedicamentRegimeEvent.Handler() {
					public void createNewPriseMedicamentRegime(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreatePriseMedicamentRegime()
								&& AccessManager.canEditPriseMedicamentRegime()) {
							PriseMedicamentRegimeFormPanel prisemedicamentregimeForm = new PriseMedicamentRegimeFormPanel(
									requestFactory, null);
							prisemedicamentregimeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(prisemedicamentregimeForm);
						}
					}
				}));

		/* View PriseMedicamentRegime Handler */
		registrations.add(eventBus.addHandler(
				ViewPriseMedicamentRegimeEvent.TYPE,
				new ViewPriseMedicamentRegimeEvent.Handler() {
					public void viewPriseMedicamentRegime(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadPriseMedicamentRegime()) {
							PriseMedicamentRegimeFormPanel prisemedicamentregimeForm = new PriseMedicamentRegimeFormPanel(
									requestFactory, entityId);
							prisemedicamentregimeForm.setCloseEvent(closeEvent);
							displayWrapperPanel(prisemedicamentregimeForm);
						}
					}
				}));

		/* List PriseMedicamentRegime Handler	 */
		registrations.add(eventBus.addHandler(
				ListPriseMedicamentRegimeEvent.TYPE,
				new ListPriseMedicamentRegimeEvent.Handler() {
					public void listPriseMedicamentRegime() {
						if (AccessManager
								.canDirectAccessPriseMedicamentRegime()
								&& AccessManager.canReadPriseMedicamentRegime())
							displayWrapperPanel(new PriseMedicamentRegimeListPanel(
									requestFactory));
					}
					public void listPriseMedicamentRegime(String searchText) {
						if (AccessManager
								.canDirectAccessPriseMedicamentRegime()
								&& AccessManager.canReadPriseMedicamentRegime())
							displayWrapperPanel(new PriseMedicamentRegimeListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Medicament 
		 * */

		/* Create Medicament Handler */
		registrations.add(eventBus.addHandler(CreateMedicamentEvent.TYPE,
				new CreateMedicamentEvent.Handler() {
					public void createNewMedicament(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateMedicament()
								&& AccessManager.canEditMedicament()) {
							MedicamentFormPanel medicamentForm = new MedicamentFormPanel(
									requestFactory, null);
							medicamentForm.setCloseEvent(closeEvent);
							displayWrapperPanel(medicamentForm);
						}
					}
				}));

		/* View Medicament Handler */
		registrations.add(eventBus.addHandler(ViewMedicamentEvent.TYPE,
				new ViewMedicamentEvent.Handler() {
					public void viewMedicament(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadMedicament()) {
							MedicamentFormPanel medicamentForm = new MedicamentFormPanel(
									requestFactory, entityId);
							medicamentForm.setCloseEvent(closeEvent);
							displayWrapperPanel(medicamentForm);
						}
					}
				}));

		/* List Medicament Handler	 */
		registrations.add(eventBus.addHandler(ListMedicamentEvent.TYPE,
				new ListMedicamentEvent.Handler() {
					public void listMedicament() {
						if (AccessManager.canDirectAccessMedicament()
								&& AccessManager.canReadMedicament())
							displayWrapperPanel(new MedicamentListPanel(
									requestFactory));
					}
					public void listMedicament(String searchText) {
						if (AccessManager.canDirectAccessMedicament()
								&& AccessManager.canReadMedicament())
							displayWrapperPanel(new MedicamentListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Intrant 
		 * */

		/* Create Intrant Handler */
		registrations.add(eventBus.addHandler(CreateIntrantEvent.TYPE,
				new CreateIntrantEvent.Handler() {
					public void createNewIntrant(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateIntrant()
								&& AccessManager.canEditIntrant()) {
							IntrantFormPanel intrantForm = new IntrantFormPanel(
									requestFactory, null);
							intrantForm.setCloseEvent(closeEvent);
							displayWrapperPanel(intrantForm);
						}
					}
				}));

		/* View Intrant Handler */
		registrations.add(eventBus.addHandler(ViewIntrantEvent.TYPE,
				new ViewIntrantEvent.Handler() {
					public void viewIntrant(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadIntrant()) {
							IntrantFormPanel intrantForm = new IntrantFormPanel(
									requestFactory, entityId);
							intrantForm.setCloseEvent(closeEvent);
							displayWrapperPanel(intrantForm);
						}
					}
				}));

		/* List Intrant Handler	 */
		registrations.add(eventBus.addHandler(ListIntrantEvent.TYPE,
				new ListIntrantEvent.Handler() {
					public void listIntrant() {
						if (AccessManager.canDirectAccessIntrant()
								&& AccessManager.canReadIntrant())
							displayWrapperPanel(new IntrantListPanel(
									requestFactory));
					}
					public void listIntrant(String searchText) {
						if (AccessManager.canDirectAccessIntrant()
								&& AccessManager.canReadIntrant())
							displayWrapperPanel(new IntrantListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Formation 
		 * */

		/* Create Formation Handler */
		registrations.add(eventBus.addHandler(CreateFormationEvent.TYPE,
				new CreateFormationEvent.Handler() {
					public void createNewFormation(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateFormation()
								&& AccessManager.canEditFormation()) {
							FormationFormPanel formationForm = new FormationFormPanel(
									requestFactory, null);
							formationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(formationForm);
						}
					}
				}));

		/* View Formation Handler */
		registrations.add(eventBus.addHandler(ViewFormationEvent.TYPE,
				new ViewFormationEvent.Handler() {
					public void viewFormation(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadFormation()) {
							FormationFormPanel formationForm = new FormationFormPanel(
									requestFactory, entityId);
							formationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(formationForm);
						}
					}
				}));

		/* List Formation Handler	 */
		registrations.add(eventBus.addHandler(ListFormationEvent.TYPE,
				new ListFormationEvent.Handler() {
					public void listFormation() {
						if (AccessManager.canDirectAccessFormation()
								&& AccessManager.canReadFormation())
							displayWrapperPanel(new FormationListPanel(
									requestFactory));
					}
					public void listFormation(String searchText) {
						if (AccessManager.canDirectAccessFormation()
								&& AccessManager.canReadFormation())
							displayWrapperPanel(new FormationListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity CandidatureFormation 
		 * */

		/* Create CandidatureFormation Handler */
		registrations.add(eventBus.addHandler(
				CreateCandidatureFormationEvent.TYPE,
				new CreateCandidatureFormationEvent.Handler() {
					public void createNewCandidatureFormation(
							GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateCandidatureFormation()
								&& AccessManager.canEditCandidatureFormation()) {
							CandidatureFormationFormPanel candidatureformationForm = new CandidatureFormationFormPanel(
									requestFactory, null);
							candidatureformationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(candidatureformationForm);
						}
					}
				}));

		/* View CandidatureFormation Handler */
		registrations.add(eventBus.addHandler(
				ViewCandidatureFormationEvent.TYPE,
				new ViewCandidatureFormationEvent.Handler() {
					public void viewCandidatureFormation(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadCandidatureFormation()) {
							CandidatureFormationFormPanel candidatureformationForm = new CandidatureFormationFormPanel(
									requestFactory, entityId);
							candidatureformationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(candidatureformationForm);
						}
					}
				}));

		/* List CandidatureFormation Handler	 */
		registrations.add(eventBus.addHandler(
				ListCandidatureFormationEvent.TYPE,
				new ListCandidatureFormationEvent.Handler() {
					public void listCandidatureFormation() {
						if (AccessManager.canDirectAccessCandidatureFormation()
								&& AccessManager.canReadCandidatureFormation())
							displayWrapperPanel(new CandidatureFormationListPanel(
									requestFactory));
					}
					public void listCandidatureFormation(String searchText) {
						if (AccessManager.canDirectAccessCandidatureFormation()
								&& AccessManager.canReadCandidatureFormation())
							displayWrapperPanel(new CandidatureFormationListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Qualification 
		 * */

		/* Create Qualification Handler */
		registrations.add(eventBus.addHandler(CreateQualificationEvent.TYPE,
				new CreateQualificationEvent.Handler() {
					public void createNewQualification(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateQualification()
								&& AccessManager.canEditQualification()) {
							QualificationFormPanel qualificationForm = new QualificationFormPanel(
									requestFactory, null);
							qualificationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(qualificationForm);
						}
					}
				}));

		/* View Qualification Handler */
		registrations.add(eventBus.addHandler(ViewQualificationEvent.TYPE,
				new ViewQualificationEvent.Handler() {
					public void viewQualification(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadQualification()) {
							QualificationFormPanel qualificationForm = new QualificationFormPanel(
									requestFactory, entityId);
							qualificationForm.setCloseEvent(closeEvent);
							displayWrapperPanel(qualificationForm);
						}
					}
				}));

		/* List Qualification Handler	 */
		registrations.add(eventBus.addHandler(ListQualificationEvent.TYPE,
				new ListQualificationEvent.Handler() {
					public void listQualification() {
						if (AccessManager.canDirectAccessQualification()
								&& AccessManager.canReadQualification())
							displayWrapperPanel(new QualificationListPanel(
									requestFactory));
					}
					public void listQualification(String searchText) {
						if (AccessManager.canDirectAccessQualification()
								&& AccessManager.canReadQualification())
							displayWrapperPanel(new QualificationListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Tutoriel 
		 * */

		/* Create Tutoriel Handler */
		registrations.add(eventBus.addHandler(CreateTutorielEvent.TYPE,
				new CreateTutorielEvent.Handler() {
					public void createNewTutoriel(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateTutoriel()
								&& AccessManager.canEditTutoriel()) {
							TutorielFormPanel tutorielForm = new TutorielFormPanel(
									requestFactory, null);
							tutorielForm.setCloseEvent(closeEvent);
							displayWrapperPanel(tutorielForm);
						}
					}
				}));

		/* View Tutoriel Handler */
		registrations.add(eventBus.addHandler(ViewTutorielEvent.TYPE,
				new ViewTutorielEvent.Handler() {
					public void viewTutoriel(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadTutoriel()) {
							TutorielFormPanel tutorielForm = new TutorielFormPanel(
									requestFactory, entityId);
							tutorielForm.setCloseEvent(closeEvent);
							displayWrapperPanel(tutorielForm);
						}
					}
				}));

		/* List Tutoriel Handler	 */
		registrations.add(eventBus.addHandler(ListTutorielEvent.TYPE,
				new ListTutorielEvent.Handler() {
					public void listTutoriel() {
						if (AccessManager.canDirectAccessTutoriel()
								&& AccessManager.canReadTutoriel())
							displayWrapperPanel(new TutorielListPanel(
									requestFactory));
					}
					public void listTutoriel(String searchText) {
						if (AccessManager.canDirectAccessTutoriel()
								&& AccessManager.canReadTutoriel())
							displayWrapperPanel(new TutorielListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity SmsPredefini 
		 * */

		/* Create SmsPredefini Handler */
		registrations.add(eventBus.addHandler(CreateSmsPredefiniEvent.TYPE,
				new CreateSmsPredefiniEvent.Handler() {
					public void createNewSmsPredefini(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateSmsPredefini()
								&& AccessManager.canEditSmsPredefini()) {
							SmsPredefiniFormPanel smspredefiniForm = new SmsPredefiniFormPanel(
									requestFactory, null);
							smspredefiniForm.setCloseEvent(closeEvent);
							displayWrapperPanel(smspredefiniForm);
						}
					}
				}));

		/* View SmsPredefini Handler */
		registrations.add(eventBus.addHandler(ViewSmsPredefiniEvent.TYPE,
				new ViewSmsPredefiniEvent.Handler() {
					public void viewSmsPredefini(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadSmsPredefini()) {
							SmsPredefiniFormPanel smspredefiniForm = new SmsPredefiniFormPanel(
									requestFactory, entityId);
							smspredefiniForm.setCloseEvent(closeEvent);
							displayWrapperPanel(smspredefiniForm);
						}
					}
				}));

		/* List SmsPredefini Handler	 */
		registrations.add(eventBus.addHandler(ListSmsPredefiniEvent.TYPE,
				new ListSmsPredefiniEvent.Handler() {
					public void listSmsPredefini() {
						if (AccessManager.canDirectAccessSmsPredefini()
								&& AccessManager.canReadSmsPredefini())
							displayWrapperPanel(new SmsPredefiniListPanel(
									requestFactory));
					}
					public void listSmsPredefini(String searchText) {
						if (AccessManager.canDirectAccessSmsPredefini()
								&& AccessManager.canReadSmsPredefini())
							displayWrapperPanel(new SmsPredefiniListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity OutBox 
		 * */

		/* Create OutBox Handler */
		registrations.add(eventBus.addHandler(CreateOutBoxEvent.TYPE,
				new CreateOutBoxEvent.Handler() {
					public void createNewOutBox(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateOutBox()
								&& AccessManager.canEditOutBox()) {
							OutBoxFormPanel outboxForm = new OutBoxFormPanel(
									requestFactory, null);
							outboxForm.setCloseEvent(closeEvent);
							displayWrapperPanel(outboxForm);
						}
					}
				}));

		/* View OutBox Handler */
		registrations.add(eventBus.addHandler(ViewOutBoxEvent.TYPE,
				new ViewOutBoxEvent.Handler() {
					public void viewOutBox(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadOutBox()) {
							OutBoxFormPanel outboxForm = new OutBoxFormPanel(
									requestFactory, entityId);
							outboxForm.setCloseEvent(closeEvent);
							displayWrapperPanel(outboxForm);
						}
					}
				}));

		/* List OutBox Handler	 */
		registrations.add(eventBus.addHandler(ListOutBoxEvent.TYPE,
				new ListOutBoxEvent.Handler() {
					public void listOutBox() {
						if (AccessManager.canDirectAccessOutBox()
								&& AccessManager.canReadOutBox())
							displayWrapperPanel(new OutBoxListPanel(
									requestFactory));
					}
					public void listOutBox(String searchText) {
						if (AccessManager.canDirectAccessOutBox()
								&& AccessManager.canReadOutBox())
							displayWrapperPanel(new OutBoxListPanel(
									requestFactory, searchText));
					}
				}));
		/** 
		 * Handlers for entity Utilisateur 
		 * */

		/* Create Utilisateur Handler */
		registrations.add(eventBus.addHandler(CreateUtilisateurEvent.TYPE,
				new CreateUtilisateurEvent.Handler() {
					public void createNewUtilisateur(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateUtilisateur()
								&& AccessManager.canEditUtilisateur()) {
							UtilisateurFormPanel utilisateurForm = new UtilisateurFormPanel(
									requestFactory, null);
							utilisateurForm.setCloseEvent(closeEvent);
							displayWrapperPanel(utilisateurForm);
						}
					}
				}));

		/* View Utilisateur Handler */
		registrations.add(eventBus.addHandler(ViewUtilisateurEvent.TYPE,
				new ViewUtilisateurEvent.Handler() {
					public void viewUtilisateur(String entityId,
							GwtEvent<?> closeEvent) {
						if (AccessManager.canReadUtilisateur()) {
							UtilisateurFormPanel utilisateurForm = new UtilisateurFormPanel(
									requestFactory, entityId);
							utilisateurForm.setCloseEvent(closeEvent);
							displayWrapperPanel(utilisateurForm);
						}
					}
				}));

		/* List Utilisateur Handler	 */
		registrations.add(eventBus.addHandler(ListUtilisateurEvent.TYPE,
				new ListUtilisateurEvent.Handler() {
					public void listUtilisateur() {
						if (AccessManager.canDirectAccessUtilisateur()
								&& AccessManager.canReadUtilisateur())
							displayWrapperPanel(new UtilisateurListPanel(
									requestFactory));
					}
					public void listUtilisateur(String searchText) {
						if (AccessManager.canDirectAccessUtilisateur()
								&& AccessManager.canReadUtilisateur())
							displayWrapperPanel(new UtilisateurListPanel(
									requestFactory, searchText));
					}
				}));

		/* Create EnvoiSMS Handler */
		registrations.add(eventBus.addHandler(CreateEnvoiSMSEvent.TYPE,
				new CreateEnvoiSMSEvent.Handler() {
					public void createNewEnvoiSMS(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateEnvoiSMS()) {
							EnvoiSMSFormPanel envoismsForm = new EnvoiSMSFormPanel(
									requestFactory, null);
							envoismsForm.setCloseEvent(closeEvent);
							displayWrapperPanel(envoismsForm);
						}
					}
				}));

		/* Create Rapport Handler */
		registrations.add(eventBus.addHandler(CreateRapportEvent.TYPE,
				new CreateRapportEvent.Handler() {
					public void createNewRapport(GwtEvent<?> closeEvent) {
						if (AccessManager.canCreateRapport()) {
							RapportFormPanel rapportForm = new RapportFormPanel(
									requestFactory, null);
							rapportForm.setCloseEvent(closeEvent);
							displayWrapperPanel(rapportForm);
						}
					}
				}));
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		historyCount++;
		String token = event.getValue();
		EntityToken entityToken = TokenHelper.getToken(token);

		if (entityToken != null) {

			if (entityToken.getType().equals(TokenHelper.TK_CLASSIC)) {
				eventBus.fireEvent(new GoHomeEvent());
			}

			if (entityToken.getType().equals("rapport")) {
				if (AccessManager.canCreateRapport()) {
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateRapportEvent());
					}
				}
			}

			if (entityToken.getType().equals("envoiSms")) {
				if (AccessManager.canCreateRapport()) {
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						eventBus.fireEvent(new CreateEnvoiSMSEvent());
					}
				}
			}

			/** actions for Patient */
			if (entityToken.getType().equals("patient")) {
				if (AccessManager.canReadPatient()) {
					/* view for Patient */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewPatientEvent(entityToken
								.getId()));

					/* list for Patient */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessPatient()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListPatientEvent());
							else
								eventBus.fireEvent(new ListPatientEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditPatient()) {
					/* new Patient */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreatePatient())
							eventBus.fireEvent(new CreatePatientEvent());
					}
				}
			}
			/** actions for CasIndex */
			if (entityToken.getType().equals("casindex")) {
				if (AccessManager.canReadCasIndex()) {
					/* view for CasIndex */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCasIndexEvent(entityToken
								.getId()));

					/* list for CasIndex */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessCasIndex()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListCasIndexEvent());
							else
								eventBus.fireEvent(new ListCasIndexEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditCasIndex()) {
					/* new CasIndex */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateCasIndex())
							eventBus.fireEvent(new CreateCasIndexEvent());
					}
				}
			}
			/** actions for CasTuberculose */
			if (entityToken.getType().equals("castuberculose")) {
				if (AccessManager.canReadCasTuberculose()) {
					/* view for CasTuberculose */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCasTuberculoseEvent(
								entityToken.getId()));

					/* list for CasTuberculose */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessCasTuberculose()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListCasTuberculoseEvent());
							else
								eventBus.fireEvent(new ListCasTuberculoseEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditCasTuberculose()) {
					/* new CasTuberculose */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateCasTuberculose())
							eventBus.fireEvent(new CreateCasTuberculoseEvent());
					}
				}
			}
			/** actions for ExamenSerologie */
			if (entityToken.getType().equals("examenserologie")) {
				if (AccessManager.canReadExamenSerologie()) {
					/* view for ExamenSerologie */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewExamenSerologieEvent(
								entityToken.getId()));

					/* list for ExamenSerologie */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessExamenSerologie()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListExamenSerologieEvent());
							else
								eventBus.fireEvent(new ListExamenSerologieEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditExamenSerologie()) {
					/* new ExamenSerologie */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateExamenSerologie())
							eventBus.fireEvent(new CreateExamenSerologieEvent());
					}
				}
			}
			/** actions for ExamenBiologique */
			if (entityToken.getType().equals("examenbiologique")) {
				if (AccessManager.canReadExamenBiologique()) {
					/* view for ExamenBiologique */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewExamenBiologiqueEvent(
								entityToken.getId()));

					/* list for ExamenBiologique */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessExamenBiologique()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListExamenBiologiqueEvent());
							else
								eventBus.fireEvent(new ListExamenBiologiqueEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditExamenBiologique()) {
					/* new ExamenBiologique */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateExamenBiologique())
							eventBus.fireEvent(new CreateExamenBiologiqueEvent());
					}
				}
			}
			/** actions for ExamenMicroscopie */
			if (entityToken.getType().equals("examenmicroscopie")) {
				if (AccessManager.canReadExamenMicroscopie()) {
					/* view for ExamenMicroscopie */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewExamenMicroscopieEvent(
								entityToken.getId()));

					/* list for ExamenMicroscopie */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessExamenMicroscopie()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListExamenMicroscopieEvent());
							else
								eventBus.fireEvent(new ListExamenMicroscopieEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditExamenMicroscopie()) {
					/* new ExamenMicroscopie */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateExamenMicroscopie())
							eventBus.fireEvent(new CreateExamenMicroscopieEvent());
					}
				}
			}
			/** actions for ExamenATB */
			if (entityToken.getType().equals("examenatb")) {
				if (AccessManager.canReadExamenATB()) {
					/* view for ExamenATB */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewExamenATBEvent(entityToken
								.getId()));

					/* list for ExamenATB */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessExamenATB()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListExamenATBEvent());
							else
								eventBus.fireEvent(new ListExamenATBEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditExamenATB()) {
					/* new ExamenATB */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateExamenATB())
							eventBus.fireEvent(new CreateExamenATBEvent());
					}
				}
			}
			/** actions for PriseMedicamenteuse */
			if (entityToken.getType().equals("prisemedicamenteuse")) {
				if (AccessManager.canReadPriseMedicamenteuse()) {
					/* view for PriseMedicamenteuse */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewPriseMedicamenteuseEvent(
								entityToken.getId()));

					/* list for PriseMedicamenteuse */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessPriseMedicamenteuse()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListPriseMedicamenteuseEvent());
							else
								eventBus.fireEvent(new ListPriseMedicamenteuseEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditPriseMedicamenteuse()) {
					/* new PriseMedicamenteuse */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreatePriseMedicamenteuse())
							eventBus.fireEvent(new CreatePriseMedicamenteuseEvent());
					}
				}
			}
			/** actions for RendezVous */
			if (entityToken.getType().equals("rendezvous")) {
				if (AccessManager.canReadRendezVous()) {
					/* view for RendezVous */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewRendezVousEvent(entityToken
								.getId()));

					/* list for RendezVous */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessRendezVous()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListRendezVousEvent());
							else
								eventBus.fireEvent(new ListRendezVousEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditRendezVous()) {
					/* new RendezVous */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateRendezVous())
							eventBus.fireEvent(new CreateRendezVousEvent());
					}
				}
			}
			/** actions for TransfertReference */
			if (entityToken.getType().equals("transfertreference")) {
				if (AccessManager.canReadTransfertReference()) {
					/* view for TransfertReference */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewTransfertReferenceEvent(
								entityToken.getId()));

					/* list for TransfertReference */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessTransfertReference()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListTransfertReferenceEvent());
							else
								eventBus.fireEvent(new ListTransfertReferenceEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditTransfertReference()) {
					/* new TransfertReference */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateTransfertReference())
							eventBus.fireEvent(new CreateTransfertReferenceEvent());
					}
				}
			}
			/** actions for Lot */
			if (entityToken.getType().equals("lot")) {
				if (AccessManager.canReadLot()) {
					/* view for Lot */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewLotEvent(entityToken.getId()));

					/* list for Lot */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessLot()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListLotEvent());
							else
								eventBus.fireEvent(new ListLotEvent(entityToken
										.getId()));
						}
					}
				}
				if (AccessManager.canEditLot()) {
					/* new Lot */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateLot())
							eventBus.fireEvent(new CreateLotEvent());
					}
				}
			}
			/** actions for HorsUsage */
			if (entityToken.getType().equals("horsusage")) {
				if (AccessManager.canReadHorsUsage()) {
					/* view for HorsUsage */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewHorsUsageEvent(entityToken
								.getId()));

					/* list for HorsUsage */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessHorsUsage()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListHorsUsageEvent());
							else
								eventBus.fireEvent(new ListHorsUsageEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditHorsUsage()) {
					/* new HorsUsage */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateHorsUsage())
							eventBus.fireEvent(new CreateHorsUsageEvent());
					}
				}
			}
			/** actions for EntreeLot */
			if (entityToken.getType().equals("entreelot")) {
				if (AccessManager.canReadEntreeLot()) {
					/* view for EntreeLot */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewEntreeLotEvent(entityToken
								.getId()));

					/* list for EntreeLot */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessEntreeLot()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListEntreeLotEvent());
							else
								eventBus.fireEvent(new ListEntreeLotEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditEntreeLot()) {
					/* new EntreeLot */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateEntreeLot())
							eventBus.fireEvent(new CreateEntreeLotEvent());
					}
				}
			}
			/** actions for SortieLot */
			if (entityToken.getType().equals("sortielot")) {
				if (AccessManager.canReadSortieLot()) {
					/* view for SortieLot */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewSortieLotEvent(entityToken
								.getId()));

					/* list for SortieLot */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessSortieLot()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListSortieLotEvent());
							else
								eventBus.fireEvent(new ListSortieLotEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditSortieLot()) {
					/* new SortieLot */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateSortieLot())
							eventBus.fireEvent(new CreateSortieLotEvent());
					}
				}
			}
			/** actions for Commande */
			if (entityToken.getType().equals("commande")) {
				if (AccessManager.canReadCommande()) {
					/* view for Commande */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCommandeEvent(entityToken
								.getId()));

					/* list for Commande */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessCommande()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListCommandeEvent());
							else
								eventBus.fireEvent(new ListCommandeEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditCommande()) {
					/* new Commande */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateCommande())
							eventBus.fireEvent(new CreateCommandeEvent());
					}
				}
			}
			/** actions for DetailCommandeMedicament */
			if (entityToken.getType().equals("detailcommandemedicament")) {
				if (AccessManager.canReadDetailCommandeMedicament()) {
					/* view for DetailCommandeMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailCommandeMedicamentEvent(
								entityToken.getId()));

					/* list for DetailCommandeMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager
								.canDirectAccessDetailCommandeMedicament()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailCommandeMedicamentEvent());
							else
								eventBus.fireEvent(new ListDetailCommandeMedicamentEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailCommandeMedicament()) {
					/* new DetailCommandeMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailCommandeMedicament())
							eventBus.fireEvent(new CreateDetailCommandeMedicamentEvent());
					}
				}
			}
			/** actions for DetailCommandeIntrant */
			if (entityToken.getType().equals("detailcommandeintrant")) {
				if (AccessManager.canReadDetailCommandeIntrant()) {
					/* view for DetailCommandeIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailCommandeIntrantEvent(
								entityToken.getId()));

					/* list for DetailCommandeIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager
								.canDirectAccessDetailCommandeIntrant()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailCommandeIntrantEvent());
							else
								eventBus.fireEvent(new ListDetailCommandeIntrantEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailCommandeIntrant()) {
					/* new DetailCommandeIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailCommandeIntrant())
							eventBus.fireEvent(new CreateDetailCommandeIntrantEvent());
					}
				}
			}
			/** actions for Reception */
			if (entityToken.getType().equals("reception")) {
				if (AccessManager.canReadReception()) {
					/* view for Reception */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewReceptionEvent(entityToken
								.getId()));

					/* list for Reception */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessReception()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListReceptionEvent());
							else
								eventBus.fireEvent(new ListReceptionEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditReception()) {
					/* new Reception */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateReception())
							eventBus.fireEvent(new CreateReceptionEvent());
					}
				}
			}
			/** actions for DetailReceptionMedicament */
			if (entityToken.getType().equals("detailreceptionmedicament")) {
				if (AccessManager.canReadDetailReceptionMedicament()) {
					/* view for DetailReceptionMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailReceptionMedicamentEvent(
								entityToken.getId()));

					/* list for DetailReceptionMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager
								.canDirectAccessDetailReceptionMedicament()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailReceptionMedicamentEvent());
							else
								eventBus.fireEvent(new ListDetailReceptionMedicamentEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailReceptionMedicament()) {
					/* new DetailReceptionMedicament */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailReceptionMedicament())
							eventBus.fireEvent(new CreateDetailReceptionMedicamentEvent());
					}
				}
			}
			/** actions for DetailReceptionIntrant */
			if (entityToken.getType().equals("detailreceptionintrant")) {
				if (AccessManager.canReadDetailReceptionIntrant()) {
					/* view for DetailReceptionIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailReceptionIntrantEvent(
								entityToken.getId()));

					/* list for DetailReceptionIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager
								.canDirectAccessDetailReceptionIntrant()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailReceptionIntrantEvent());
							else
								eventBus.fireEvent(new ListDetailReceptionIntrantEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailReceptionIntrant()) {
					/* new DetailReceptionIntrant */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailReceptionIntrant())
							eventBus.fireEvent(new CreateDetailReceptionIntrantEvent());
					}
				}
			}
			/** actions for Ravitaillement */
			if (entityToken.getType().equals("ravitaillement")) {
				if (AccessManager.canReadRavitaillement()) {
					/* view for Ravitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewRavitaillementEvent(
								entityToken.getId()));

					/* list for Ravitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessRavitaillement()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListRavitaillementEvent());
							else
								eventBus.fireEvent(new ListRavitaillementEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditRavitaillement()) {
					/* new Ravitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateRavitaillement())
							eventBus.fireEvent(new CreateRavitaillementEvent());
					}
				}
			}
			/** actions for DetailRavitaillement */
			if (entityToken.getType().equals("detailravitaillement")) {
				if (AccessManager.canReadDetailRavitaillement()) {
					/* view for DetailRavitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailRavitaillementEvent(
								entityToken.getId()));

					/* list for DetailRavitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessDetailRavitaillement()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailRavitaillementEvent());
							else
								eventBus.fireEvent(new ListDetailRavitaillementEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailRavitaillement()) {
					/* new DetailRavitaillement */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailRavitaillement())
							eventBus.fireEvent(new CreateDetailRavitaillementEvent());
					}
				}
			}
			/** actions for Inventaire */
			if (entityToken.getType().equals("inventaire")) {
				if (AccessManager.canReadInventaire()) {
					/* view for Inventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewInventaireEvent(entityToken
								.getId()));

					/* list for Inventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessInventaire()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListInventaireEvent());
							else
								eventBus.fireEvent(new ListInventaireEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditInventaire()) {
					/* new Inventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateInventaire())
							eventBus.fireEvent(new CreateInventaireEvent());
					}
				}
			}
			/** actions for DetailInventaire */
			if (entityToken.getType().equals("detailinventaire")) {
				if (AccessManager.canReadDetailInventaire()) {
					/* view for DetailInventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDetailInventaireEvent(
								entityToken.getId()));

					/* list for DetailInventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessDetailInventaire()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDetailInventaireEvent());
							else
								eventBus.fireEvent(new ListDetailInventaireEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDetailInventaire()) {
					/* new DetailInventaire */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDetailInventaire())
							eventBus.fireEvent(new CreateDetailInventaireEvent());
					}
				}
			}
			/** actions for Personnel */
			if (entityToken.getType().equals("personnel")) {
				if (AccessManager.canReadPersonnel()) {
					/* view for Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewPersonnelEvent(entityToken
								.getId()));

					/* list for Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessPersonnel()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListPersonnelEvent());
							else
								eventBus.fireEvent(new ListPersonnelEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditPersonnel()) {
					/* new Personnel */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreatePersonnel())
							eventBus.fireEvent(new CreatePersonnelEvent());
					}
				}
			}
			/** actions for DepartPersonnel */
			if (entityToken.getType().equals("departpersonnel")) {
				if (AccessManager.canReadDepartPersonnel()) {
					/* view for DepartPersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDepartPersonnelEvent(
								entityToken.getId()));

					/* list for DepartPersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessDepartPersonnel()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDepartPersonnelEvent());
							else
								eventBus.fireEvent(new ListDepartPersonnelEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDepartPersonnel()) {
					/* new DepartPersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDepartPersonnel())
							eventBus.fireEvent(new CreateDepartPersonnelEvent());
					}
				}
			}
			/** actions for ArriveePersonnel */
			if (entityToken.getType().equals("arriveepersonnel")) {
				if (AccessManager.canReadArriveePersonnel()) {
					/* view for ArriveePersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewArriveePersonnelEvent(
								entityToken.getId()));

					/* list for ArriveePersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessArriveePersonnel()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListArriveePersonnelEvent());
							else
								eventBus.fireEvent(new ListArriveePersonnelEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditArriveePersonnel()) {
					/* new ArriveePersonnel */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateArriveePersonnel())
							eventBus.fireEvent(new CreateArriveePersonnelEvent());
					}
				}
			}
			/** actions for Region */
			if (entityToken.getType().equals("region")) {
				if (AccessManager.canReadRegion()) {
					/* view for Region */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewRegionEvent(entityToken
								.getId()));

					/* list for Region */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessRegion()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListRegionEvent());
							else
								eventBus.fireEvent(new ListRegionEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditRegion()) {
					/* new Region */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateRegion())
							eventBus.fireEvent(new CreateRegionEvent());
					}
				}
			}
			/** actions for DistrictSante */
			if (entityToken.getType().equals("districtsante")) {
				if (AccessManager.canReadDistrictSante()) {
					/* view for DistrictSante */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewDistrictSanteEvent(
								entityToken.getId()));

					/* list for DistrictSante */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessDistrictSante()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListDistrictSanteEvent());
							else
								eventBus.fireEvent(new ListDistrictSanteEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditDistrictSante()) {
					/* new DistrictSante */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateDistrictSante())
							eventBus.fireEvent(new CreateDistrictSanteEvent());
					}
				}
			}
			/** actions for CentreDiagTrait */
			if (entityToken.getType().equals("centrediagtrait")) {
				if (AccessManager.canReadCentreDiagTrait()) {
					/* view for CentreDiagTrait */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCentreDiagTraitEvent(
								entityToken.getId()));

					/* list for CentreDiagTrait */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessCentreDiagTrait()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListCentreDiagTraitEvent());
							else
								eventBus.fireEvent(new ListCentreDiagTraitEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditCentreDiagTrait()) {
					/* new CentreDiagTrait */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateCentreDiagTrait())
							eventBus.fireEvent(new CreateCentreDiagTraitEvent());
					}
				}
			}
			/** actions for LaboratoireReference */
			if (entityToken.getType().equals("laboratoirereference")) {
				if (AccessManager.canReadLaboratoireReference()) {
					/* view for LaboratoireReference */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewLaboratoireReferenceEvent(
								entityToken.getId()));

					/* list for LaboratoireReference */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessLaboratoireReference()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListLaboratoireReferenceEvent());
							else
								eventBus.fireEvent(new ListLaboratoireReferenceEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditLaboratoireReference()) {
					/* new LaboratoireReference */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateLaboratoireReference())
							eventBus.fireEvent(new CreateLaboratoireReferenceEvent());
					}
				}
			}
			/** actions for LieuDit */
			if (entityToken.getType().equals("lieudit")) {
				if (AccessManager.canReadLieuDit()) {
					/* view for LieuDit */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewLieuDitEvent(entityToken
								.getId()));

					/* list for LieuDit */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessLieuDit()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListLieuDitEvent());
							else
								eventBus.fireEvent(new ListLieuDitEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditLieuDit()) {
					/* new LieuDit */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateLieuDit())
							eventBus.fireEvent(new CreateLieuDitEvent());
					}
				}
			}
			/** actions for Regime */
			if (entityToken.getType().equals("regime")) {
				if (AccessManager.canReadRegime()) {
					/* view for Regime */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewRegimeEvent(entityToken
								.getId()));

					/* list for Regime */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessRegime()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListRegimeEvent());
							else
								eventBus.fireEvent(new ListRegimeEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditRegime()) {
					/* new Regime */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateRegime())
							eventBus.fireEvent(new CreateRegimeEvent());
					}
				}
			}
			/** actions for PriseMedicamentRegime */
			if (entityToken.getType().equals("prisemedicamentregime")) {
				if (AccessManager.canReadPriseMedicamentRegime()) {
					/* view for PriseMedicamentRegime */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewPriseMedicamentRegimeEvent(
								entityToken.getId()));

					/* list for PriseMedicamentRegime */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager
								.canDirectAccessPriseMedicamentRegime()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListPriseMedicamentRegimeEvent());
							else
								eventBus.fireEvent(new ListPriseMedicamentRegimeEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditPriseMedicamentRegime()) {
					/* new PriseMedicamentRegime */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreatePriseMedicamentRegime())
							eventBus.fireEvent(new CreatePriseMedicamentRegimeEvent());
					}
				}
			}
			/** actions for Medicament */
			if (entityToken.getType().equals("medicament")) {
				if (AccessManager.canReadMedicament()) {
					/* view for Medicament */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewMedicamentEvent(entityToken
								.getId()));

					/* list for Medicament */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessMedicament()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListMedicamentEvent());
							else
								eventBus.fireEvent(new ListMedicamentEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditMedicament()) {
					/* new Medicament */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateMedicament())
							eventBus.fireEvent(new CreateMedicamentEvent());
					}
				}
			}
			/** actions for Intrant */
			if (entityToken.getType().equals("intrant")) {
				if (AccessManager.canReadIntrant()) {
					/* view for Intrant */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewIntrantEvent(entityToken
								.getId()));

					/* list for Intrant */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessIntrant()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListIntrantEvent());
							else
								eventBus.fireEvent(new ListIntrantEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditIntrant()) {
					/* new Intrant */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateIntrant())
							eventBus.fireEvent(new CreateIntrantEvent());
					}
				}
			}
			/** actions for Formation */
			if (entityToken.getType().equals("formation")) {
				if (AccessManager.canReadFormation()) {
					/* view for Formation */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewFormationEvent(entityToken
								.getId()));

					/* list for Formation */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessFormation()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListFormationEvent());
							else
								eventBus.fireEvent(new ListFormationEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditFormation()) {
					/* new Formation */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateFormation())
							eventBus.fireEvent(new CreateFormationEvent());
					}
				}
			}
			/** actions for CandidatureFormation */
			if (entityToken.getType().equals("candidatureformation")) {
				if (AccessManager.canReadCandidatureFormation()) {
					/* view for CandidatureFormation */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewCandidatureFormationEvent(
								entityToken.getId()));

					/* list for CandidatureFormation */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessCandidatureFormation()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListCandidatureFormationEvent());
							else
								eventBus.fireEvent(new ListCandidatureFormationEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditCandidatureFormation()) {
					/* new CandidatureFormation */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateCandidatureFormation())
							eventBus.fireEvent(new CreateCandidatureFormationEvent());
					}
				}
			}
			/** actions for Qualification */
			if (entityToken.getType().equals("qualification")) {
				if (AccessManager.canReadQualification()) {
					/* view for Qualification */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewQualificationEvent(
								entityToken.getId()));

					/* list for Qualification */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessQualification()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListQualificationEvent());
							else
								eventBus.fireEvent(new ListQualificationEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditQualification()) {
					/* new Qualification */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateQualification())
							eventBus.fireEvent(new CreateQualificationEvent());
					}
				}
			}
			/** actions for Tutoriel */
			if (entityToken.getType().equals("tutoriel")) {
				if (AccessManager.canReadTutoriel()) {
					/* view for Tutoriel */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewTutorielEvent(entityToken
								.getId()));

					/* list for Tutoriel */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessTutoriel()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListTutorielEvent());
							else
								eventBus.fireEvent(new ListTutorielEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditTutoriel()) {
					/* new Tutoriel */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateTutoriel())
							eventBus.fireEvent(new CreateTutorielEvent());
					}
				}
			}
			/** actions for SmsPredefini */
			if (entityToken.getType().equals("smspredefini")) {
				if (AccessManager.canReadSmsPredefini()) {
					/* view for SmsPredefini */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewSmsPredefiniEvent(
								entityToken.getId()));

					/* list for SmsPredefini */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessSmsPredefini()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListSmsPredefiniEvent());
							else
								eventBus.fireEvent(new ListSmsPredefiniEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditSmsPredefini()) {
					/* new SmsPredefini */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateSmsPredefini())
							eventBus.fireEvent(new CreateSmsPredefiniEvent());
					}
				}
			}
			/** actions for OutBox */
			if (entityToken.getType().equals("outbox")) {
				if (AccessManager.canReadOutBox()) {
					/* view for OutBox */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewOutBoxEvent(entityToken
								.getId()));

					/* list for OutBox */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessOutBox()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListOutBoxEvent());
							else
								eventBus.fireEvent(new ListOutBoxEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditOutBox()) {
					/* new OutBox */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateOutBox())
							eventBus.fireEvent(new CreateOutBoxEvent());
					}
				}
			}
			/** actions for Utilisateur */
			if (entityToken.getType().equals("utilisateur")) {
				if (AccessManager.canReadUtilisateur()) {
					/* view for Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_VIEW))
						eventBus.fireEvent(new ViewUtilisateurEvent(entityToken
								.getId()));

					/* list for Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_LIST)) {
						if (AccessManager.canDirectAccessUtilisateur()) {
							if (entityToken.getId() == null
									&& !entityToken.getId().isEmpty())
								eventBus.fireEvent(new ListUtilisateurEvent());
							else
								eventBus.fireEvent(new ListUtilisateurEvent(
										entityToken.getId()));
						}
					}
				}
				if (AccessManager.canEditUtilisateur()) {
					/* new Utilisateur */
					if (entityToken.getAction().equals(TokenHelper.TK_NEW)) {
						if (AccessManager.canCreateUtilisateur())
							eventBus.fireEvent(new CreateUtilisateurEvent());
					}
				}
			}

		} else
			eventBus.fireEvent(new GoHomeEvent());
	}

}
