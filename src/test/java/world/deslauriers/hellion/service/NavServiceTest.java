package world.deslauriers.hellion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import world.deslauriers.hellion.model.Nav;
import world.deslauriers.hellion.repository.NavDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavServiceTest {
	
	private static final Integer JUDO_ID = 14;
	private static final String JUDO = "judo";
	private static final Integer ART_ID = 15;
	private static final String ART = "art";
	private static final String CATEGORY = "gallery";
	private static final Integer NAV_TABLE_SIZE = 2;
	
	@Autowired
	private NavService navService;
	
	@MockBean
	private NavDao navDao;
	
	private Nav judo;
	private Nav art;
	private List<Nav> mockNavList;
	
	private boolean containsNav(final List<Nav> navItem, final String item) {
		return navItem.stream().filter(o -> o.getNav().equals(item)).findFirst().isPresent();
	}
	
	@Before
	public void setUp() {
		
		judo = new Nav.NavBuilder().id(JUDO_ID).nav(JUDO).category(CATEGORY).build();
		art = new Nav.NavBuilder().id(ART_ID).nav(ART).category(CATEGORY).build();
		
		mockNavList = new ArrayList<Nav>();
		mockNavList.add(judo);
		mockNavList.add(art);
	}
	
	@Test
	public void testFindAllShouldReturnListOfNavs() {
		
		Mockito.when(navDao.findAll()).thenReturn(mockNavList);
		List<Nav> navs = navService.findAll();
		
		assertThat(navs).isNotNull();
		assertThat(navs).isNotEmpty();
		
		assertEquals(NAV_TABLE_SIZE,Integer.valueOf(navs.size()));
		assertEquals(JUDO, navs.get(0).getNav());
		assertEquals(ART, navs.get(1).getNav());
		
		assertFalse(containsNav(navs, "foo"));
		assertFalse(containsNav(navs, "bar"));
	}
	
	@Test
	public void testFindOneNavServiceReturnsCorrectNav() {
		
		Mockito.when(navDao.findOneNav(JUDO)).thenReturn(Optional.of(judo));
		Optional<Nav> nav = navService.findOneNav(JUDO);
		
		assertThat(nav).isNotNull();
		assertThat(nav).isNotEmpty();
		
		assertEquals(JUDO_ID, Integer.valueOf(nav.get().getId()));
		assertEquals(JUDO, nav.get().getNav());
		assertEquals(CATEGORY, nav.get().getCategory());
		
		assertFalse(ART.equals(nav.get().getNav()));
		assertFalse(ART_ID == nav.get().getId());
	}
}
