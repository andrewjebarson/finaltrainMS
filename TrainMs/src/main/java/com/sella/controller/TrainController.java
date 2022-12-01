package com.sella.controller;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sella.dao.TrainDao;
import com.sella.exception.IdFoundException;
import com.sella.exception.IdNotFoundException;
import com.sella.model.Detail;
import com.sella.model.Error;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","*"})
public class TrainController {
	@Autowired
	TrainDao appDao;
	
	@PostMapping("addTrain")
	public ResponseEntity addTrain(@RequestBody Detail detail ) {
		try {
			appDao.registerTrain(detail);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			throw new IdFoundException("Resource already found");
		}	
	}
	
	
	
	
	@DeleteMapping("deleteTrain/{trainId}")
	public ResponseEntity deleteTrain(@PathVariable int trainId) {	
		try {
			appDao.removeTrain(trainId);	
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e);
			throw new IdNotFoundException("Resource not found");
		}	
	}
	@PutMapping("updateTrain")
	public ResponseEntity updateTrain(@RequestBody Detail detail) {
		try {
			Detail d=appDao.modifyTrain(detail);
			return new ResponseEntity<>(d,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("Resource not found to modify details");
		}
	}
	@GetMapping("showallTrain")
	public ResponseEntity showallTrain() {
		try {
			List<Detail> d=appDao.getallTrain();
			return new ResponseEntity<>(d,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("Resource not found");
		}
	}
	@GetMapping("getTrainById/{trainId}")
	public ResponseEntity getTrainById(@PathVariable int trainId) {
		try {
			Detail d=appDao.searchTrainById(trainId);
			return new ResponseEntity<>(d,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("Resource not found");
		}
	}
	@GetMapping("getTrainByName/{trainName}")
	public ResponseEntity getTrainByName(@PathVariable String trainName) {
		try {
			List<Detail> details=appDao.searchTrainByName(trainName);
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("Resource not found");
		}
	}
	@GetMapping("getSource")
	public ResponseEntity getSource() {
		try {
			Set<String> details=appDao.searchSource();
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("source point not found");
		}
	}
	@GetMapping("getDestination/{fromStation}")
	public ResponseEntity getDestination(@PathVariable String fromStation) {
		try {
			Set<String> details=appDao.searchDestination(fromStation);
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("destination point not found");
		}
	}
	@GetMapping("getTrainByFromAndToStation/{fromStation}/{toStation}")
	public ResponseEntity getTrainByFromAndToStation(@PathVariable String fromStation, @PathVariable String toStation) {
		try {
			List<Detail> details=appDao.searchByFromAndToStation(fromStation, toStation);
			return new ResponseEntity<>(details,HttpStatus.OK);
		}catch (Exception e) {
			throw new IdNotFoundException("Trains not found for this place");
		}
	}
	@GetMapping("getTrainByStationAndDate/{fromStation}/{toStation}/{departureDate}")
	public ResponseEntity getTrainByStationAndDate(@PathVariable String fromStation, @PathVariable String toStation,@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) Date departureDate) {
        try {
			List<Detail> details=appDao.searchByStationandDate(fromStation, toStation,departureDate);
			return new ResponseEntity<>(details,HttpStatus.OK);	
		}catch (Exception e) {
			throw new IdNotFoundException("Trains not found for this place");
		}
    }
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity idException(Exception e) {
		Error er=new Error();
		er.setCode(HttpStatus.NOT_FOUND.toString());
		er.setErMsg(e.getMessage());
		return new ResponseEntity<>(er,HttpStatus.NOT_FOUND) ;	
	}
	@ExceptionHandler(IdFoundException.class)
	public ResponseEntity idfoundException(Exception e) {
		Error er=new Error();
		er.setCode(HttpStatus.FOUND.toString());
		er.setErMsg(e.getMessage());
		return new ResponseEntity<>(er,HttpStatus.FOUND) ;	
	}
	
	public ResponseEntity  apiFallBack(Exception e) {
		return new ResponseEntity("Please try after some time",HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	
}
