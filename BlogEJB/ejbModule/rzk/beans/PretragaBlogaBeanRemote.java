package rzk.beans;

import java.util.List;

import javax.ejb.Remote;

import model.Blog;

@Remote
public interface PretragaBlogaBeanRemote {
	public List<Blog> pretraziBlogove(String text);
}
