package com.parsons.elliott.springboot.garage.mySpringBootGarageApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.exception.ResourceNotFoundException;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.model.MySpringBootDataModel;
import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.repository.MySpringBootRepository;

@RestController
@RequestMapping("/api")
public class MySpringBootDataAppController {
	
	@Autowired
	MySpringBootRepository myRepository;
	
	//Method to create a vehicle//
	@PostMapping("/create/vehicle")
	public MySpringBootDataModel createVehicle(@Valid @RequestBody MySpringBootDataModel mSDM) 
	{
		return myRepository.save(mSDM); 
	}
	
	//Method to get a vehicle//
	@GetMapping("/vehicle/{id}")
	public MySpringBootDataModel getVehiclebyID(@PathVariable(value = "id")Long vehicleID) 
	{
		return myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("MySpringBootDataModel","id",vehicleID));
	}

	/*//Method to get all by vehicle type//
	@GetMapping("/vehicle/type/{type}")

	public List<MySpringBootDataModel> getVehicleByType(@PathVariable(value = "type")String type)
	{
		if (myRepository.findByType(type)== null) {
			throw new ResourceNotFoundException("MySpringBootDataModel","type",type);
		}
		return myRepository.findByType(type);
	}*/
	
	//Method to get all by vehicle model//
	@GetMapping("/vehicle/type/{type}")
	public List<MySpringBootDataModel> getVehicleByModel(@PathVariable(value = "type")String model)
	{
		if (myRepository.findByType(model)== null) {
			throw new ResourceNotFoundException("MySpringBootDataModel","type",model);
		}
		return myRepository.findByModel(model);   
	}
	
	//Method to get all vehicles// 
	@GetMapping("/vehicle")
	public List<MySpringBootDataModel> getAllVehicles()
	{
		return myRepository.findAll();
	}
	
	//Method to update a vehicle//
	@PutMapping("/vehicle/{id}")
	public MySpringBootDataModel updateVehicle(
			@PathVariable(value = "id)")Long vehicleID,
			@Valid 
			@RequestBody MySpringBootDataModel vehicleDetails) 
	{
	
	MySpringBootDataModel mSDM = myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("Vehicle","id",vehicleID));
	
	mSDM.setType(vehicleDetails.getType());
	mSDM.setMake(vehicleDetails.getMake());
	mSDM.setModel(vehicleDetails.getModel());
	
	MySpringBootDataModel updateData = myRepository.save(mSDM);
	return updateData;
	}
	
	//Method to remove by vehicle type//
	@DeleteMapping("/vehicle/type/{type}")
	public ResponseEntity<?>deleteByType(@PathVariable(value = "type")String type)
	{
		List<MySpringBootDataModel> mSDM = myRepository.findByType(type);
		
		myRepository.deleteAll(mSDM);
		return ResponseEntity.ok().build();
	}
	
	//Method to remove a vehicle//
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value ="id")Long vehicleID)
	{
		MySpringBootDataModel mSDM = myRepository.findById(vehicleID).orElseThrow(()->new ResourceNotFoundException("vehicle","id",vehicleID));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	} 

}
   