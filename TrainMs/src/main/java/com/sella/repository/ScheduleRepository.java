package com.sella.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sella.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

}
