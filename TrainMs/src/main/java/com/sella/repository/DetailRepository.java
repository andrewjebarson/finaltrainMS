package com.sella.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sella.model.Detail;

public interface DetailRepository extends JpaRepository<Detail, Integer>{
	public List<Detail> findByTrainName(String trainName);	
}

