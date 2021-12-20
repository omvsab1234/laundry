package com.Order;

import java.util.ArrayList;
import java.util.List;

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

import com.model.CustomerDetailModel;

@Controller
public class ReturnOrderControllerNew {

	@Autowired
	OrderServiceInterface orderServiceInterface;
	
	//Customer_NotReadyPage
	@RequestMapping(value="Customer_NotReadyPage")
	public ModelAndView Customer_NotReadyPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("CustomerDetailsPage");
			int invoiceNo = Integer.parseInt(request.getParameter("i"));
			String orderId = request.getParameter("o");
			int cId = Integer.parseInt(request.getParameter("c"));
			System.out.println("i= "+invoiceNo+" o= "+orderId+" c= "+cId);
			List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(cId);
			List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(invoiceNo,orderId);
			
			//System.out.println("AllOrderDetails "+AllOrderDetails);
			model.addAttribute("CustomerInfo", CustomerInfo);
			model.addAttribute("AllOrderDetails", AllOrderDetails);
			return new ModelAndView("Customer_NotReadyPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//JSON
	//getOrderChiAllDetail
	@RequestMapping(value = "getOrderChiAllDetail", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getOrderChiAllDetail(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
		System.out.println("orderId=> " + orderId+" invoiceNo "+invoiceNo);
		List<String> list = new ArrayList<>();
		list = orderServiceInterface.getOrderChiAllDetail(orderId,invoiceNo);
		System.out.println(list);
		return list;
	}
	
	//SaveReadyClothsInOrder
	@RequestMapping(value = "SaveReadyClothsInOrder", params = "SaveReadyOrder", method = RequestMethod.POST)
	public ModelAndView SaveReadyClothsInOrder(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			try {
				System.out.println("Check Uncheck");
				int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
				String orderId = request.getParameter("orderId");
				String[] selectedClothOrderIds = request.getParameterValues("SelectedId");
				int cId = Integer.parseInt(request.getParameter("cId2"));

				orderServiceInterface.updateClothReadyStatus(selectedClothOrderIds, invoiceNo, orderId);
				List<String> CustomerInfo = orderServiceInterface.getCustomerInfo(cId);
				List<String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(invoiceNo, orderId);
				model.addAttribute("CustomerInfo", CustomerInfo);
				model.addAttribute("AllOrderDetails", AllOrderDetails);
				return new ModelAndView("Customer_NotReadyPage");
			} catch (Exception e) {
				model.addAttribute("Message", e);
				return new ModelAndView("mainLoginPage");
			}
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//
	//SaveReadyClothsInOrder
	@RequestMapping(value = "SaveReadyClothsInOrder",params = "DeliverClothOrder", method = RequestMethod.POST)
	public ModelAndView DeleverReadyClothsInOrder(Model model, HttpServletRequest request, 
			 HttpSession httpSession, HttpServletResponse response) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside deliver ClothsOrder details");
			System.out.println("Check Uncheck");
			int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
			String cMobile = request.getParameter("cMobile2");
			String orderId = request.getParameter("orderId");
			String[] selectedClothOrderIds = request.getParameterValues("SelectedId");
			String custom_radio_1 = request.getParameter("custom_radio_1");
			double balanceAmt = Double.parseDouble(request.getParameter("balanceAmt"));
			double paidAmt = Double.parseDouble(request.getParameter("paidAmt"));
			int cId=Integer.parseInt(request.getParameter("cId2"));
			
			System.out.println("custom_radio_1 "+custom_radio_1+" C Id-> "+cId+" balanceAmt "+balanceAmt+" paidAmt-> "+paidAmt);
			orderServiceInterface.updateClothDeliveredStatus(selectedClothOrderIds,invoiceNo,orderId,balanceAmt,paidAmt,cId);
			System.out.println("Order Delivered...");
			
			System.out.println("CustomerContact "+cMobile);
			List <String> CustomerInfo = orderServiceInterface.getCustomerInfo(cMobile);
			List <String> AllOrderDetails = orderServiceInterface.getAllOrderDetails(cMobile);
			System.out.println("AllOrderDetails "+AllOrderDetails);
			model.addAttribute("CustomerInfo", CustomerInfo);
			model.addAttribute("AllOrderDetails", AllOrderDetails);
			return new ModelAndView("CustomerDetailsPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
}
