package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oId;
	private String orderDate;
	private String orderId;
	private String dueDate;
	private int invoiceNo;
	private int totalQuantity;
	private int NotReadyQty;
	private int ReadyQty = 0;
	private int DeliveredQty = 0;
	private double totalAmount;
	private double amountPaid;
	private double amountRemaining;
	private double NewBalance;
	private String orderStatus="Not Ready";
	private int flag = 1;
	
	@OneToOne
	@JoinColumn(name="cId")
	CustomerDetailModel customerDetailModel;

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public double getNewBalance() {
		return NewBalance;
	}

	public void setNewBalance(double newBalance) {
		NewBalance = newBalance;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getAmountRemaining() {
		return amountRemaining;
	}

	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public CustomerDetailModel getCustomerDetailModel() {
		return customerDetailModel;
	}

	public void setCustomerDetailModel(CustomerDetailModel customerDetailModel) {
		this.customerDetailModel = customerDetailModel;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getNotReadyQty() {
		return NotReadyQty;
	}

	public void setNotReadyQty(int notReadyQty) {
		NotReadyQty = notReadyQty;
	}

	public int getReadyQty() {
		return ReadyQty;
	}

	public void setReadyQty(int readyQty) {
		ReadyQty = readyQty;
	}

	public int getDeliveredQty() {
		return DeliveredQty;
	}

	public void setDeliveredQty(int deliveredQty) {
		DeliveredQty = deliveredQty;
	}
	
	
}
