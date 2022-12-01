package com.sella.model;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import lombok.Data;

@Entity
@EntityScan("com.sella")
@Table(name="train_detail")
@Data
@DynamicInsert
@DynamicUpdate

public class Detail {
	@Id
	private int trainId;
	private String trainName;
	private int basePrice;
	@OneToMany(cascade = CascadeType.ALL)
	private List <Schedule> schedules;	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Station> stations;	
	
}
