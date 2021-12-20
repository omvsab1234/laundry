package com.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;

@Controller
public class LoginPageController {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("getMainLogin")
	public ModelAndView FirstPage(HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		System.out.println("Login For Admin...");
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select *from numberdata");
		List l=query.list();
		if(l.isEmpty())
		{
			for(int i=1;i<=1000;i++)
			{
				SQLQuery query2=session.createSQLQuery("insert into numberdata values('"+i+"')");
				query2.executeUpdate();
			}
		}
		System.out.println("generate Number Data table");
		return new ModelAndView("mainLoginPage");
	}

	@RequestMapping(value="HomePageFromLogin",method = RequestMethod.POST)//,method = RequestMethod.POST
	public ModelAndView HomePage(Model model,HttpServletRequest request,HttpSession httpSession,
			@RequestParam("UserName") String UserName,
			@RequestParam("password") String password) {
		System.out.println("Home Page");
		System.out.println("User-Id-> "+UserName);
		System.out.println(" pwd-> "+password);

		if (UserName.equals("Admin") && password.equals("Admin")) {
			httpSession = request.getSession();
			httpSession.setAttribute("UsernameAdmin", UserName);
			System.out.println("Admin Logged in...");
			
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList();
			model.addAttribute("Todays_Orders", AllDetailsList.get("Todays_Orders"));
			model.addAttribute("Todays_Deliverys", AllDetailsList.get("Todays_Deliverys"));
			model.addAttribute("Todays_Cloths", AllDetailsList.get("Todays_Cloths"));
			model.addAttribute("todays_Amount", AllDetailsList.get("todays_Amount"));
			model.addAttribute("months_Amount", AllDetailsList.get("months_Amount"));
			//model.addAttribute("AllDetailsList", AllDetailsList);
			
			return new ModelAndView("DashboardPage");
		} else {
			model.addAttribute("Message", "Please Enter Correct UserName And Password");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//Dashboard
	@RequestMapping("Dashboard")
	public ModelAndView DashboardPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("DashboardPage");
			HashMap<String, String> AllDetailsList;
			AllDetailsList = adminServiceInterface.getAllDetailsList();
			model.addAttribute("Todays_Orders", AllDetailsList.get("Todays_Orders"));
			model.addAttribute("Todays_Deliverys", AllDetailsList.get("Todays_Deliverys"));
			model.addAttribute("Todays_Cloths", AllDetailsList.get("Todays_Cloths"));
			model.addAttribute("todays_Amount", AllDetailsList.get("todays_Amount"));
			model.addAttribute("months_Amount", AllDetailsList.get("months_Amount"));
			//model.addAttribute("AllDetailsList", AllDetailsList);
			return new ModelAndView("DashboardPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "HomeFromHome", method = RequestMethod.GET)
	public ModelAndView HomePageToShow(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Home Page To Show");
			return new ModelAndView("mainHomePage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}

	///////////////// OLD /////////////////////////
	@RequestMapping(value = "HomePageView", method = RequestMethod.GET)
	public ModelAndView HomePageView(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Home Page To Show");
			return new ModelAndView("HomePageView");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "homePageAction", params = "AddNewCustomer", method = RequestMethod.POST)
	public ModelAndView AddNewCustomer(Model model,HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			String str = request.getParameter("customer");
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
	
	@RequestMapping(value = "Logout", method = RequestMethod.GET)
	public ModelAndView LogoutPage(HttpServletRequest request,HttpSession httpSession) {
		System.out.println("Logout Page");
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " Session Distroyed..");
		httpSession.invalidate();
		return new ModelAndView("mainLoginPage");
	}
	
/*makeReamainingPayment.html*/
	
	@RequestMapping(value="makeReamainingPayment", method = RequestMethod.GET)
	public ModelAndView makeReamainingPayment(Model model,HttpServletRequest request,HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			int cId = Integer.parseInt(request.getParameter("cId2"));
			double payingAmount =Double.parseDouble(request.getParameter("payingAmount"));
			double paidAmt =Double.parseDouble(request.getParameter("paidAmt"));
			System.out.println("cId-> "+cId+" payingAmount-> "+payingAmount+" paidAmt-> "+paidAmt);
			adminServiceInterface.updateReamainingPayment(cId,payingAmount,paidAmt);
			System.out.println("SearchCustomerPage");
			return new ModelAndView("SearchCustomerPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
}
