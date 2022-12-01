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

@Data
@Entity
@EntityScan("com.sella")
@Table(name="train_station")
@DynamicInsert
@DynamicUpdate

public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int stationId;
	private String stationName;
	private int stopNo;
	
}

