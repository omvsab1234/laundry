package com.admin;

import java.util.HashMap;
import java.util.List;

import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

public interface AdminDaoInterface {

	void save(ClothTypeModel clothTypeModel);
	List<String> getClothTypeList();
	void updateClothType(int clothId, String clothType);
	void deleteClothType(int clothId);
	ClothTypeModel getDuplicateClothType(String clothType);

	
	void saveServiceType(ServiceTypeModel serviceTypeModel);
	List<String> getServiceTypeList();
	void updateServiceType(int serviceId, String serviceTypeName);
	void deleteServiceType(int serviceId);
	ServiceTypeModel getDuplicateServiceType(String serviceTypeName);

	
	List<String> getRateList();
	HashMap<String, String> getClothList();
	HashMap<String, String> getServiceList();
	void saveRate(RateMasterModel rateMasterModel);
	void deleteRate(int rateId);
	void updateRate(int rateId, double rate);
	RateMasterModel getDuplicateRate(ClothTypeModel clothTypeModel, ServiceTypeModel serviceTypeModel);

	ClothTypeModel findClothTypeiD(int clothTypeID);
	ServiceTypeModel findServiceTypeiD(int serviceTypeID);
	
	void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid, double lastRem);
	PreferanceModel getDuplicatePreferance(String preferanceName);
	void savePreferance(PreferanceModel preferanceModel);
	List<String> getPreferanceList();
	
	void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice);
	void deletePreferanceType(int preferanceId);
	HashMap<String, String> getAllDetailsList();
	void updateReamainingPayment(int cId, double NewAmt);
}
