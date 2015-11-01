package org.imogene.epicam.client.event.create;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListRavitaillementEvent;

/**
 * Event that is fired in order to display a Ravitaillement form in creation mode
 * @author MEDES-IMPS
 */
public class CreateRavitaillementEvent
		extends
			GwtEvent<CreateRavitaillementEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private final GwtEvent<?> closeEvent;

	public interface Handler extends EventHandler {
		void createNewRavitaillement(GwtEvent<?> closeEvent);
	}

	public CreateRavitaillementEvent() {
		this(new ListRavitaillementEvent());
	}

	public CreateRavitaillementEvent(GwtEvent<?> closeEvent) {
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.createNewRavitaillement(closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

}
