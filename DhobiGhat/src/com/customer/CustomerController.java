package com.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.SendSMS.STCOPSMS;
import com.admin.AdminServiceInterface;
import com.model.CustomerDetailModel;
import com.model.CustomerOrderModel;
import com.model.CustomerOrderModel_2;
import com.model.OrderDetails;
import com.model.master.RateMasterModel;

@Controller
public class CustomerController {

	@Autowired
	CustomerServiceInterface customerServiceInterface;
	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	List<String> keyword = new ArrayList<>();
	
	@RequestMapping(value="AddCustomer") 	////////////////////////// NEW 
	public ModelAndView AddCustomerPage(Model model, HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Add Customer Page");
			//dropdown cloth list
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
			//dropdown Service List
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);
			
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPageNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
//================== SaveCustomer =========================	New
	@RequestMapping(value="SaveNewCustomer", method = RequestMethod.POST)
	public ModelAndView SaveCustomer(Model model,@ModelAttribute("") CustomerDetailModel customerDetailModel,
			HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Save Customer Info");
			CustomerDetailModel customerDetailModel2 = new CustomerDetailModel();
			customerDetailModel2 = customerServiceInterface.checkDuplicateCustomer(customerDetailModel.getcMobile());
			if(customerDetailModel2 == null) {
				customerServiceInterface.saveCustomerDetails(customerDetailModel);
				SendSmsForNewCustomer(customerDetailModel);
			}else{
				model.addAttribute("msgError", "Customer Already Exist...");
				System.out.println("Duplicate Customer..");
			}
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	public void SendSmsForNewCustomer(CustomerDetailModel customerDetailModel){
		String mobile="";
		String msg="";
		String gender = "";
		try {
			System.out.println("Sending Order Ready SMS");
			mobile = customerDetailModel.getcMobile();
			if(customerDetailModel.getGender().equals("male")){
				gender = "Mr";
			}
			if(customerDetailModel.getGender().equals("female")){
				gender = "Mrs";
			}
			if(customerDetailModel.getGender().equals("ufemale")){
				gender = "Miss";
			}
			
			msg = "Dear "+gender+". "+customerDetailModel.getcName()+", Welcome To Per Clean,%0AThank You...";

			System.out.println("mobile : " + mobile + " msg " + msg);
			STCOPSMS stcopsms = new STCOPSMS();
			stcopsms.sendSMS(mobile, msg);
			System.out.println("msg send success..");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//editCustomerDetails
	@RequestMapping(value = "editCustomerDetails", params = "updateCustomer", method = RequestMethod.POST)
	public ModelAndView updateCustomer(Model model,  HttpServletRequest request, HttpSession httpSession,
			@RequestParam("mCustomerId") int CustomerId,
			@RequestParam("mCustomerMobile") String CustomerMobile,
			@RequestParam("mCustomerName") int CustomerName,
			@RequestParam("mCustomerAddress") String CustomerAddress) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("update Cutomer");
			customerServiceInterface.updateCustomerDetails(CustomerId, CustomerMobile,CustomerName,CustomerAddress);
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPageNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "editCustomerDetails", params = "deleteCustomer", method = RequestMethod.POST)
	public ModelAndView deleteCustomer(Model model,  HttpServletRequest request, HttpSession httpSession,
			@RequestParam("mCustomerId") int CustomerId) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("delete Cutomer");
			customerServiceInterface.deleteCustomerDetails(CustomerId);
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AddCustomerPageNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
//=========================================================================================saveClothOrder
	
	// Json get Menu list page
		@RequestMapping(value = "saveClothOrder", method = RequestMethod.GET)
		@ResponseBody
		public void saveClothOrder(HttpServletRequest request, @ModelAttribute("") CustomerOrderModel customerOrderModel) {
			String cId = request.getParameter("cId");
			int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
			String ClothTypeID = request.getParameter("ClothTypeID");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			String PreferecesName = request.getParameter("PreferecesName");
			String orderDate = request.getParameter("orderDate");
			String orderId = request.getParameter("orderId");
			double Rate = Double.parseDouble(request.getParameter("Rate"));
			int Quantity =Integer.parseInt(request.getParameter("Quantity"));
			double Amount = Double.parseDouble(request.getParameter("Amount"));
			CustomerOrderModel_2 customerOrderModel_2 =new CustomerOrderModel_2();
			
			CustomerDetailModel customerDetailModel = new CustomerDetailModel();
			customerDetailModel = customerServiceInterface.findCustomerID(cId);
			customerOrderModel.setCustomerDetailModel(customerDetailModel);
			customerOrderModel.setClothType(ClothTypeID);
			customerOrderModel.setServiceType(ServiceTypeID);
			customerOrderModel.setPreferanceType(PreferecesName);
			customerOrderModel.setRate(Rate);
			customerOrderModel.setAmount(Amount);
			customerOrderModel.setQuantity(Quantity);
			customerOrderModel.setOrderDate(orderDate);
			customerOrderModel.setOrderId(orderId);
			customerOrderModel.setInvoiceNo(invoiceNo);
			
			customerOrderModel_2.setCustomerDetailModel(customerDetailModel);
			customerOrderModel_2.setClothType(ClothTypeID);
			customerOrderModel_2.setServiceType(ServiceTypeID);
			customerOrderModel_2.setPreferanceType(PreferecesName);
			customerOrderModel_2.setRate(Rate);
			customerOrderModel_2.setAmount(Amount);
			customerOrderModel_2.setQuantity(Quantity);
			customerOrderModel_2.setOrderDate(orderDate);
			customerOrderModel_2.setOrderId(orderId);
			customerOrderModel_2.setInvoiceNo(invoiceNo);
			customerServiceInterface.saveCustomerOrder(customerOrderModel);
			customerServiceInterface.saveCustomerOrder2(customerOrderModel_2);
			System.out.println("okk");
		}
		
		
//===================================== getRate Details From Rate Master ====================	
	// Json get rate List from Rate Master page
	@RequestMapping(value = "getRateDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRateDetails(HttpServletRequest request,
			@ModelAttribute("") RateMasterModel rateMasterModel) {
		String ServiceTypeID = request.getParameter("ServiceTypeID");
		String ClothTypeID = request.getParameter("ClothTypeID");
		System.out.println("ClothTypeID=> " + ClothTypeID + " ServiceTypeID=> "+ ServiceTypeID);
		List<String> list = new ArrayList<>();
		list = customerServiceInterface.getRateDetails(ServiceTypeID, ClothTypeID);
		System.out.println(list);
		return list;
	}

//======================= getRateFromCustomerOrder =========================
	// Json get get Rate From Customer Order page
		@RequestMapping(value = "getRateFromCustomerOrder", method = RequestMethod.GET)
		@ResponseBody
		public List<String> getRateFromCustomerOrder(HttpServletRequest request,
				@ModelAttribute("") CustomerOrderModel customerOrderModel) {
			String CustomerId = request.getParameter("CustomerId");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			String ClothTypeID = request.getParameter("ClothTypeID");
			System.out.println("CustomerId "+CustomerId+" ClothTypeID " + ClothTypeID + " ServiceTypeID=> "+ ServiceTypeID);
			System.out.println("getting previous rate...");
			List<String> list = new ArrayList<>();
			list = customerServiceInterface.getRateFromCustomerOrder(CustomerId,ServiceTypeID, ClothTypeID);
			System.out.println(list);
			return list;
		}
	
//=========================  deleteOrder 
		// Json get get Rate From Customer Order page 
		@RequestMapping(value = "cancelClothOrder", method = RequestMethod.GET)
		@ResponseBody
		public void deleteOrder(HttpServletRequest request,
			@ModelAttribute("") CustomerOrderModel customerOrderModel) {
			int cId = Integer.parseInt(request.getParameter("cId"));
			String orderId = request.getParameter("orderId");
			String ClothTypeID = request.getParameter("ClothTypeID");
			String ServiceTypeID = request.getParameter("ServiceTypeID");
			System.out.println("New cId "+cId+" ClothTypeID " + ClothTypeID + " ServiceTypeID=> "+ ServiceTypeID+" orderId "+orderId);
			
			customerServiceInterface.deleteOrder(cId,orderId,ClothTypeID,ServiceTypeID);
		}
	
		
		// Calling stock Autocomplete	  ========== deleteOrder
					@RequestMapping("/searchCustomerContact")
					@ResponseBody
					public List<String> autoContactcomplete(HttpServletRequest requet) {
						keyword = customerServiceInterface.searchCustomerContact(requet.getParameter("term")); 
																											
						return keyword;
					}
			
			//===== getCustomerDetails
			// Json CustomerDetails
			@RequestMapping(value = "getCustomerContactDetails", method = RequestMethod.GET)
			@ResponseBody
			public List<String> getCustomerContactDetails(HttpServletRequest request, @ModelAttribute("") CustomerDetailModel customerDetailModel) {
				String CustomerId = request.getParameter("CustomerId");
				System.out.println("CustomerId=" + CustomerId);
				List<String> list = new ArrayList<>();
					list = customerServiceInterface.getCustomerContactDetails(CustomerId);
					System.out.println(list);
				return list;
			}
			
			//getInvoiceNo
			@RequestMapping(value = "getInvoiceNo", method = RequestMethod.GET)
			@ResponseBody
			public List<String> getInvoiceNo(HttpServletRequest request, @ModelAttribute("") CustomerDetailModel customerDetailModel) {
				List<String> list = new ArrayList<>();
					list = customerServiceInterface.getInvoiceNo();
					System.out.println(list);
				return list;
			}
			
			// Json Customer All Details
						@RequestMapping(value = "getCustomerContactAllDetails", method = RequestMethod.GET)
						@ResponseBody
						public List<String> getCustomerContactAllDetails(HttpServletRequest request, @ModelAttribute("") CustomerDetailModel customerDetailModel) {
							String CustomerContact = request.getParameter("CustomerContact");
							System.out.println("CustomerContact = " + CustomerContact);
							List<String> list = new ArrayList<>();
								list = customerServiceInterface.getCustomerContactAllDetails(CustomerContact);
								System.out.println(list);
							return list;
						}
			
//======================= Get Order =================================== 
	@RequestMapping(value = "GetOrderNew")
	public ModelAndView GetOrder(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Get Order Customer Page");
			// dropdown cloth list
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
			// dropdown Service List
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);

			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("GetOrderNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value="allOrders")
	public ModelAndView allOrders(Model model, HttpServletRequest request, HttpSession httpSession){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("all Orders Page");
			
			List<String> allOrders;
			allOrders = customerServiceInterface.getAllOrdersList();
			model.addAttribute("allOrders", allOrders);
			System.out.println("ALl Orders : "+allOrders);
			return new ModelAndView("AllOrdersPageNew");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	} 
}
