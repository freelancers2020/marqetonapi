package com.marqeton.marqetonapi.service.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marqeton.marqetonapi.dao.admin.ConcernDAO;
import com.marqeton.marqetonapi.model.Concern;
import com.marqeton.marqetonapi.service.admin.ConcernService;

@Service("ConcernService")
public class ConcernServiceImpl implements ConcernService{
	
	@Autowired
	ConcernDAO concernDAO;

	@Override
	public Concern saveConcern(Concern concern) {
		concern.setCreatedOn(new Date());
		concern.setUpdatedOn(new Date());
		Concern savedConcern = concernDAO.saveConcern(concern);
		return savedConcern;
	}

	@Override
	public List<Concern> getConcerns() {
		List<Concern> concernList = concernDAO.getConcern();
		return concernList;
	}

	@Override
	public Concern getConcernById(Long id) {
		return concernDAO.getConcernById(id);
	}

	@Override
	public void deleteConcernById(Long id) {
		concernDAO.deleteConcernById(id);
		
	}

	@Override
	public Concern updateConcernById(Long id, Concern concern) { 
		Concern updatedConcern = concernDAO.updateConcernById(id, concern);
		return updatedConcern;
	}

}
