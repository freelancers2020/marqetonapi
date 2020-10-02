package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Option;

public interface OptionService {

	Option saveOption(Option option);

	List<Option> getOptions();

	Option updateOption(Option option);

	void deleteConcernById(Long id);

}
