package com.admin;

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
import org.springframework.web.servlet.ModelAndView;

import com.model.master.ClothTypeModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Controller
public class AdminController {

	@Autowired
	AdminServiceInterface adminServiceInterface;
	
//========================= Rate Master =========================
	@RequestMapping(value = "RateMaster")
	public ModelAndView getRateMaster(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getRateMaster");
			//dropdown cloth list
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
			
			//dropdown Service List
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);
			
			List<String> RateList;
			RateList = adminServiceInterface.getRateList();
			model.addAttribute("RateList", RateList);
			
			List<String> ClothTypeList;
			ClothTypeList = adminServiceInterface.getClothTypeList();
			model.addAttribute("ClothTypeList", ClothTypeList);
			
			List<String> ServiceTypeList;
			ServiceTypeList = adminServiceInterface.getServiceTypeList();
			model.addAttribute("ServiceTypeList", ServiceTypeList);
			return new ModelAndView("RateMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "editRateDetails", params = "updateRate", method = RequestMethod.POST)
	public ModelAndView updateRate(Model model,HttpServletRequest request, HttpSession httpSession,
			@RequestParam("mRateId") int RateId, @RequestParam("mRate") double Rate) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Rate Master");
			
			adminServiceInterface.updateRate(RateId,Rate);
			// dropdown
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);

			// dropdown
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);

			List<String> RateList;
			RateList = adminServiceInterface.getRateList();
			model.addAttribute("RateList", RateList);
			return new ModelAndView("RateMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
	
	@RequestMapping(value = "editRateDetails", params = "deleteRate", method = RequestMethod.POST)
	public ModelAndView deleteRate(Model model,HttpServletRequest request, HttpSession httpSession,
			@RequestParam("mRateId") int RateId) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete Rate Master");
			
			adminServiceInterface.deleteRate(RateId);
			// dropdown
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);

			// dropdown
			HashMap<String, String> ServiceList;
			ServiceList = adminServiceInterface.getServiceList();
			model.addAttribute("ServiceList", ServiceList);

			List<String> RateList;
			RateList = adminServiceInterface.getRateList();
			model.addAttribute("RateList", RateList);
			return new ModelAndView("RateMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("accessD");
		}
	}
}
