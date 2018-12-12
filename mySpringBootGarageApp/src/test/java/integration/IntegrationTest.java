package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.MySpringBootGarageAppApplication;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.model.MySpringBootDataModel;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.repository.MySpringBootRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MySpringBootGarageAppApplication.class})
@ContextConfiguration(classes = {MySpringBootGarageAppApplication.class})
@AutoConfigureMockMvc
public class IntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private MySpringBootRepository repository;
	
	@Before
	public void clearDB() 
	{
		repository.deleteAll();  
	} 
	
	//Test to create a vehicle//
	@Test
	public void addAVehicleToDatbaseTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/create/vehicle")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"type\" : \"Motorcycle\",\"make\" : \"Kawasaki\", \"model\" : \"600R\"}"))
				.andExpect(status()
				.isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.model", is("600R"))); 
		}
	
	//Test to get a vehicle//
	@Test 
	public void findingAndRetrievingVehicleFromDatabase() 
		throws Exception {
		repository.save(new MySpringBootDataModel("Car","BMW","M5")); 
		mvc.perform(get("/api/vehicle") 
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content() 
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].type",is("Car")));		 
      } 
} 
