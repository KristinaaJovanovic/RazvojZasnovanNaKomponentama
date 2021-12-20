package rzk.interceptors;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import rzk.beans.KategorijeBlogaBean;

public class BrojPostavljenihInterceptor {
	
	@EJB
	KategorijeBlogaBean kb;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		kb.povecajBrojPostavljenih();
		
		return ctx.proceed();
	}

}
