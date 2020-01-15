package world.deslauriers.hellion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nav")
public class Nav implements Serializable{

	private static final long serialVersionUID = -8892274040461587746L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="nav")
	private String nav;
	
	@Column(name="category")
	private String category;
	
	public Nav() {}

	public Nav(int id, String nav, String category) {
		
		this.id = id;
		this.nav = nav;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static class NavBuilder {
		
		private int id;
		private String nav;
		private String category;
		
		public NavBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public NavBuilder nav(String nav) {
			this.nav = nav;
			return this;
		}
		
		public NavBuilder category(String category) {
			this.category = category;
			return this;
		}
		
		public Nav build() {
			return new Nav(id, nav, category);
		}
	}
	
	@Override
	public String toString() {
		return "Nav [id=" + id + ", nav=" + nav + ", category=" + category + "]";
	}
	
}
