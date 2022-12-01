package com.sella.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.Data;

@Entity
@EntityScan("com.sella")
@Data
@Table(name="train_seat")
@DynamicInsert
@DynamicUpdate

public class Seat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	private int categoryPrice;
	private int totalSeats;
	
}