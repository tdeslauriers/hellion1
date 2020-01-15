package world.deslauriers.hellion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import world.deslauriers.hellion.model.Image;
import world.deslauriers.hellion.model.Nav;
import world.deslauriers.hellion.repository.ImageDao;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private NavService navService;
	
	@Override
	public List<Image> findImagesByYear(String year) {
		
		Optional<Nav> nav = navService.findOneNav(year);
		Integer navId = nav.get().getId();
		
		return imageDao.findByYearId(navId);
	}

}
