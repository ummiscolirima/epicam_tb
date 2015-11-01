package org.imogene.epicam.client.event.list;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that is fired in order to display the list of HorsUsage form entries
 * @author MEDES-IMPS
 */
public class ListHorsUsageEvent extends GwtEvent<ListHorsUsageEvent.Handler> {

	public static final Type<Handler> TYPE = new Type<Handler>();

	private String searchText = null;

	public interface Handler extends EventHandler {
		void listHorsUsage();
		void listHorsUsage(String searchText);
	}

	public ListHorsUsageEvent() {
	}

	public ListHorsUsageEvent(String searchText) {
		this.searchText = searchText;
	}

	@Override
	protected void dispatch(Handler handler) {
		if (searchText == null)
			handler.listHorsUsage();
		else
			handler.listHorsUsage(searchText);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}
}
