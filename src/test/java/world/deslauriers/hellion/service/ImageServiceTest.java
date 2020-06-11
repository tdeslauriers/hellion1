package world.deslauriers.hellion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import world.deslauriers.hellion.model.Image;
import world.deslauriers.hellion.repository.ImageDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
	
	private static final String IMAGE_2016 = "Deslauriers-12.jpg";
	private static final String YEAR1 = "2017";
	private static final Integer YEAR_2017_ID = 11;
	private static final String IMAGE_2017_0 = "Deslauriers-28.jpg";
	private static final String IMAGE_2017_1 = "Deslauriers-29.jpg";
	private static final Integer YEAR_LIST_SIZE = 2;
	
	@Autowired
	private ImageService imageService;
	
	@MockBean
	private ImageDao imageDao;
	
	private Image pic2017_0;
	private Image pic2017_1;
	private List<Image> mockImagesList;
	
	private boolean containsImage(final List<Image> imageList, final String name) {
		return imageList.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}
	
	@Before
	public void setUp() {
		
		pic2017_0 = new Image();
		pic2017_0.setNavcategory(11);
		pic2017_0.setName(IMAGE_2017_0);
		pic2017_1 = new Image();
		pic2017_1.setNavcategory(11);
		pic2017_1.setName(IMAGE_2017_1);
		
		mockImagesList = new ArrayList<Image>();
		mockImagesList.add(pic2017_0);
		mockImagesList.add(pic2017_1);
	}
	
	@Test
	public void testFindImagesByYearReturnsCorrectImageList() {
		
		Mockito.when(imageDao.findByYearId(YEAR_2017_ID)).thenReturn(mockImagesList);
		List<Image> imageQuery = imageService.findImagesByYear(YEAR1);
		
		assertThat(imageQuery).isNotNull();
		assertThat(imageQuery).isNotEmpty();
		
		assertEquals(YEAR_LIST_SIZE, Integer.valueOf(imageQuery.size()));
		assertEquals(IMAGE_2017_0, imageQuery.get(0).getName());
		assertEquals(IMAGE_2017_1, imageQuery.get(1).getName());
		
		assertFalse(containsImage(imageQuery, IMAGE_2016));
	}
}	

