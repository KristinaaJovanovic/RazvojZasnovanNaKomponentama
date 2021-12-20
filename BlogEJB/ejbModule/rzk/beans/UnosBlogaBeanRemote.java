package rzk.beans;

import javax.ejb.Remote;

@Remote
public interface UnosBlogaBeanRemote {
	public boolean login(String username, String password);
	public boolean postuj(String text, int kategorija);
}
