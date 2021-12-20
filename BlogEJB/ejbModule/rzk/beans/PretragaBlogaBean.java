package rzk.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Blog;
import rzk.interceptors.BrojPregledanihInterceptor;

/**
 * Session Bean implementation class PretragaBlogaBean
 */
@Stateless
@LocalBean
public class PretragaBlogaBean implements PretragaBlogaBeanRemote {
	
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public PretragaBlogaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@Interceptors(BrojPregledanihInterceptor.class)
	public List<Blog> pretraziBlogove(String text) {
		Query q=em.createQuery("select b from Blog b where b.text LIKE :text");
		q.setParameter("text", text);
		
		List<Blog> blogovi=q.getResultList();
		return blogovi;
	}
	
	
	

}
