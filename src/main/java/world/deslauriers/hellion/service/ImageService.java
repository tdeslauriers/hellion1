package world.deslauriers.hellion.service;

import java.util.List;

import world.deslauriers.hellion.model.Image;

public interface ImageService {

	public List<Image> findImagesByYear(String year);

}
