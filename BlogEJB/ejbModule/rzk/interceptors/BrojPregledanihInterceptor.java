package rzk.interceptors;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Blog;
import rzk.beans.KategorijeBlogaBean;

public class BrojPregledanihInterceptor {
	
	@EJB
	KategorijeBlogaBean kb;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		List<Blog> blogovi=(List<Blog>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		kb.povecajBrojPregledaPosta(blogovi);
		return ctx.proceed();
	}

}
