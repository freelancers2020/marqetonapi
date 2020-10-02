package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Concern;

public interface ConcernDAO {

	Concern saveConcern(Concern concern);

	List<Concern> getConcern();

	Concern getConcernById(Long id);

	public void deleteConcernById(Long id);

	Concern updateConcernById(Long id, Concern concern);

}
