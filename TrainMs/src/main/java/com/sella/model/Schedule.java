package com.sella.model;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.Data;

@Entity
@Data
@EntityScan("com.sella")
@Table(name="train_schedule")
@DynamicInsert
@DynamicUpdate

public class Schedule {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int scheduleId;
	private Date departureDate;
	private Date arrivalDate;
	private String fromStation;
	private String toStation;
	private int journeyHours;
	@OneToMany(cascade = CascadeType.ALL)
	private List <Seat> seats;
	
}
