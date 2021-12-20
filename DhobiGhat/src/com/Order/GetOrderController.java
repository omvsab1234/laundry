package com.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SendSMS.STCOPSMS;
import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;
import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.OrderDetails;
import com.model.ReturnOrderDetails;
import com.model.ReturnOrderEntryModel;

import net.sf.jasperreports.engine.JRException;
import util.PrintJasperReport;

@Controller
public class GetOrderController {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	List<String> keyword = new ArrayList<>();
	
	//SearchCustomerContactDetails
	@RequestMapping(value = "SearchCustomerContactDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> SearchCustomerContactDetails(HttpServletRequest request) {
		String CustomerContact = request.getParameter("CustomerContact");
		System.out.println("CustomerContact=> " + CustomerContact );
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.SearchCustomerContactDetails(CustomerContact);
		System.out.println(list);
		return list;
	}
	
	// searchCustomerMobileNo
	// Calling customer Autocomplete
	@RequestMapping("searchCustomerMobileNo")
	@ResponseBody
	public List<String> searchCustomerMobileNo(HttpServletRequest requet) {
		keyword = orderServiceInterface.searchCustomerMobileNo(requet.getParameter("term")); // Calling Autocomplete
																								// method
		return keyword;
	}

	// searchCustomerMobileNo
	// Calling customer Autocomplete
	@RequestMapping("searchCustomerContactAutoComplete")
	@ResponseBody
	public List<String> searchCustomerContactAutoComplete(HttpServletRequest requet) {
		keyword = orderServiceInterface.searchCustomerContactAutoComplete(requet.getParameter("term")); // Calling Autocomplete
																								// method
		return keyword;
	}

	//GetOrderPage
	@RequestMapping(value="GetOrderPage")
	public ModelAndView GetOrderPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("GetOrderPage");
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
			
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);
			List<String> preferanceList;
			preferanceList = adminServiceInterface.getPreferanceList();
			model.addAttribute("preferanceList", preferanceList);
			
			model.addAttribute("customerId", customerId);
			return new ModelAndView("GetOrderPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//saveCompleteOrder
	@RequestMapping(value = "saveCompleteOrder", method = RequestMethod.POST,params = "GetOrder")
	public ModelAndView saveCompleteOrder( @ModelAttribute("") OrderDetails orderDetails,Model model, HttpServletRequest request, 
			 HttpSession httpSession, HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside save CompleteOrder details");
			double NewBalance = Double.parseDouble(request.getParameter("NewBalance"));
			String cId=request.getParameter("cId");
			System.out.println("C Id-> "+cId+"NewBalance-> "+NewBalance);
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId);
			System.out.println("id set -> "+customerDetailModel);
			orderDetails.setCustomerDetailModel(customerDetailModel);
			customerServiceInterface.updateAllReamaingAmount(Integer.parseInt(cId),NewBalance);
			orderDetails.setNotReadyQty(orderDetails.getTotalQuantity());
			customerServiceInterface.saveOrder(orderDetails);
			System.out.println("Order SAVED...");
			
			SendSmsForGetOrder(orderDetails);
			
			String filename = "orderRecipts";
			  HashMap<String, Object> hm = new HashMap<String, Object>();
			  hm.put("orderId",orderDetails.getOrderId()); 
			  PrintJasperReport.printreport(filename, request, response, hm);
			
			/*String filename = "barcodeGenerator";
			  HashMap<String, Object> hm = new HashMap<String, Object>();
			  hm.put("orderId",orderDetails.getOrderId()); 
			  PrintJasperReport.printreport(filename, request, response, hm);*/
			
			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	public void SendSmsForGetOrder( OrderDetails orderDetails){
		String mobile="";
		String msg="";
		try {
			System.out.println("Sending Order Ready SMS");
			mobile = orderDetails.getCustomerDetailModel().getcMobile();
			msg = "Dear " + orderDetails.getCustomerDetailModel().getcName() + "%0AYour Invoice No "
					+ orderDetails.getInvoiceNo() + "%0ATot Cloths Qty :" + orderDetails.getTotalQuantity() + "%0ATot Amt :"
					+ orderDetails.getTotalAmount() + "%0APaid Amt :" + orderDetails.getAmountPaid() + "%0ARem Amt:"
					+ orderDetails.getAmountRemaining() + "%0AYour Due Date is "+orderDetails.getDueDate()+"%0AThank You..";

			System.out.println("Invoice No "+orderDetails.getInvoiceNo()+" mobile : " + mobile + " msg " + msg);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg);
			System.out.println("msg send success..");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "saveCompleteOrder", method = RequestMethod.POST,params = "OrderSlip")
	public ModelAndView getOrderSlip( OrderDetails orderDetails,Model model, HttpServletRequest request, 
			 HttpSession httpSession, HttpServletResponse response) throws JRException, NamingException, SQLException, IOException{
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
	
			System.out.println("inside OrderSlip report");		
			String orderId=request.getParameter("orderId");
			System.out.println("orderId:"+orderId);
			
			  String filename = "orderRecipts";
			  HashMap<String, Object> hm = new HashMap<String, Object>();
			  hm.put("orderId",orderId); 
			  PrintJasperReport.printreport(filename, request, response, hm);
			
			return new ModelAndView("SearchCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//CustomerDetailsPage
		@RequestMapping("CustomerDetailsPage")
		public ModelAndView CustomerDetailsPage(HttpServletRequest request, HttpSession httpSession, Model model){
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
				System.out.println("CustomerDetailsPage");
				String CustomerContact = request.getParameter("CustomerContact");
				System.out.println("CustomerContact "+CustomerContact);
				List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(CustomerContact);
				List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(CustomerContact);
				System.out.println("AllOrderDetails "+AllOrderDetails);
				model.addAttribute("CustomerInfo", CustomerInfo);
				model.addAttribute("AllOrderDetails", AllOrderDetails);
				return new ModelAndView("CustomerDetailsPage");
			}else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("mainLoginPage");
			}
		}
	
	//
	@RequestMapping(value="ReturnOrderPage")
	public ModelAndView ReturnOrderPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("ReturnOrderPage");
			int invoiceNo = Integer.parseInt(request.getParameter("i"));
			int totalQuantity = Integer.parseInt(request.getParameter("q"));
			String orderId = request.getParameter("o");
			String customerId = request.getParameter("c");
			System.out.println("invoiceNo "+invoiceNo+" orderId "+orderId+" customerId "+customerId);
			List<String> ClothList = orderServiceInterface.getClothListForReturn(invoiceNo,orderId,customerId);
			model.addAttribute("ClothList", ClothList);
			model.addAttribute("invoiceNo", invoiceNo);
			model.addAttribute("customerId", customerId);
			model.addAttribute("orderId", orderId);
			model.addAttribute("totalQuantity", totalQuantity);
			return new ModelAndView("ReturnOrderPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//getServiceTypeListForReturn
	@RequestMapping(value = "getServiceTypeListForReturn", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getServiceTypeListForReturn(HttpServletRequest request) {
		String ClothType = request.getParameter("ClothType");
		String orderId = request.getParameter("orderId");
		int cId = Integer.parseInt(request.getParameter("cId"));
		
		System.out.println("cId=> " + cId);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getServiceTypeList(orderId,ClothType,cId);
		System.out.println(list);
		return list;
	}
	
	//========== getQuantityForReturn  ===========	
		@RequestMapping(value = "getQuantityForReturn", method = RequestMethod.GET)
		@ResponseBody
		public List<String> getQuantityForReturn(HttpServletRequest request) {
			String ClothTypeID = request.getParameter("ClothTypeID");
			String orderId = request.getParameter("orderId");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			System.out.println("ServiceTypeID => "+ServiceTypeID);
			List<String> list = new ArrayList<>();
			list = orderServiceInterface.getQuantityForReturn(orderId,ClothTypeID,ServiceTypeID);
			System.out.println(list);
			return list;
		}
		
		
		@RequestMapping(value = "saveReturnOrderEntry", method = RequestMethod.GET)
		@ResponseBody
		public void saveReturnOrderEntry(HttpServletRequest request, @ModelAttribute("") ReturnOrderEntryModel returnOrderEntryModel) {
			String cId = request.getParameter("cId");
			
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId);
			returnOrderEntryModel.setCustomerDetailModel(customerDetailModel);
			orderServiceInterface.saveReturnOrderEntry(returnOrderEntryModel);
			System.out.println("okk");
		}
		
		
		@RequestMapping(value = "saveReturnOrder", method = RequestMethod.GET)
		@ResponseBody
		public void saveReturnOrder(HttpServletRequest request,
				@ModelAttribute("") ReturnOrderDetails returnOrderDetails) {
			
			String cId = request.getParameter("cId");
			
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId);
			returnOrderDetails.setCustomerDetailModel(customerDetailModel);
			orderServiceInterface.saveReturnOrder(returnOrderDetails);
			System.out.println("okk");
		}
		
		//deliverClothsOrder
		@RequestMapping(value = "deliverClothsOrder", method = RequestMethod.GET)
		public ModelAndView deliverClothsOrder(Model model, HttpServletRequest request, 
				 HttpSession httpSession, HttpServletResponse response) {
			httpSession = request.getSession();
			String UsName = (String) httpSession.getAttribute("UsernameAdmin");
			System.out.println(UsName + " is User");
			if (UsName != null) {
				System.out.println("inside deliver ClothsOrder details");
				String custom_radio_1 = request.getParameter("custom_radio_1");
				double balanceAmt = Double.parseDouble(request.getParameter("balanceAmt"));
				double paidAmt = Double.parseDouble(request.getParameter("paidAmt"));
				String cId=request.getParameter("cId2");
				
				System.out.println("custom_radio_1 "+custom_radio_1+" C Id-> "+cId+" balanceAmt "+balanceAmt+" paidAmt-> "+paidAmt);
				CustomerDetailModel customerDetailModel = new CustomerDetailModel();
				customerDetailModel = customerServiceInterface.findCustomerID(cId);
				System.out.println("id set -> "+customerDetailModel);
				orderServiceInterface.updateBalance(balanceAmt,paidAmt,cId);
				System.out.println("Order Delivered...");
				
				return new ModelAndView("SearchCustomerPage");
			} else {
				System.out.println("Invalid Admin...");
				model.addAttribute("Message", "First Login Your Account...");
				return new ModelAndView("accessD");
			}
		}
		
	// CancelCurrentOrder
	// Json get get Rate From Customer Order page
	@RequestMapping(value = "CancelCurrentOrder", method = RequestMethod.GET)
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, @ModelAttribute("") CustomerOrderModel customerOrderModel) {
		int cId = Integer.parseInt(request.getParameter("cId"));
		String orderId = request.getParameter("orderId");
		String ClothTypeID = request.getParameter("ClothTypeID");
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
		System.out.println("cId " + cId + " ClothTypeID " + ClothTypeID + " ServiceTypeID=> " + ServiceTypeID
				+ " orderId " + orderId);

		customerServiceInterface.deleteOrder(cId, orderId, ClothTypeID, ServiceTypeID);
	}
}
