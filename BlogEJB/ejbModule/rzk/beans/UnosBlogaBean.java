package rzk.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Blog;
import model.BlogKateg;
import model.BlogKorisnik;
import rzk.interceptors.BrojPostavljenihInterceptor;

/**
 * Session Bean implementation class UnosBlogaBean
 */
@Stateful
@LocalBean
public class UnosBlogaBean implements UnosBlogaBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	BlogKorisnik loggedUser;

    /**
     * Default constructor. 
     */
    public UnosBlogaBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean login(String username, String password) {
		Query q=em.createQuery("select k from BlogKorisnik k where k.username LIKE :username and k.password LIKE :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		try {
			loggedUser=(BlogKorisnik) q.getResultList().get(0);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	@Interceptors(BrojPostavljenihInterceptor.class)
	public boolean postuj(String text, int kategorija) {
		Blog b=new Blog();
		b.setBlogKorisnik(loggedUser);
		b.setDatum(new Date());
		b.setText(text);
		b.setLikeNo(0);
		b.setBlogKateg(em.find(BlogKateg.class, kategorija));
		
		try {
			em.persist(b);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@PostConstruct
	public void create() {
		DateFormat dateFormat=new SimpleDateFormat();
		Calendar cal=Calendar.getInstance();
		System.out.println("Bean created: "+dateFormat.format(cal.getTime()));
	}
	
	@PreDestroy
	public void destroy() {
		DateFormat dateFormat=new SimpleDateFormat();
		Calendar cal=Calendar.getInstance();
		System.out.println("Bean has been destroyed: "+dateFormat.format(cal.getTime()));
	}

}
