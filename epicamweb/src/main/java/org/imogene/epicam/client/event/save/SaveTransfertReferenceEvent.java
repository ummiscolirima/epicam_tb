package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.TransfertReferenceProxy;

/**
 * Event that is fired after that a TransfertReference form has been saved
 * @author MEDES-IMPS
 */
public class SaveTransfertReferenceEvent
		extends
			GwtEvent<SaveTransfertReferenceEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveTransfertReference(TransfertReferenceProxy value);
		void saveTransfertReference(TransfertReferenceProxy value,
				String initField);
	}

	private final TransfertReferenceProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveTransfertReferenceEvent(TransfertReferenceProxy value) {
		this(value, null);
	}

	public SaveTransfertReferenceEvent(TransfertReferenceProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveTransfertReference(value);
		else
			handler.saveTransfertReference(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
