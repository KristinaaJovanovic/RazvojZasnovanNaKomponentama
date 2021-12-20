package rzk.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Blog;
import model.BlogKateg;

/**
 * Session Bean implementation class KategorijeBlogaBean
 */
@Singleton
@LocalBean
public class KategorijeBlogaBean implements KategorijeBlogaBeanRemote {
	
	@PersistenceContext
	EntityManager em;
	
	//statisticki podaci
	int brPostavljenihBlogova;
	HashMap<Integer, Integer> brPregledaBloga;
	
	

    public int getBrPostavljenihBlogova() {
		return brPostavljenihBlogova;
	}

	public void setBrPostavljenihBlogova(int brPostavljenihBlogova) {
		this.brPostavljenihBlogova = brPostavljenihBlogova;
	}

	public HashMap<Integer, Integer> getBrPregledaBloga() {
		return brPregledaBloga;
	}

	public void setBrPregledaBloga(HashMap<Integer, Integer> brPregledaBloga) {
		this.brPregledaBloga = brPregledaBloga;
	}

	/**
     * Default constructor. 
     */
    public KategorijeBlogaBean() {
        // TODO Auto-generated constructor stub
    	brPostavljenihBlogova=0;
    	brPregledaBloga=new HashMap<Integer, Integer>();
    }

	@Override
	public List<BlogKateg> vratiSveKategorije() {
		Query q=em.createQuery("select k from BlogKateg k");
		
		return q.getResultList();
	}
	
	public void povecajBrojPostavljenih() {
		brPostavljenihBlogova++;
	}
	
	public void povecajBrojPregledaPosta(List<Blog> blogovi) {
		for(Blog b: blogovi) {
			if(brPregledaBloga.containsKey(b.getIdBlog())) {
				brPregledaBloga.put(b.getIdBlog(), brPregledaBloga.get(b.getIdBlog()+1));
			}else {
				brPregledaBloga.put(b.getIdBlog(), 1);
			}
		}
		
	}
	
	@Timeout
	public void stampajStatistiku() {
		System.out.println(brPostavljenihBlogova);
	}
	
	
	@Resource
	TimerService ts;
	Timer tm;
	
	public void startTimer() {
		TimerConfig tc=new TimerConfig();
		tc.setPersistent(false);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date datum=sdf.parse("01-01-2021 00:00:00");
			tm=(Timer) ts.createIntervalTimer(datum, 1000*60*60*24, tc);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
