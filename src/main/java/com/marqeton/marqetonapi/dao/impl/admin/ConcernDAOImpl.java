package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.ConcernDAO;
import com.marqeton.marqetonapi.model.Concern;
import com.marqeton.marqetonapi.repository.admin.ConcernRepository;

@Repository("ConcernRepository")
public class ConcernDAOImpl implements ConcernDAO{

	@Autowired
	ConcernRepository concernRepository;

	@Override
	public Concern saveConcern(Concern concern) {
		return concernRepository.save(concern);
	}

	@Override
	public List<Concern> getConcern() {
		return new ArrayList<Concern> ((List<Concern>)concernRepository.findAll());
	}

	@Override
	public Concern getConcernById(Long id) {
		return concernRepository.findById(id).get();
	}

	@Override
	public void deleteConcernById(Long id) {
		concernRepository.deleteById(id);

	}

	@Override
	public Concern updateConcernById(Long id, Concern concern) {
		Concern fetchConcern = concernRepository.findById(id).get();
		fetchConcern.setName(concern.getName());
		fetchConcern.setDescription(concern.getDescription());
		fetchConcern.setStatus(concern.getStatus());
		fetchConcern.setUpdatedOn(new Date());
		Concern updatedConcern = concernRepository.save(fetchConcern);
		return updatedConcern;
	}

}
