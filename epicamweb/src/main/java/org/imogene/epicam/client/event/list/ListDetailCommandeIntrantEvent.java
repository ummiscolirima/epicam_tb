package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of DetailCommandeIntrant form entries
 * @author MEDES-IMPS
 */
public class ListDetailCommandeIntrantEvent
		extends
			GwtEvent<ListDetailCommandeIntrantEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listDetailCommandeIntrant();
		void listDetailCommandeIntrant(String searchText);
	}

	public ListDetailCommandeIntrantEvent() {
	}

	public ListDetailCommandeIntrantEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listDetailCommandeIntrant();
		else
			handler.listDetailCommandeIntrant(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
