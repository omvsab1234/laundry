package com.customer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.OrderDetails;
import com.model.master.PreferanceModel;

@Service("customerServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerServiceImpl implements CustomerServiceInterface {

	@Autowired
	CustomerDaoInterface customerDaoInterface;
	
	@Override
	public List<String> getCustomerList() {
		return customerDaoInterface.getCustomerList();
	}

	@Override
	public List<String> getRateDetails(String serviceTypeID, String clothTypeID) {
		return customerDaoInterface.getRateDetails(serviceTypeID, clothTypeID);
	}
	
	@Override
	public List<String> getRateFromCustomerOrder(String customerContact, String serviceTypeID, String clothTypeID) {
		return customerDaoInterface.getRateFromCustomerOrder(customerContact, serviceTypeID, clothTypeID);
	}

	@Override
	public void saveCustomerDetails(CustomerDetailModel customerDetailModel) {
		customerDaoInterface.saveCustomerDetails(customerDetailModel);
	}

	@Override
	public List<String> searchCustomerContact(String keyword) {
		String str = keyword ;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < str.length(); i++) {
			Boolean flag = Character.isDigit(str.charAt(i));
			if (flag) {
				System.out.println("'" + str.charAt(i) + "' is a number");
				result = customerDaoInterface.searchCustomerContact(keyword);
			} else {
				System.out.println("'"+ str.charAt(i)+"' is a letter");
				result = customerDaoInterface.searchCustomerName(keyword);
			}
		}
		return result;
	}

	@Override
	public List<String> getCustomerContactDetails(String customerContact) {
		return customerDaoInterface.getCustomerContactDetails(customerContact);
	}
	
	@Override
	public List<String> getCustomerContactAllDetails(String customerContact) {
		return customerDaoInterface.getCustomerContactAllDetails(customerContact);
	}

	@Override
	public void updateCustomerDetails(int customerId, String customerMobile, int customerName, String customerAddress) {
		customerDaoInterface.updateCustomerDetails(customerId, customerMobile, customerName, customerAddress);
	}

	@Override
	public void deleteCustomerDetails(int customerId) {
		customerDaoInterface.deleteCustomerDetails(customerId);
	}

	@Override
	public CustomerDetailModel findCustomerID(String cId) {
		int cId1 = Integer.parseInt(cId);
		return customerDaoInterface.findCustomerID(cId1);
	}

	@Override
	public void saveCustomerOrder(CustomerOrderModel customerOrderModel) {
		customerDaoInterface.saveCustomerOrder(customerOrderModel);
	}
	
	@Override
	public void saveCustomerOrder2(CustomerOrderModel_2 customerOrderModel_2) {
		customerDaoInterface.saveCustomerOrder2(customerOrderModel_2);
	}

	@Override
	public void saveOrder(OrderDetails orderDetails) {
		//customerDaoInterface.updateSingleOrderStatus(orderDetails.getQrOrderId());
		customerDaoInterface.saveOrder(orderDetails);
	}

	@Override
	public void deleteOrder(int cId, String orderId, String clothTypeID, String serviceTypeID) {
		customerDaoInterface.deleteOrder(cId, orderId, clothTypeID, serviceTypeID);
	}

	@Override
	public List<String> getClothTypeDetails(String orderId) {
		return customerDaoInterface.getClothTypeDetails(orderId);
	}

	@Override
	public List<String> getServiceTypeList(String orderId,String clothTypeID) {
		return customerDaoInterface.getServiceTypeList(orderId,clothTypeID);
	}

	@Override
	public List<String> getQuantity(String orderId, String clothTypeID, String serviceTypeID) {
		return customerDaoInterface.getQuantity(orderId, clothTypeID, serviceTypeID);
	}


	@Override
	public void updateReturnOrder(int oId, int retrunQuatity) {
		customerDaoInterface.updateReturnOrder(oId, retrunQuatity);
		
	}

	@Override
	public List<String> getRemainingAmount(String orderId) {
		return customerDaoInterface.getRemainingAmount(orderId);
	}

	@Override
	public void updatePayment(String orderId, double amountPaid, double amountRemaining, int qrQuantity,double allRemainingAmt) {
		customerDaoInterface.updatePayment(orderId, amountPaid, amountRemaining, qrQuantity,allRemainingAmt);		
	}

	@Override
	public List<String> getAllOrdersList() {
		return customerDaoInterface.getAllOrdersList();
	}

	@Override
	public void updateOrderStatus(String orderId) {
		customerDaoInterface.updateOrderStatus(orderId);
	}

	@Override
	public void updateAllReamaingAmount(int cId, double qAmountRemainingAll) {
		customerDaoInterface.updateAllReamaingAmount(cId, qAmountRemainingAll);
	}

	@Override
	public List<String> getInvoiceNo() {
		return customerDaoInterface.getInvoiceNo();
	}
	
	@Override
	public CustomerDetailModel checkDuplicateCustomer(String mobileNo) {
		return customerDaoInterface.checkDuplicateCustomer(mobileNo);
	}
}
