package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.Medicament;
import org.imogene.epicam.server.handler.MedicamentHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate Medicament beans 
 * @author Medes-IMPS
 */
public class MedicamentLocator extends Locator<Medicament, String> {

	private MedicamentHandler handler;

	public MedicamentLocator() {

	}

	@Override
	public Medicament create(Class<? extends Medicament> clazz) {
		return new Medicament();
	}

	@Override
	public Medicament find(Class<? extends Medicament> clazz, String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<Medicament> getDomainType() {
		return Medicament.class;
	}

	@Override
	public String getId(Medicament domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Medicament domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (MedicamentHandler) context.getBean("medicamentHandler");
	}
}
