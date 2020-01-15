package world.deslauriers.hellion.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import world.deslauriers.hellion.model.Image;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageDaoTest {
	
	private static final Integer NAV_CATEGORY_0 = 10;
	private static final String IMAGE_2016 = "Deslauriers-12.jpg";
	private static final Integer NAV_CATEGORY_1 = 11;
	private static final String IMAGE_2017 = "Deslauriers-28.jpg";
	private static final Integer YEAR_LIST_SIZE = 20;
	
	@Autowired
	private ImageDao imageDao;
	
	private Image pic2017;
	private Image pic2016;
	
	private boolean containsImage(final List<Image> imageList, final String name) {
		return imageList.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}
	
	@Before
	public void setUp() {
		
		pic2016 = new Image.ImageBuilder().navcategory(10).name(IMAGE_2016).build();
		pic2017 = new Image.ImageBuilder().navcategory(11).name(IMAGE_2017).build();		
	}
	
	@Test
	public void testFindByYearShouldOnlyReturnSingleYearsPics() {
		
		List<Image> picsfrom2017 = imageDao.findByYearId(NAV_CATEGORY_1);
		
		assertThat(picsfrom2017).isNotNull().hasSize(YEAR_LIST_SIZE);
		assertTrue(containsImage(picsfrom2017, pic2017.getName()));
		assertFalse(containsImage(picsfrom2017, pic2016.getName()));
	}
}
