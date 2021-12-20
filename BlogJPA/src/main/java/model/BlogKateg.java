package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BlogKateg database table.
 * 
 */
@Entity
@NamedQuery(name="BlogKateg.findAll", query="SELECT b FROM BlogKateg b")
public class BlogKateg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String naziv;

	//bi-directional many-to-one association to Blog
	@OneToMany(mappedBy="blogKateg")
	private List<Blog> blogs;

	public BlogKateg() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Blog addBlog(Blog blog) {
		getBlogs().add(blog);
		blog.setBlogKateg(this);

		return blog;
	}

	public Blog removeBlog(Blog blog) {
		getBlogs().remove(blog);
		blog.setBlogKateg(null);

		return blog;
	}

}