package world.deslauriers.hellion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="image")
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
	
	@Column(name="uuid")
	private String uuid;

	public Image() {}
	
	public Image(String name, String description, String uuid) {
		super();
		this.name = name;
		this.description = description;
		this.uuid = uuid;
	}

	public Image(int id, String name, String description, int navcategory, String uuid) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.navcategory = navcategory;
		this.uuid = uuid;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
