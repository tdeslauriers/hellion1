package world.deslauriers.hellion.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import world.deslauriers.hellion.model.Nav;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavDaoTest {
	
	private static final String JUDO = "judo";
	private static final String ART = "art";
	private static final String CATEGORY = "gallery";
	private static final Integer NAV_TABLE_SIZE = 6;
	
	@Autowired
	private NavDao navDao;
	
	private Nav judo;
	private Nav art;
	
	private boolean containsNav(final List<Nav> navItem, final String item) {
		return navItem.stream().filter(o -> o.getNav().equals(item)).findFirst().isPresent();
	}
	
	private boolean containsCategory(final List<Nav> navItem, final String item) {
		return navItem.stream().filter(o -> o.getCategory().equals(item)).findFirst().isPresent();
	}
	
	@Before
	public void setUp() {
		
		judo = new Nav.NavBuilder().nav(JUDO).category(CATEGORY).build();
		art = new Nav.NavBuilder().nav(ART).category(CATEGORY).build();
	}
	
	@Test
	public void testFindAllShouldReturnListOfNavs() {
		
		List<Nav> navs = navDao.findAll();
		
		assertThat(navs).isNotNull().hasSize(NAV_TABLE_SIZE);
		
		assertTrue(containsNav(navs, judo.getNav()));
		assertTrue(containsCategory(navs, judo.getCategory()));
		assertTrue(containsNav(navs, art.getNav()));
		assertTrue(containsCategory(navs, art.getCategory()));
		
		assertFalse(containsNav(navs, "foo"));
		assertFalse(containsCategory(navs, "bar"));
	}
	
	@Test
	public void testFindOneNavShouldReturnValidNavObject() {
		
		Optional<Nav> validNavItem0 = navDao.findOneNav(JUDO);
		Optional<Nav> validNavItem1 = navDao.findOneNav(ART);
		
		assertThat(validNavItem0).isPresent();
		assertThat(validNavItem0.get().getNav()).isEqualTo(judo.getNav());
		assertThat(validNavItem0.get().getCategory()).isEqualTo(judo.getCategory());
		
		assertThat(validNavItem1).isPresent();
		assertThat(validNavItem1.get().getNav()).isEqualTo(art.getNav());
		assertThat(validNavItem1.get().getCategory()).isEqualTo(art.getCategory());
	}
}
