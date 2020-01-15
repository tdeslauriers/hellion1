package world.deslauriers.hellion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import world.deslauriers.hellion.model.Image;
import world.deslauriers.hellion.model.Nav;
import world.deslauriers.hellion.service.ImageService;
import world.deslauriers.hellion.service.NavService;

@CrossOrigin(origins= {"http://localhost:3000", "http://localhost:4200"})
@RestController
@RequestMapping(value="/api/nav")
public class NavController {

	@Autowired
	private NavService navService;
	
	@Autowired
	private ImageService imageService;
	
	@CrossOrigin(origins= {"http://localhost:3000", "http://localhost:4200"})
	@RequestMapping(value="/gallery", 
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Nav>> getNav(){

		List<Nav> navs = new ArrayList<Nav>(navService.findAll());
		
		return new ResponseEntity<List<Nav>>(navs, HttpStatus.OK);
	}
	
	@RequestMapping(value="/gallery/{year}", 
					method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Image>> getImagesByNavYear(@PathVariable("year") String year){
		
		List<Image> images = new ArrayList<Image>(imageService.findImagesByYear(year));
		
		return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
	}
}
