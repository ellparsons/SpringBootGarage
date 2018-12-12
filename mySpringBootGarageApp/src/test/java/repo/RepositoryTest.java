package repo;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.MySpringBootGarageAppApplication;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.model.MySpringBootDataModel;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.repository.MySpringBootRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MySpringBootGarageAppApplication.class})
@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private MySpringBootRepository myRepo;
	 
	@Test
	public void retrieveByIdTest() {
		MySpringBootDataModel model1 = new MySpringBootDataModel("Car","Honda","S2000");
		entityManager.persist(model1); 
		entityManager.flush();  
		assertTrue(myRepo.findById(model1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByModel() {
		MySpringBootDataModel model2 = new MySpringBootDataModel("Car","Honda","S2000");
		entityManager.persist(model2);
		entityManager.flush();
			for (int i =0; i < myRepo.findByModel(model2.getModel()).size(); i++) {
					assertEquals(myRepo.findByModel(model2.getModel()).get(i).getModel(),model2.getModel());
			}
	} 

}
