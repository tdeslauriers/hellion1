package world.deslauriers.hellion.repository;

import java.util.List;

import world.deslauriers.hellion.model.Image;

public interface ImageDao {

	public List<Image> findByYearId(Integer yearId);
}
