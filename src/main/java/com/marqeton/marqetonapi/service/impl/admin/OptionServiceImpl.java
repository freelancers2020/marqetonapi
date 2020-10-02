package com.marqeton.marqetonapi.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marqeton.marqetonapi.dao.admin.OptionDAO;
import com.marqeton.marqetonapi.model.Option;
import com.marqeton.marqetonapi.service.admin.OptionService;

@Service("OptionService")
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	OptionDAO optionDAO;
	
	@Override
	public Option saveOption(Option option) {
		option.setCreatedOn(new Date());
		option.setUpdatedOn(new Date());
		Option savedOption = optionDAO.saveOption(option);
		return savedOption;
	}

	@Override
	public List<Option> getOptions() {
		List<Option> optionList = optionDAO.getOptions();
		return optionList;
	}

	@Override
	public Option updateOption(Option option) {

		option.setUpdatedOn(new Date());
		Option savedOption = optionDAO.saveOption(option);
		return savedOption;
	}

	@Override
	public void deleteConcernById(Long id) {
		
		optionDAO.deleteOptionById(id);
	}

}
