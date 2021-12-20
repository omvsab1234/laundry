package com.reports;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Barcode.BarcodeServiceInterface;
import com.customer.CustomerServiceInterface;

import net.sf.jasperreports.engine.JRException;
import util.PrintJasperReport;

@Controller
public class AllReportController {

	@Autowired
	BarcodeServiceInterface barcodeServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	// AllOrderReportPage
	@RequestMapping(value = "AllOrderReportPage")
	public ModelAndView AllOrderReportPage(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			List<String> AllOrderList = barcodeServiceInterface.getAllOrderList();
			model.addAttribute("AllOrderList", AllOrderList);
			return new ModelAndView("AllOrderReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
		
	// AllCustomersReportPage
	@RequestMapping(value = "AllCustomersReportPage")
	public ModelAndView AllCustomersReportPage(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			List<String> CustomerList;
			CustomerList = customerServiceInterface.getCustomerList();
			model.addAttribute("CustomerList", CustomerList);
			return new ModelAndView("AllCustomersReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// ******AllCutomers Report**********
	@RequestMapping(value = "AllCustomerReport")
	public ModelAndView AllCustomerReport(Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside AllCutomers report");
			String filename = "AllCutomers";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			PrintJasperReport.printreport(filename, request, response, hm);
			return new ModelAndView("");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	// ======================== MobileNoWiseReport =======================
	@RequestMapping(value = "MobileNoWiseReportPage")
	public ModelAndView MobileNoWiseReportPage(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			return new ModelAndView("MobileNoWiseReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
		
	@RequestMapping(value = "getMobileNoWiseReport")
	public ModelAndView getMobileNoWiseReport(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ParseException, JRException, NamingException, SQLException, IOException {
		System.out.println("IN SIDE get Mobile No Wise Report");
		HttpSession httpSession;
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			String mobileNo = request.getParameter("mobileNo");
			// String orderDate = request.getParameter("orderDate");
			System.out.println("mobileNo " + mobileNo);
			// use for jasper page report
			String filename1 = "MobileNoWiseReort";
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("mobileNo", mobileNo);

			System.out.println(hm);
			PrintJasperReport.printreport(filename1, request, response, hm);
			// end
			return new ModelAndView("MobileNoWiseReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
		
	// ******Paid Pending Report**********
	@RequestMapping(value = "paidPendingReport")
	public ModelAndView paidPending(Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside paidPending report");
			String filename = "paidPending";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			PrintJasperReport.printreport(filename, request, response, hm);
			return new ModelAndView("");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// ******Only Pending Report**********
	@RequestMapping(value = "allPaindingReport")
	public ModelAndView allPainding(Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("inside paidPending report");
			String filename = "allPending";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			PrintJasperReport.printreport(filename, request, response, hm);
			return new ModelAndView("");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//dailyCollectionReportPage
	@RequestMapping(value = "dailyCollectionReportPage")
	public ModelAndView dailyCollectionReportPage(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			return new ModelAndView("dailyCollectionReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//dailyCollectionReport
	@RequestMapping(value = "getDailyCollectionReport", method = RequestMethod.POST)
	public ModelAndView dailyCollectionReport(Model model, HttpServletRequest request, HttpSession httpSession,HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			String dateForDailyCollection=request.getParameter("dateForDailyCollection");
			
			 String filename = "dailyCollection";
			  HashMap<String, Object> hm = new HashMap<String, Object>();
			  hm.put("dateForDailyCollection", dateForDailyCollection);
			  
			  PrintJasperReport.printreport(filename, request, response, hm);
			
			return new ModelAndView("dailyCollectionReport");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	//fromDateToDateReportPage
	@RequestMapping(value = "fromDateToDateReportPage")
	public ModelAndView fromDateToDateReportPage(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			return new ModelAndView("fromDateToDateReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	// getFromToDateReport.html
	@RequestMapping(value = "getFromToDateReport", method = RequestMethod.POST)
	public ModelAndView getFromToDateReport(Model model, HttpServletRequest request, HttpSession httpSession,
			HttpServletResponse response) throws JRException, NamingException, SQLException, IOException {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String filename = "FromToDateOrdersReport";
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("fromDate", fromDate);
			hm.put("toDate", toDate);
			PrintJasperReport.printreport(filename, request, response, hm);
			return new ModelAndView("");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	//OrderIdWiseDetailsReportPage
	@RequestMapping(value = "OrderIdWiseDetailsReportPage")
	public ModelAndView OrderIdWiseDetailsReport(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			return new ModelAndView("OrderIdWiseDetailsReportPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	//generateOrderDetailsReort
	@RequestMapping(value = "generateOrderDetailsReort")
	public String generateOrderDetailsReort(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, JRException, NamingException, SQLException, IOException {
		System.out.println("IN SIDE generateOrderDetailsReort");
		String orderId = request.getParameter("orderId");
		String str = orderId ;
		int l = str.length(); 
		Boolean flag = Character.isDigit(str.charAt(0));
		if (flag) {
				System.out.println("'" + str.charAt(0) + "' is a number");
				System.out.println("Its Invoice No");
				int invoiceNo = Integer.parseInt(orderId);
				String filename1 = "InvoiceNoWiseDetailsReort";
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("invoiceNo", invoiceNo);
				System.out.println(hm);
				PrintJasperReport.printreport(filename1, request, response, hm);
		} else {
				System.out.println("'"+ str.charAt(0)+"' is a letter");
				System.out.println("Its Customer Name");
				String filename1 = "OrderIdWiseDetailsReort";
				HashMap<String, Object> hm = new HashMap<>();
				hm.put("orderId", orderId);
				System.out.println(hm);
				PrintJasperReport.printreport(filename1, request, response, hm);
		}
		return "OrderIdWiseDetailsReportView";
	}
}
