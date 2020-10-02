package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.OptionDAO;
import com.marqeton.marqetonapi.model.Option;
import com.marqeton.marqetonapi.repository.admin.OptionRepository;

@Repository("OptionRepository")
public class OptionDAOImpl implements OptionDAO{

	@Autowired
	OptionRepository optionRepository;
	@Override
	public Option saveOption(Option option) {
		return optionRepository.save(option);
	}
	@Override
	public List<Option> getOptions() {
		return new ArrayList<Option> ((List<Option>)optionRepository.findAll());
	}
	@Override
	public void deleteOptionById(Long id) {
		optionRepository.deleteById(id);
	}

}
