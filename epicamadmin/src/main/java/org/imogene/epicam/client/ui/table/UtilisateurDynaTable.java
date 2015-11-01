package org.imogene.epicam.client.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.imogene.epicam.client.AccessManager;
import org.imogene.epicam.client.event.create.CreateUtilisateurEvent;
import org.imogene.epicam.client.event.list.ListUtilisateurEvent;
import org.imogene.epicam.client.event.view.ViewUtilisateurEvent;
import org.imogene.epicam.client.EpicamRenderer;
import org.imogene.epicam.client.ui.filter.UtilisateurFilterPanel;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.UtilisateurProxy;
import org.imogene.epicam.shared.proxy.LocalizedTextProxy;
import org.imogene.epicam.shared.request.UtilisateurRequest;
import org.imogene.epicam.client.i18n.NLS;
import org.imogene.web.client.event.SelectionChangedInTableEvent;
import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.table.ImogBeanDataProvider;
import org.imogene.web.client.ui.table.ImogColumn;
import org.imogene.web.client.ui.table.ImogDynaTable;
import org.imogene.web.client.ui.table.filter.ImogFilterPanel;
import org.imogene.web.client.util.BooleanUtil;
import org.imogene.web.client.util.DateUtil;
import org.imogene.web.client.util.TokenHelper;
import org.imogene.epicam.shared.constants.EpicamBirtConstants;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PushButton;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.imogene.admin.client.i18n.AdminNLS;
import org.imogene.web.client.util.ProfileUtil;

/**
 * Composite that displays the list of Utilisateur entries
 * @author MEDES-IMPS
 */
public class UtilisateurDynaTable extends ImogDynaTable<UtilisateurProxy> {

	private List<HandlerRegistration> registrations = new ArrayList<HandlerRegistration>();

	private PushButton deleteButton;

	public UtilisateurDynaTable(EpicamRequestFactory requestFactory,
			ImogBeanDataProvider<UtilisateurProxy> provider,
			boolean checkBoxesVisible) {
		super(requestFactory, provider, checkBoxesVisible);
	}

	public ImogFilterPanel getFilterPanel() {
		ImogFilterPanel filterPanel = new UtilisateurFilterPanel();
		super.configureFilterPanel(filterPanel);
		return filterPanel;
	}

	/**
	 * 
	 */
	@Override
	protected void setColumns() {

		if (AccessManager.canReadUtilisateurIdentification()) {
			Column<UtilisateurProxy, String> nomColumn = new NomColumn();
			nomColumn.setSortable(true);
			table.addColumn(nomColumn, NLS.constants()
					.utilisateur_field_s_nom());
		}
		if (AccessManager.canReadUtilisateurIdentification()) {
			Column<UtilisateurProxy, String> professionColumn = new ProfessionColumn();
			professionColumn.setSortable(true);
			table.addColumn(professionColumn, NLS.constants()
					.utilisateur_field_s_profession());
		}
		if (AccessManager.canReadUtilisateurIdentification()) {
			Column<UtilisateurProxy, String> dateNaissanceColumn = new DateNaissanceColumn();
			dateNaissanceColumn.setSortable(true);
			table.addColumn(dateNaissanceColumn, NLS.constants()
					.utilisateur_field_s_dateNaissance());
		}

		if (ProfileUtil.isAdmin()) {
			Column<UtilisateurProxy, String> loginColumn = new LoginColumn();
			loginColumn.setSortable(true);
			table.addColumn(loginColumn, AdminNLS.constants()
					.imogActor_field_s_login());
		}
	}

	@Override
	protected GwtEvent<?> getViewEvent(UtilisateurProxy value) {
		History.newItem(TokenHelper.TK_VIEW + "/utilisateur/" + value.getId(),
				true);
		return null;
	}

	@Override
	protected String getDefaultSortProperty() {
		return "modified";
	}

	@Override
	protected boolean getDefaultSortPropertyOrder() {
		return false;
	}

	/**
	 * Creates the Create action command for the entity
	 * @return the create command
	 */
	public Command getCreateCommand() {

		if (AccessManager.canCreateUtilisateur()
				&& AccessManager.canEditUtilisateur()) {
			Command command = new Command() {
				public void execute() {
					History.newItem(TokenHelper.TK_NEW + "/utilisateur/", true);
				}
			};
			return command;
		} else
			return null;
	}

	/**
	 * Creates the Delete action command for the entity
	 * @return the delete command
	 */
	public PushButton getDeleteButton() {

		if (AccessManager.canDeleteUtilisateur()
				&& AccessManager.canEditUtilisateur()) {
			deleteButton = new PushButton(BaseNLS.constants().button_delete());
			deleteButton.setStyleName(imogResources.imogStyle().imogButton());
			deleteButton.addStyleName("Dynatable-Button");
			deleteButton.setVisible(false);
			return deleteButton;
		}

		return null;
	}

	/**
	 * Creates the Handlers linked to the delete button
	 */
	private void setDeleteButtonHandlers() {

		if (AccessManager.canDeleteUtilisateur()
				&& AccessManager.canEditUtilisateur()) {

			// Click handler
			registrations.add(deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					Set<UtilisateurProxy> selectedEntities = selectionModel
							.getSelectedSet();

					int count = selectedEntities.size();
					if (count > 0) {

						EpicamRenderer renderer = EpicamRenderer.get();

						StringBuffer msg = new StringBuffer();
						msg.append(BaseNLS.constants()
								.confirmation_delete_several1()
								+ " "
								+ NLS.constants().utilisateur_name()
								+ " "
								+ BaseNLS.constants()
										.confirmation_delete_several2() + ": ");
						int i = 0;
						for (UtilisateurProxy entity : selectedEntities) {
							if (count == 1 || i == count - 1)
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "' ?");
							else
								msg.append("'"
										+ renderer.getDisplayValue(entity)
										+ "', ");
							i = i + 1;
						}

						boolean toDelete = Window.confirm(msg.toString());
						if (toDelete) {

							Request<Void> deleteRequest = getUtilisateurRequest()
									.delete(selectedEntities);
							deleteRequest.fire(new Receiver<Void>() {
								@Override
								public void onSuccess(Void response) {
									//Window.alert("The selected Utilisateur entries have been deleted");
									requestFactory.getEventBus().fireEvent(
											new ListUtilisateurEvent());
								}

								@Override
								public void onFailure(ServerFailure error) {
									Window.alert("Error deleting the Utilisateur entries");
									super.onFailure(error);
								}
							});
						}
					}

				}
			}));

			// Selection changed handler	
			registrations.add(requestFactory.getEventBus().addHandler(
					SelectionChangedInTableEvent.TYPE,
					new SelectionChangedInTableEvent.Handler() {
						@Override
						public void noticeSelectionChange(int selectedItems) {
							if (selectedItems > 0)
								deleteButton.setVisible(true);
							else
								deleteButton.setVisible(false);
						}
					}));
		}
	}

	/**
	 * Creates the action command that enables to export the Utilisateur
	 * entries in a csv file
	 * @return the command
	 */
	public Command getCsvExportButton() {

		if (AccessManager.canExportUtilisateur()) {

			Command command = new Command() {
				public void execute() {

					String url = GWT.getHostPageBaseURL()
							+ EpicamBirtConstants.USR_CSV_KEY + "?"
							+ EpicamBirtConstants.REPORT_NAME
							+ "=utilisateur_csv" + "&"
							+ EpicamBirtConstants.REPORT_LOCALE + "="
							+ NLS.constants().locale() + "&"
							+ EpicamBirtConstants.REPORT_FORMAT + "="
							+ EpicamBirtConstants.CSV;

					if (beanDataProvider.getSearchCriterions() != null)
						url = url + getDataProviderCriteria();

					Window.open(url, "_blank", "");
				}
			};
			return command;

		} else
			return null;
	}

	private UtilisateurRequest getUtilisateurRequest() {
		EpicamRequestFactory epicamRequestFactory = (EpicamRequestFactory) requestFactory;
		return epicamRequestFactory.utilisateurRequest();
	}

	@Override
	protected void onUnload() {
		for (HandlerRegistration r : registrations)
			r.removeHandler();
		registrations.clear();
		super.onUnload();
	}

	@Override
	protected void onLoad() {
		setDeleteButtonHandlers();
		super.onLoad();
	}

	/**
	 * --------------------- * Internal classes * ----------------------
	 */

	/**
	 * Column for field Nom
	 * @author MEDES-IMPS
	 */
	private class NomColumn extends ImogColumn<UtilisateurProxy, String> {

		public NomColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(UtilisateurProxy object) {
			String value = null;
			if (object != null) {
				if (object.getNom() == null)
					value = "";
				else
					value = object.getNom();
			}
			return value;
		}

		public String getPropertyName() {
			return "nom";
		}
	}

	/**
	 * Column for field Profession
	 * @author MEDES-IMPS
	 */
	private class ProfessionColumn extends ImogColumn<UtilisateurProxy, String> {

		public ProfessionColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(UtilisateurProxy object) {
			String value = null;
			if (object != null) {
				if (object.getProfession() == null)
					value = "";
				else
					value = object.getProfession();
			}
			return value;
		}

		public String getPropertyName() {
			return "profession";
		}
	}

	/**
	 * Column for field DateNaissance
	 * @author MEDES-IMPS
	 */
	private class DateNaissanceColumn
			extends
				ImogColumn<UtilisateurProxy, String> {

		public DateNaissanceColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(UtilisateurProxy object) {
			String value = null;
			if (object != null) {
				if (object.getDateNaissance() == null)
					value = "";
				else
					value = DateUtil.getFormatedDate(object.getDateNaissance());
			}
			return value;
		}

		public String getPropertyName() {
			return "dateNaissance";
		}
	}

	/**
	 * Column for field Login
	 * @author MEDES-IMPS
	 */
	private class LoginColumn extends ImogColumn<UtilisateurProxy, String> {

		public LoginColumn() {
			super(new TextCell());
		}

		@Override
		public String getValue(UtilisateurProxy object) {
			String value = null;
			if (object.getLogin() == null)
				value = "";
			else
				value = object.getLogin();

			return value;
		}

		public String getPropertyName() {
			return "login";
		}
	}

}
