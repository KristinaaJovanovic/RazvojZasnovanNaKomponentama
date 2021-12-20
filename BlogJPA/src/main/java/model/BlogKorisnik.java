package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BlogKorisnik database table.
 * 
 */
@Entity
@NamedQuery(name="BlogKorisnik.findAll", query="SELECT b FROM BlogKorisnik b")
public class BlogKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String username;

	private String ime;

	private String password;

	private String prezime;

	//bi-directional many-to-one association to Blog
	@OneToMany(mappedBy="blogKorisnik")
	private List<Blog> blogs;

	public BlogKorisnik() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Blog addBlog(Blog blog) {
		getBlogs().add(blog);
		blog.setBlogKorisnik(this);

		return blog;
	}

	public Blog removeBlog(Blog blog) {
		getBlogs().remove(blog);
		blog.setBlogKorisnik(null);

		return blog;
	}

}