package world.deslauriers.hellion.model.audit;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(
		value= {"createdBy", "updatedBy"},
		allowGetters=true
)
public abstract class UserDateAudit extends DateAudit {
	
	private static final long serialVersionUID = 4607636816920671239L;

	@CreatedBy
	private Long createdBy;
	
	@LastModifiedBy
	private Long updatedBy;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
}
