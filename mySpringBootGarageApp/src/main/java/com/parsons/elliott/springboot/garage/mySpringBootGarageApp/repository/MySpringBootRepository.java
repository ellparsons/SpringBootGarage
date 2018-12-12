package com.parsons.elliott.springboot.garage.mySpringBootGarageApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parsons.elliott.springboot.garage.mySpringBootGarageApp.model.MySpringBootDataModel;

@Repository
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel,Long>
{
	List<MySpringBootDataModel>findByType(String type);
	List<MySpringBootDataModel>findByModel(String model);
}