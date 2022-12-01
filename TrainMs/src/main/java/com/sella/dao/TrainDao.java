package com.sella.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sella.model.Detail;

import com.sella.model.Schedule;
import com.sella.model.Seat;
import com.sella.model.Station;
import com.sella.repository.DetailRepository;
import com.sella.repository.ScheduleRepository;
import com.sella.repository.SeatRepository;
import com.sella.repository.StationRepository;

@Repository
public class TrainDao {
	@Autowired
	DetailRepository detailRepo;
	@Autowired
	SeatRepository seatRepo;
	@Autowired
	ScheduleRepository scheduleRepo;
	@Autowired
	StationRepository stationRepo;
	
	public void registerTrain(Detail detail) {
		detailRepo.save(detail);
		
	}
	public void removeTrain(int trainId) {
		Detail detail = detailRepo.findById(trainId).get();
		for(Station st:detail.getStations()) {
			stationRepo.deleteById(st.getStationId());
		}
		for(Schedule s:detail.getSchedules()) {
			for(Seat se:s.getSeats()){
				seatRepo.deleteById(se.getCategoryId());
			}
			scheduleRepo.deleteById(s.getScheduleId());
		}
		detailRepo.deleteById(trainId);
	}
	public Detail modifyTrain(Detail d) {
		Detail d1 = detailRepo.findById(d.getTrainId()).get();
		d1.setTrainName(d.getTrainName());
		d1.setBasePrice(d.getBasePrice());
		d1.setSchedules(d.getSchedules());
		d1.setStations(d.getStations());
		return detailRepo.save(d1);	
	}
	public List<Detail> getallTrain(){
		return detailRepo.findAll();
	}
	public Detail searchTrainById(int trainId) {
		Detail detail = detailRepo.findById(trainId).get();
		return detail;	
	}
	public List<Detail> searchTrainByName(String trainName) {
		List<Detail> detailList = detailRepo.findByTrainName(trainName);
		return detailList;	
	}
	public List<Detail> searchTrainByStartStation(String fromStation) {
		List<Detail> d=new ArrayList<Detail>();
		List<Detail> tempList=getallTrain();	
		for(Detail t:tempList) {
			for(Schedule s:t.getSchedules()) {
				if(s.getFromStation().equals(fromStation)) {
					d.add(t);
				}
			}
		}		
		return d ;		
	}	
	
	public Set<String> searchSource(){
		Set<String> d = new LinkedHashSet<String>();	
		List<Detail> tempList=getallTrain();
		for(Detail t:tempList) {
			for(Schedule s:t.getSchedules()) {
				d.add(s.getFromStation());
			}
		}		
		return d ;		
	}
	
	public Set<String>  searchDestination(String fromStation) {
		Set<String> d = new LinkedHashSet<String>();	
		List<Detail> tempList=searchTrainByStartStation(fromStation);
		for(Detail t:tempList) {
			for(Schedule s:t.getSchedules()) {
				if(s.getFromStation().equals(fromStation)) {
				d.add(s.getToStation());
				}
			}
		}		
		return d ;		
	}
	public List<Detail> searchByFromAndToStation(String fromStation,String toStation){
		List<Detail> detailList=new ArrayList<Detail>();
		List<Detail> tempList=detailRepo.findAll();
		for(Detail t:tempList) {
			for(Schedule s:t.getSchedules()) {
				if(s.getFromStation().equals(fromStation)&& s.getToStation().equals(toStation)) {
					detailList.add(t);
				}	
			}
		}
		return detailList;
	}
	public List<Detail> searchByStationandDate(String fromStation,String toStation,Date departureDate ){
		List<Detail> detailList=new ArrayList<Detail>();
		Date endDate=(Date) departureDate.clone();
		endDate.setDate(departureDate.getDate()+1);
		List<Detail> tempList=searchByFromAndToStation(fromStation,toStation);
		for (Detail t:tempList) {
			for (Schedule s:t.getSchedules()) {
				if((s.getDepartureDate().after(departureDate) && s.getDepartureDate().before(endDate))
						||(s.getDepartureDate().compareTo(departureDate))==0 ) {
						detailList.add(t);
				}
			}
		}
		return detailList;
	}
	
	
}
