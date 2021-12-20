package com.admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Service("adminServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminServiceInterface {

	@Autowired
	AdminDaoInterface adminDaoInterface;

	@Override
	public void save(ClothTypeModel clothTypeModel) {
		adminDaoInterface.save(clothTypeModel);
	}

	@Override
	public List<String> getClothTypeList() {
		return adminDaoInterface.getClothTypeList();
	}

	@Override
	public void updateClothType(int clothId, String clothType) {
		adminDaoInterface.updateClothType(clothId, clothType);
	}

	@Override
	public void deleteClothType(int clothId) {
		adminDaoInterface.deleteClothType(clothId);
	}

	@Override
	public void saveServiceType(ServiceTypeModel serviceTypeModel) {
		adminDaoInterface.saveServiceType(serviceTypeModel);
	}

	@Override
	public List<String> getServiceTypeList() {
		return adminDaoInterface.getServiceTypeList();
	}

	@Override
	public void updateServiceType(int serviceId, String serviceTypeName) {
		adminDaoInterface.updateServiceType(serviceId, serviceTypeName);
	}

	@Override
	public void deleteServiceType(int serviceId) {
		adminDaoInterface.deleteServiceType(serviceId);
	}

	@Override
	public List<String> getRateList() {
		return adminDaoInterface.getRateList();
	}

	@Override
	public HashMap<String, String> getClothList() {
		return adminDaoInterface.getClothList();
	}

	@Override
	public HashMap<String, String> getServiceList() {
		return adminDaoInterface.getServiceList();
	}

	@Override
	public void saveRate(RateMasterModel rateMasterModel) {
		adminDaoInterface.saveRate(rateMasterModel);
	}
	
	@Override
	public void deleteRate(int rateId) {
		adminDaoInterface.deleteRate(rateId);
	}

	@Override
	public void updateRate(int rateId, double rate) {
		adminDaoInterface.updateRate(rateId, rate);
	}

	@Override
	public ClothTypeModel findClothTypeiD(int clothTypeID) {
		return adminDaoInterface.findClothTypeiD(clothTypeID);
	}

	@Override
	public ServiceTypeModel findServiceTypeiD(int serviceTypeID) {
		return adminDaoInterface.findServiceTypeiD(serviceTypeID);
	}

	@Override
	public ClothTypeModel getDuplicateClothType(String clothType) {
		return adminDaoInterface.getDuplicateClothType(clothType);
	}

	@Override
	public ServiceTypeModel getDuplicateServiceType(String serviceTypeName) {
		
		return adminDaoInterface.getDuplicateServiceType(serviceTypeName);
	}
	
	@Override
	public RateMasterModel getDuplicateRate(ClothTypeModel clothTypeModel, ServiceTypeModel serviceTypeModel) {
		
		return adminDaoInterface.getDuplicateRate(clothTypeModel, serviceTypeModel);
	}

	@Override
	public void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid,
			double lastRem) {
		adminDaoInterface.updateReamainingPayment(cMobile,remAmt,orderId,lastPaid,lastRem);
	}

	@Override
	public PreferanceModel getDuplicatePreferance(String preferanceName) {
		return adminDaoInterface.getDuplicatePreferance(preferanceName);
	}

	@Override
	public void savePreferance(PreferanceModel preferanceModel) {
		adminDaoInterface.savePreferance(preferanceModel);
	}

	@Override
	public List<String> getPreferanceList() {
		return adminDaoInterface.getPreferanceList();
	}

	@Override
	public void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice) {
		adminDaoInterface.updatePreferanceType( preferanceId, preferanceName, preferancePrice);
	}

	@Override
	public void deletePreferanceType(int preferanceId) {
		adminDaoInterface.deletePreferanceType(preferanceId);
	}

	@Override
	public HashMap<String, String> getAllDetailsList() {
		return adminDaoInterface.getAllDetailsList();
	}

	@Override
	public void updateReamainingPayment(int cId, double payingAmount, double paidAmt) {
		adminDaoInterface.updateReamainingPayment( cId, (payingAmount - paidAmt));
	}
}
