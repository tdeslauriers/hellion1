package world.deslauriers.hellion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="image1")
public class Image implements Serializable {
	
	private static final long serialVersionUID = -7912597309119940676L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="navcategory")
	private int navcategory;

	public Image() {}

	public Image(int id, String name, String description, int navcategory) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.navcategory = navcategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNavcategory() {
		return navcategory;
	}

	public void setNavcategory(int navcategory) {
		this.navcategory = navcategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static class ImageBuilder {
		 
		private int id;
		private String name;
		private String description;
		private int navcategory;
		
		public ImageBuilder id(int id) {
			this.id = id;
			return this;
		}
		
		public ImageBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public ImageBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public ImageBuilder navcategory(int navcategory) {
			this.navcategory = navcategory;
			return this;
		}
		
		public Image build() {
			return new Image(id, name, description, navcategory);
		}
	}
	
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", description=" + description + ", navcategory=" + navcategory
				+ "]";
	}
	
}
