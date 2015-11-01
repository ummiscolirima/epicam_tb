package org.imogene.epicam.client.event.view;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.client.event.list.ListDetailReceptionMedicamentEvent;

/**
 * Event that is fired in order to display a DetailReceptionMedicament form in view mode
 * @author MEDES-IMPS
 */
public class ViewDetailReceptionMedicamentEvent
		extends
			GwtEvent<ViewDetailReceptionMedicamentEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void viewDetailReceptionMedicament(String entityId,
				GwtEvent<?> closeEvent);
	}

	private final String entityId;

	private final GwtEvent<?> closeEvent;

	public ViewDetailReceptionMedicamentEvent(String entityId) {
		this(entityId, new ListDetailReceptionMedicamentEvent());
	}

	public ViewDetailReceptionMedicamentEvent(String entityId,
			GwtEvent<?> closeEvent) {
		this.entityId = entityId;
		this.closeEvent = closeEvent;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.viewDetailReceptionMedicament(entityId, closeEvent);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
