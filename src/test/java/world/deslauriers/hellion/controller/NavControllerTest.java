package world.deslauriers.hellion.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import world.deslauriers.hellion.service.ImageService;
import world.deslauriers.hellion.service.NavService;

@RunWith(SpringRunner.class)
@WebMvcTest(NavController.class)
@AutoConfigureMockMvc
public class NavControllerTest {
	
	private static String TESTYEAR = "2017";
	private static String TESTYEAR1 = "judo";
	
	@Autowired
	private NavController navController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private NavService navService;
	
	@MockBean
	private ImageService imageService;
	
	@Test
	public void testNavControllerIsNotNull() throws Exception {
		
		assertThat(navController).isNotNull();
	}
	
	@Test
	public void testRequestToGetNav() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/nav/gallery")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content()
				.contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void testRequestToGetImagesByYear() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/nav/gallery/{year}", TESTYEAR)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content()
				.contentType(MediaType.APPLICATION_JSON_UTF8));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/nav/gallery/{year}", TESTYEAR1)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content()
				.contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
