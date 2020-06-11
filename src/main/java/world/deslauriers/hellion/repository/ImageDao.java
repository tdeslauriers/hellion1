package world.deslauriers.hellion.repository;

import java.util.List;
import java.util.Optional;

import world.deslauriers.hellion.model.Image;

public interface ImageDao {

	List<Image> findByYearId(Integer yearId);
	Optional<Image> findByUuid(String uuid);
}
