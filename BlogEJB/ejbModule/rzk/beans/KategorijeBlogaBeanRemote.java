package rzk.beans;

import java.util.List;

import javax.ejb.Remote;

import model.BlogKateg;

@Remote
public interface KategorijeBlogaBeanRemote {
	public List<BlogKateg> vratiSveKategorije();
}
