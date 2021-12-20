package com.reports;

import java.util.List;

public interface ReportDaoInterface {

	List<String> searchOrderId(String keyword);

	List<String> searchOrderDate(String keyword, String mobileNo);

	List<String> getDailyCollectionList(String dateForDailyCollection);
}
