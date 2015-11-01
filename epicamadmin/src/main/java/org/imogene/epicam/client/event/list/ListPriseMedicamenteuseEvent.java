package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of PriseMedicamenteuse form entries
 * @author MEDES-IMPS
 */
public class ListPriseMedicamenteuseEvent
		extends
			GwtEvent<ListPriseMedicamenteuseEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listPriseMedicamenteuse();
		void listPriseMedicamenteuse(String searchText);
	}

	public ListPriseMedicamenteuseEvent() {
	}

	public ListPriseMedicamenteuseEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listPriseMedicamenteuse();
		else
			handler.listPriseMedicamenteuse(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
