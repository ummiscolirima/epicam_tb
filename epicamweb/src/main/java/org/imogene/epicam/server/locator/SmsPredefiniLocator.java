package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.SmsPredefini;
import org.imogene.epicam.server.handler.SmsPredefiniHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate SmsPredefini beans 
 * @author Medes-IMPS
 */
public class SmsPredefiniLocator extends Locator<SmsPredefini, String> {

	private SmsPredefiniHandler handler;

	public SmsPredefiniLocator() {

	}

	@Override
	public SmsPredefini create(Class<? extends SmsPredefini> clazz) {
		return new SmsPredefini();
	}

	@Override
	public SmsPredefini find(Class<? extends SmsPredefini> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<SmsPredefini> getDomainType() {
		return SmsPredefini.class;
	}

	@Override
	public String getId(SmsPredefini domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(SmsPredefini domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (SmsPredefiniHandler) context.getBean("smsPredefiniHandler");
	}
}
