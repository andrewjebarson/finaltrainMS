package com.sella.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sella.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer>{

}
