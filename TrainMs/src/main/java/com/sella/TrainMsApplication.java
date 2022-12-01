package com.sella;


import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;

import com.sella.model.Detail;

import com.sella.model.Schedule;
import com.sella.model.Seat;

import com.sella.model.Station;

import com.sella.repository.DetailRepository;

import com.sella.repository.ScheduleRepository;
import com.sella.repository.SeatRepository;

import com.sella.repository.StationRepository;

@SpringBootApplication
public class TrainMsApplication  implements CommandLineRunner{
	
	@Autowired
	DetailRepository deRepo;
	@Autowired
	ScheduleRepository scRepo;
	@Autowired
	SeatRepository seRepo;
	@Autowired
	StationRepository stRepo;
	

	public static void main(String[] args) {
		SpringApplication.run(TrainMsApplication.class, args);
	}
	
	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) { 
	EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils); 
	String ip = null; 
	try { 
	ip = InetAddress.getLocalHost().getHostAddress(); 
	} catch (Exception e) { 
	System.out.println("Exception"); 
	} 
	config.setNonSecurePort(8082); 
	config.setIpAddress(ip); 
	config.setPreferIpAddress(true); 
	return config; 
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Detail d = new Detail();
		d.setTrainId(123455);
		d.setTrainName("cheran express");
		d.setBasePrice(24);
		Schedule s=new Schedule();
		s.setDepartureDate(new Date(2022-1900,11,3,4,30));
		s.setArrivalDate(new Date(2022-1900,11,2,4,30));
		s.setFromStation("madurai");
		s.setToStation("chennai");
		s.setJourneyHours(12);
		Seat se=new Seat();
		se.setCategoryName("first class");
		se.setCategoryPrice(400);
		se.setTotalSeats(120);
		
		
		Schedule s1=new Schedule();
		s1.setDepartureDate(new Date(2022-1900,11,4,5,30));
		s1.setArrivalDate(new Date(2022-1900,11,5,6,30));
		s1.setFromStation("chennai");
		s1.setToStation("madurai");
		s1.setJourneyHours(12);
		Seat se1=new Seat();
		se1.setCategoryName("first class");
		se1.setCategoryPrice(400);
		se1.setTotalSeats(120);
		
		
		Station sa=new Station();
		sa.setStationName("katpadi");
		sa.setStopNo(5);
		s.setSeats(Arrays.asList(se,se1));
		d.setSchedules(Arrays.asList(s,s1));
		d.setStations(Arrays.asList(sa));
		deRepo.save(d);
	
		
		Detail d1 = new Detail();
		d1.setTrainId(123456);
		d1.setTrainName("Tiruvendurm mail");
		d1.setBasePrice(24);
		Schedule s2=new Schedule();
		s2.setDepartureDate(new Date(2022-1900,11,3,4,30));
		s2.setArrivalDate(new Date(2022-1900,11,2,4,30));
		s2.setFromStation("kerala");
		s2.setToStation("chennai");
		s2.setJourneyHours(12);
		Seat se2=new Seat();
		se2.setCategoryName("first class");
		se2.setCategoryPrice(400);
		se2.setTotalSeats(120);
		
		
		Schedule s3=new Schedule();
		s3.setDepartureDate(new Date(2022-1900,11,4,5,30));
		s3.setArrivalDate(new Date(2022-1900,11,3,6,30));
		s3.setFromStation("kerala");
		s3.setToStation("chennai");
		s3.setJourneyHours(12);
		Seat se3=new Seat();
		se3.setCategoryName("first class");
		se3.setCategoryPrice(400);
		se3.setTotalSeats(120);
		
		
		Station sa1=new Station();
		sa1.setStationName("katpadi");
		sa1.setStopNo(5);
		s2.setSeats(Arrays.asList(se2,se3));
		d1.setSchedules(Arrays.asList(s2,s3));
		d1.setStations(Arrays.asList(sa1));
		deRepo.save(d1);
		
		
		
	}

}
