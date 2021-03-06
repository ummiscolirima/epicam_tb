package org.imogene.epicam.client.event.save;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

import org.imogene.epicam.shared.proxy.ExamenBiologiqueProxy;

/**
 * Event that is fired after that a ExamenBiologique form has been saved
 * @author MEDES-IMPS
 */
public class SaveExamenBiologiqueEvent
		extends
			GwtEvent<SaveExamenBiologiqueEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void saveExamenBiologique(ExamenBiologiqueProxy value);
		void saveExamenBiologique(ExamenBiologiqueProxy value, String initField);
	}

	private final ExamenBiologiqueProxy value;
	/* the relation field that initiated the creation or update of the value */
	private final String initField;

	public SaveExamenBiologiqueEvent(ExamenBiologiqueProxy value) {
		this(value, null);
	}

	public SaveExamenBiologiqueEvent(ExamenBiologiqueProxy value,
			String initField) {
		this.value = value;
		this.initField = initField;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (initField == null)
			handler.saveExamenBiologique(value);
		else
			handler.saveExamenBiologique(value, initField);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
