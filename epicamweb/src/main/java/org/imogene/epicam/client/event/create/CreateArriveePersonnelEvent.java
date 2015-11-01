package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListArriveePersonnelEvent;

/**
 * Event that is fired in order to display a ArriveePersonnel form in creation mode
 * @author MEDES-IMPS
 */
public class CreateArriveePersonnelEvent
		extends
			GwtEvent<CreateArriveePersonnelEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewArriveePersonnel(GwtEvent<?> closeEvent);
	}

	public CreateArriveePersonnelEvent() {
		this(new ListArriveePersonnelEvent());
	}

	public CreateArriveePersonnelEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewArriveePersonnel(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
