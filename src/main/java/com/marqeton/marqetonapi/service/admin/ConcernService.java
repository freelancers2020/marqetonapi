package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Concern;

public interface ConcernService {

	Concern saveConcern(Concern concern);

	List<Concern> getConcerns();

	Concern getConcernById(Long id);

	public void deleteConcernById(Long id);

	Concern updateConcernById(Long id, Concern concern);

}
