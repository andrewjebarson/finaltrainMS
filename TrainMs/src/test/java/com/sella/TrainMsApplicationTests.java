
package com.sella;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sella.model.Detail;
import com.sella.model.Schedule;
import com.sella.model.Seat;
import com.sella.model.Station;
import com.sella.repository.DetailRepository;

@SpringBootTest
class TrainMsApplicationTests {

	@Autowired
	DetailRepository detailRepo;

	
//	@Test
	public void testReadAll() {
	List<Detail> list=detailRepo.findAll();
	assertThat(list).size().isGreaterThan(0);
	}
	
//	@Test
	public void testCreate() {
	Detail d = new Detail();
	d.setTrainId(1111);
	d.setTrainName("cheran express");
	d.setBasePrice(24);
	Schedule sc=new Schedule();
	sc.setDepartureDate(new Date(2022-1900,11,7,4,30));
	sc.setArrivalDate(new Date(2022-1900,11,7,4,30));
	sc.setFromStation("chennai");
	sc.setToStation("kerla");
	sc.setJourneyHours(12);
	Seat se=new Seat();
	se.setCategoryName("first class");
	se.setCategoryPrice(400);
	se.setTotalSeats(120);
	Station sa=new Station();
	sa.setStationName("katpadi");
	sa.setStopNo(5);
	sc.setSeats(Arrays.asList(se));
	d.setSchedules(Arrays.asList(sc));
	d.setStations(Arrays.asList(sa));
	detailRepo.save(d);
	assertNotNull(detailRepo.findById(1111).get());
	}

}
