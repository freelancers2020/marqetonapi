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
import com.marqeton.marqetonapi.model.Option;
import com.marqeton.marqetonapi.service.admin.OptionService;

@CrossOrigin
@RestController
public class OptionController {

	@Autowired
	OptionService optionService;
	
	@RequestMapping(value = RestUrlConstants.ADMIN_OPTIONS, method = RequestMethod.POST)
	public ResponseEntity<Option> saveOption(@RequestBody Option option) {
		Option savedOption = optionService.saveOption(option);
		
		return new ResponseEntity<Option>(savedOption, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_OPTIONS, method = RequestMethod.PUT)
	public ResponseEntity<Option> updateOption(@RequestBody Option option) {
		Option savedOption = optionService.updateOption(option); 
		
		return new ResponseEntity<Option>(savedOption, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_OPTIONS, method = RequestMethod.GET)
	public ResponseEntity<List<Option>> getOptions() {
		List<Option> optionList = optionService.getOptions();
		
		return new ResponseEntity<List<Option>>(optionList, HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_OPTION, method = RequestMethod.DELETE)
	public ResponseEntity<Concern> deleteOptionById(@PathVariable("id") Long id){
		optionService.deleteConcernById(id);
		return new ResponseEntity<Concern>(HttpStatus.ACCEPTED);
	}
}
