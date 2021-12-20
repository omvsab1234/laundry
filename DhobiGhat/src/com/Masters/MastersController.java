package com.Masters;

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

import com.admin.AdminServiceInterface;
import com.customer.CustomerServiceInterface;
import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Controller
public class MastersController {

	@Autowired
	AdminServiceInterface adminServiceInterface;
	
	@Autowired
	CustomerServiceInterface customerServiceInterface;
	
	//PreferencesMasterPage
	@RequestMapping("PreferencesMasterPage")
	public ModelAndView PreferencesMasterPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("PreferencesMasterPage");
			List<String> preferanceList;
			preferanceList = adminServiceInterface.getPreferanceList();
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	// Save Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnAdd", method = RequestMethod.POST)
	public ModelAndView SavePreferanceType(Model model, @ModelAttribute("") PreferanceModel preferanceModel,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("getClothTypeMaster");
			PreferanceModel preferanceModel1 = adminServiceInterface
					.getDuplicatePreferance(preferanceModel.getPreferanceName());
			if (preferanceModel1 == null) {
				adminServiceInterface.savePreferance(preferanceModel);
				List<String> preferanceList;
				preferanceList = adminServiceInterface.getPreferanceList();
				model.addAttribute("preferanceList", preferanceList);
			} else {
				model.addAttribute("errorMsgPreferance", "Preferance is already added");
				List<String> preferanceList;
				preferanceList = adminServiceInterface.getPreferanceList();
				model.addAttribute("preferanceList", preferanceList);
			}
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// Update Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnUpdate", method = RequestMethod.POST)
	public ModelAndView UpdatePreferanceType(Model model,HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("UpdatePreferanceType");
			int preferanceId = Integer.parseInt(request.getParameter("preferanceId"));
			String preferanceName = request.getParameter("preferanceName");
			double preferancePrice = Double.parseDouble(request.getParameter("preferancePrice"));
			adminServiceInterface.updatePreferanceType(preferanceId,preferanceName,preferancePrice);
			List<String> preferanceList;
			preferanceList = adminServiceInterface.getPreferanceList();
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}

	// Delete Preferance Type
	@RequestMapping(value = "SaveUpdateDeletePreferanceType", params = "btnDelete", method = RequestMethod.POST)
	public ModelAndView DeletePreferanceType(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("DeletePreferanceType");
			int preferanceId = Integer.parseInt(request.getParameter("preferanceId"));
			adminServiceInterface.deletePreferanceType(preferanceId);
			List<String> preferanceList;
			preferanceList = adminServiceInterface.getPreferanceList();
			model.addAttribute("preferanceList", preferanceList);
			return new ModelAndView("PreferencesMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	
	//ClothTypeMasterPage
	@RequestMapping("ClothTypeMasterPage")
	public ModelAndView ClothTypeMasterPage(HttpServletRequest request, HttpSession httpSession, Model model){
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("ClothTypeMasterPage");
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		}else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//SaveUpdateDeleteClothType
	@RequestMapping(value = "SaveClothType",params = "btnAddCloth", method = RequestMethod.POST)
	public ModelAndView saveClothType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("saveClothType");
			ClothTypeModel clothTypeModel = new ClothTypeModel();
			clothTypeModel.setClothType(request.getParameter("clothType"));
			ClothTypeModel clothTypeModel1 = adminServiceInterface.getDuplicateClothType(clothTypeModel.getClothType());
			if (clothTypeModel1 == null) {
				adminServiceInterface.save(clothTypeModel);
			} else {
				model.addAttribute("errorMsgClothType", "ClothType is already added");
			}
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "SaveClothType",params = "btnUpdateCloth", method = RequestMethod.POST)
	public ModelAndView updateClothType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Cloth Type");
			int clothId = Integer.parseInt(request.getParameter("clothTypeID"));
			String clothTypeName = request.getParameter("clothType");
			adminServiceInterface.updateClothType(clothId, clothTypeName);
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "SaveClothType",params = "btnDeleteCloth", method = RequestMethod.POST)
	public ModelAndView deleteClothType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete ClothType");
			int clothId = Integer.parseInt(request.getParameter("clothTypeID"));
			adminServiceInterface.deleteClothType(clothId);
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//serviceTypeName
	@RequestMapping(value = "SaveServiceType",params = "btnAddService", method = RequestMethod.POST)
	public ModelAndView saveServiceType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("saveServiceType");
			ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
			serviceTypeModel.setServiceTypeName(request.getParameter("serviceTypeName"));
			ServiceTypeModel serviceTypeModel1 = adminServiceInterface.getDuplicateServiceType(serviceTypeModel.getServiceTypeName());	
			if (serviceTypeModel1 == null) {
			adminServiceInterface.saveServiceType(serviceTypeModel);
			}
			else{
				model.addAttribute("errorMsgServiceType", "ServiceType is already added");
			}
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "SaveServiceType",params = "btnUpdateCloth", method = RequestMethod.POST)
	public ModelAndView updateServiceType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Update Service Type");
			int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
			String serviceTypeName = request.getParameter("serviceTypeName");
			adminServiceInterface.updateServiceType(serviceTypeID, serviceTypeName);
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	@RequestMapping(value = "SaveClothType",params = "btnDeleteCloth", method = RequestMethod.POST)
	public ModelAndView deleteSeviceType(Model model,
			HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		if (UsName != null) {
			System.out.println("Delete Service Type");
			int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
			adminServiceInterface.deleteServiceType(serviceTypeID);
			HashMap<String, String> ClothList;
			ClothList = adminServiceInterface.getClothList();
			model.addAttribute("ClothList", ClothList);
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
			return new ModelAndView("ClothTypeMasterPage");
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
	
	//SaveRateMaster
	@RequestMapping(value = "saveRateMaster", method = RequestMethod.POST,params = "btnAddRate")
	public ModelAndView saveRate(Model model, HttpServletRequest request, HttpSession httpSession) {
		httpSession = request.getSession();
		String UsName = (String) httpSession.getAttribute("UsernameAdmin");
		System.out.println(UsName + " is User");
		int clothTypeID = Integer.parseInt(request.getParameter("clothTypeID"));
		int serviceTypeID = Integer.parseInt(request.getParameter("serviceTypeID"));
		double rate = Double.parseDouble(request.getParameter("rate"));
		if (UsName != null) {
			System.out.println("Save Rate Master");
			RateMasterModel rateMasterModel = new RateMasterModel();
			ClothTypeModel clothTypeModel = new ClothTypeModel();
			clothTypeModel = adminServiceInterface.findClothTypeiD(clothTypeID);
			rateMasterModel.setClothTypeModel(clothTypeModel);

			ServiceTypeModel serviceTypeModel = new ServiceTypeModel();
			serviceTypeModel = adminServiceInterface.findServiceTypeiD(serviceTypeID);
			rateMasterModel.setServiceTypeModel(serviceTypeModel);

			RateMasterModel rateMasterModel1 = adminServiceInterface.getDuplicateRate(rateMasterModel.getClothTypeModel(),rateMasterModel.getServiceTypeModel());	
			if (rateMasterModel1 == null) {
				rateMasterModel.setRate(rate);
				adminServiceInterface.saveRate(rateMasterModel);
			} else {
				model.addAttribute("errorMsgRateMaster", "Rate is already added");
			}
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
			
			return new ModelAndView("ClothTypeMasterPage");
			
		} else {
			System.out.println("Invalid Admin...");
			model.addAttribute("Message", "First Login Your Account...");
			return new ModelAndView("mainLoginPage");
		}
	}
}
