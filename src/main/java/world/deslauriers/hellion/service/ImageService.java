package world.deslauriers.hellion.service;

import java.util.List;
import java.util.Optional;

import world.deslauriers.hellion.model.Image;

public interface ImageService {

	List<Image> findImagesByYear(String year);
	Optional<Image> findImageByUuid(String uuid);
}
