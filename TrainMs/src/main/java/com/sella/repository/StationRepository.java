package com.sella.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sella.model.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {

}

