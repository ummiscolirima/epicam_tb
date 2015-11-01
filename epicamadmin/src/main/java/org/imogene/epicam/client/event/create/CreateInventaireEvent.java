package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListInventaireEvent;

/**
 * Event that is fired in order to display a Inventaire form in creation mode
 * @author MEDES-IMPS
 */
public class CreateInventaireEvent
		extends
			GwtEvent<CreateInventaireEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewInventaire(GwtEvent<?> closeEvent);
	}

	public CreateInventaireEvent() {
		this(new ListInventaireEvent());
	}

	public CreateInventaireEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewInventaire(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
