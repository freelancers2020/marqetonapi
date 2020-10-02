package com.marqeton.marqetonapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marqeton.marqetonapi.constants.RestUrlConstants;
import com.marqeton.marqetonapi.model.Concern;
import com.marqeton.marqetonapi.service.admin.ConcernService;

@CrossOrigin
@RestController
public class ConcernController {
	
	@Autowired
	ConcernService concernService;
	
	@RequestMapping(value = RestUrlConstants.ADMIN_CONCERNS, method = RequestMethod.POST)
	public ResponseEntity<Concern> saveConcern(@RequestBody Concern concern){
		
		Concern savedConcern = concernService.saveConcern(concern);
		return new ResponseEntity<Concern>(savedConcern, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_CONCERNS, method = RequestMethod.GET)
	public ResponseEntity<List<Concern>> getConcerns() {
		
		List<Concern> concernList = concernService.getConcerns();
		
		return new ResponseEntity<List<Concern>>(concernList, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_CONCERN, method = RequestMethod.GET)
	public ResponseEntity<Concern> getConcernById(@PathVariable("id")Long id){
		Concern savedConcern = concernService.getConcernById(id);
		
		return new ResponseEntity<Concern>(savedConcern, HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_CONCERN, method = RequestMethod.DELETE)
	public ResponseEntity<Concern> deleteConcernById(@PathVariable("id") Long id){
		concernService.deleteConcernById(id);
		return new ResponseEntity<Concern>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(value = RestUrlConstants.ADMIN_CONCERN, method = RequestMethod.PUT)
	public ResponseEntity<Concern> updateConcernById(@PathVariable(value = "id") Long id, @RequestBody Concern concern){
		Concern updateConcern = concernService.updateConcernById(id, concern);
			return new ResponseEntity<Concern>(updateConcern,HttpStatus.OK);
	}

}
