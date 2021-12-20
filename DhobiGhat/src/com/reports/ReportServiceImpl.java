package com.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reportServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class ReportServiceImpl implements ReportServiceInterface {

	@Autowired
	ReportDaoInterface reportDaoInterface;

	@Override
	public List<String> searchOrderId(String keyword) {
		return reportDaoInterface.searchOrderId(keyword);
	}

	@Override
	public List<String> searchOrderDate(String keyword, String mobileNo) {
		System.out.println("Inside Service implemrnt mobileNo "+mobileNo+" keyword "+keyword);
		return reportDaoInterface.searchOrderDate( keyword, mobileNo);
	}

	@Override
	public List<String> getDailyCollectionList(String dateForDailyCollection) {
		// TODO Auto-generated method stub
		return reportDaoInterface.getDailyCollectionList( dateForDailyCollection);
	}	
}
