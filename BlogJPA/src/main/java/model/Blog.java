package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Blog database table.
 * 
 */
@Entity
@NamedQuery(name="Blog.findAll", query="SELECT b FROM Blog b")
public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBlog;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	private int likeNo;

	private String text;

	//bi-directional many-to-one association to BlogKateg
	@ManyToOne
	@JoinColumn(name="idKateg")
	private BlogKateg blogKateg;

	//bi-directional many-to-one association to BlogKorisnik
	@ManyToOne
	@JoinColumn(name="username")
	private BlogKorisnik blogKorisnik;

	public Blog() {
	}

	public int getIdBlog() {
		return this.idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getLikeNo() {
		return this.likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BlogKateg getBlogKateg() {
		return this.blogKateg;
	}

	public void setBlogKateg(BlogKateg blogKateg) {
		this.blogKateg = blogKateg;
	}

	public BlogKorisnik getBlogKorisnik() {
		return this.blogKorisnik;
	}

	public void setBlogKorisnik(BlogKorisnik blogKorisnik) {
		this.blogKorisnik = blogKorisnik;
	}

}