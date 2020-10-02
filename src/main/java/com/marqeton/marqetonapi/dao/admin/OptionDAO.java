package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Option;

public interface OptionDAO {

	Option saveOption(Option option);

	List<Option> getOptions();

	void deleteOptionById(Long id);

}
