package com.Order;

import java.util.List;

import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

public interface OrderServiceInterface {

	List<String> SearchCustomerContactDetails(String customerContact);

	List<String> getCustomerInfo(String customerContact);

	List<String> getAllOrderDetails(String customerContact);

	List<String> getClothListForReturn(int invoiceNo, String orderId, String customerId);

	List<String> getServiceTypeList(String orderId, String clothType, int cId);

	List<String> getQuantityForReturn(String orderId, String clothTypeID, String serviceTypeID);

	void saveReturnOrderEntry(ReturnOrderEntryModel returnOrderEntryModel);

	void saveReturnOrder(ReturnOrderDetails returnOrderDetails);

	void updateBalance(double balanceAmt, double paidAmt, String cId);

	List<String> getCustomerInfo(int cId);

	List<String> getAllOrderDetails(int invoiceNo, String orderId);

	List<String> getOrderChiAllDetail(String orderId, int invoiceNo);

	void updateClothReadyStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId);

	void updateClothDeliveredStatus(String[] selectedClothOrderIds, int invoiceNo, String orderId, double balanceAmt,
			double paidAmt, int cId);
	
	public List<String> searchCustomerMobileNo(String keyword);

	public List<String> searchCustomerContactAutoComplete(String parameter);
}
