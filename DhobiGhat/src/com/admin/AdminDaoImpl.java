
package com.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.master.ClothTypeModel;
import com.model.master.PreferanceModel;
import com.model.master.RateMasterModel;
import com.model.master.ServiceTypeModel;

@Repository("adminDaoInterface")
public class AdminDaoImpl implements AdminDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	
	Session session;

	@Override
	public void save(ClothTypeModel clothTypeModel) {
		session=sessionFactory.openSession();
		session.save(clothTypeModel);
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public List<String> getClothTypeList() {
		List<String> ClothTypeList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from ClothTypeModel c where c.flag=1");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ClothTypeList=query.list();
		session.close();
		return ClothTypeList;
	}

	@Override
	public void updateClothType(int clothId, String clothType) {
		System.out.println("Inside updateClothType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ClothTypeModel c set c.clothType='"+clothType+"' where c.clothTypeID="+clothId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Cloth Type Updated...");
	}

	@Override
	public void deleteClothType(int clothId) {
		System.out.println("Inside delete ClothType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ClothTypeModel c set c.flag=0 where clothTypeID="+clothId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Cloth Type Deleted...");
	}
//====================== Service Type ========================
	@Override
	public void saveServiceType(ServiceTypeModel serviceTypeModel) {
		session=sessionFactory.openSession();
		session.save(serviceTypeModel);
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public List<String> getServiceTypeList() {
		List<String> ServiceTypeList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from ServiceTypeModel s where s.flag=1");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		ServiceTypeList=query.list();
		session.close();
		return ServiceTypeList;
	}

	@Override
	public void updateServiceType(int serviceId, String serviceTypeName) {
		System.out.println("Inside updateServiceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ServiceTypeModel s set s.serviceTypeName='"+serviceTypeName+"' where s.serviceTypeID="+serviceId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Service Type Updated...");
	}

	@Override
	public void deleteServiceType(int serviceId) {
		System.out.println("Inside delete Service Type Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update ServiceTypeModel s set s.flag=0 where s.serviceTypeID="+serviceId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Service Type Deleted...");
	}

	@Override
	public List<String> getRateList() {
		  List<String> ServiceTypeList= new ArrayList<String>();
		  session=sessionFactory.openSession();
		  SQLQuery query = session.createSQLQuery("select c.clothType,s.serviceTypeName,r.rate,r.rateId from ratemastermodel r " +
		  		"inner join clothtypemodel c on r.clothTypeID=c.clothTypeID\r\n" + 
		  		"inner join servicetypemodel s on r.serviceTypeID=s.serviceTypeID");
		  query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		  ServiceTypeList=query.list();
		  session.close();
		  return ServiceTypeList;
	}

	@Override
	public HashMap<String, String> getClothList() {
		List<HashMap> list = new ArrayList<HashMap>();
		@SuppressWarnings("rawtypes")	
		HashMap mapOfCloth = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT c.clothTypeID,c.clothType FROM  ClothTypeModel c");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();

		System.out.println("get cloth Type table: " + list);

		for (HashMap map : list) {
			mapOfCloth.put(map.get("clothTypeID"), map.get("clothType"));
		}
		session.close();
		return mapOfCloth;
	}

	@Override
	public HashMap<String, String> getServiceList() {
		List<HashMap> list = new ArrayList<HashMap>();
		@SuppressWarnings("rawtypes")	
		HashMap mapOfService = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT s.serviceTypeID,s.serviceTypeName FROM  ServiceTypeModel s");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();

		System.out.println("get Service table: " + list);

		for (HashMap map : list) {
			mapOfService.put(map.get("serviceTypeID"), map.get("serviceTypeName"));
		}
		session.close();
		return mapOfService;
	}

	@Override
	public void saveRate(RateMasterModel rateMasterModel) {
		session =sessionFactory.openSession();
		session.save(rateMasterModel);
		session.beginTransaction().commit();
		session.close();
		System.out.println("Rate Saved...");
	}
	
	@Override
	public void deleteRate(int rateId) {
		System.out.println("Inside delete Rate Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("delete from RateMasterModel where rateId="+rateId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Rate Deleted...");
	}

	@Override
	public void updateRate(int rateId, double rate) {
		System.out.println("Inside update Rate Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update RateMasterModel set rate='"+rate+"' where rateId="+rateId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Rate Updated...");
	}
	
	@Override
	public ClothTypeModel findClothTypeiD(int clothTypeID) {
		ClothTypeModel clothTypeModel=new ClothTypeModel();
		Session session=sessionFactory.openSession();
		clothTypeModel=(ClothTypeModel)session.get(ClothTypeModel.class, clothTypeID);
		session.close();
		return clothTypeModel;
	}

	@Override
	public ServiceTypeModel findServiceTypeiD(int serviceTypeID) {
		ServiceTypeModel serviceTypeModel=new ServiceTypeModel();
		Session session=sessionFactory.openSession();
		serviceTypeModel=(ServiceTypeModel)session.get(ServiceTypeModel.class, serviceTypeID);
		session.close();
		return serviceTypeModel;
	}

	@Override
	public ClothTypeModel getDuplicateClothType(String clothType) {
		System.out.println("inside ClothType duplicate dao");
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ClothTypeModel.class);
		Criterion cr1 = Restrictions.eq("clothType", clothType);
		criteria.add(cr1);

		ClothTypeModel clothTypeModel = (ClothTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside clothType duplicate dao end");
		session.close();
		return clothTypeModel;
	}

	@Override
	public ServiceTypeModel getDuplicateServiceType(String serviceTypeName) {
		System.out.println("inside ServiceType duplicate dao");
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(ServiceTypeModel.class);
		Criterion cr1 = Restrictions.eq("serviceTypeName", serviceTypeName);
		criteria.add(cr1);

		ServiceTypeModel serviceTypeModel = (ServiceTypeModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return serviceTypeModel;
	}

	@Override
	public RateMasterModel getDuplicateRate(ClothTypeModel clothTypeModel, ServiceTypeModel serviceTypeModel) {
		System.out.println("inside Rate duplicate dao");
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(RateMasterModel.class);
		Criterion cr1 = Restrictions.eq("clothTypeModel", clothTypeModel);
		Criterion cr2 = Restrictions.eq("serviceTypeModel", serviceTypeModel);
		criteria.add(cr1);
		criteria.add(cr2);
		
		RateMasterModel rateMasterModel = (RateMasterModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside Rate duplicate dao end");
		session.close();
		
		return rateMasterModel;
	}

	public void updateCustomerReamainingPayment(String cMobile, double remAmt) {
		System.out.println("Inside update Reamaining Payment");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update customerdetailmodel c set c.cAmount='"+remAmt+"' where c.cMobile='"+cMobile+"'");
		query.executeUpdate();
		session.close();
		System.out.println("Reamaining Payment Updated...");
	}

	@Override
	public void updateReamainingPayment(String cMobile, double remAmt, String orderId, double lastPaid,
			double lastRem) {
		System.out.println("Inside update Order");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update orderdetails o set o.amountPaid='"+lastPaid+"', o.amountRemaining='"+lastRem+"' where o.qrOrderId='"+orderId+"'");
		query.executeUpdate();
		session.close();
		System.out.println("Order Updated...& goes to Reamaining Payment Updating...");
		updateCustomerReamainingPayment(cMobile,remAmt);
	}

	@Override
	public PreferanceModel getDuplicatePreferance(String preferanceName) {
		System.out.println("inside ServiceType duplicate dao");
		Criteria criteria = null;
		Session session = sessionFactory.openSession();
		criteria = session.createCriteria(PreferanceModel.class);
		Criterion cr1 = Restrictions.eq("preferanceName", preferanceName);
		criteria.add(cr1);
		PreferanceModel preferanceModel = (PreferanceModel) criteria.setMaxResults(1).uniqueResult();
		System.out.println("inside ServiceType duplicate dao end");
		session.close();
		return preferanceModel;
	}

	@Override
	public void savePreferance(PreferanceModel preferanceModel) {
		session=sessionFactory.openSession();
		session.save(preferanceModel);
		session.beginTransaction().commit();
		session.close();
	}

	@Override
	public List<String> getPreferanceList() {
		List<String> PreferanceList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select * from PreferanceModel p where p.flag=1");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		PreferanceList=query.list();
		session.close();
		return PreferanceList;
	}

	@Override
	public void updatePreferanceType(int preferanceId, String preferanceName, double preferancePrice) {
		System.out.println("Inside updatePreferanceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update PreferanceModel p set p.preferanceName='"+preferanceName+"',p.preferancePrice="+preferancePrice+" where p.id="+preferanceId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Preferance Type Updated...");
	}

	@Override
	public void deletePreferanceType(int preferanceId) {
		System.out.println("Inside deletePreferanceType Dao");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update PreferanceModel p set p.flag=0 where p.id="+preferanceId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Preferance Type Deleted...");
	}

	@Override
	public HashMap<String, String> getAllDetailsList() {
		List<HashMap> list = new ArrayList<HashMap>();
			
		HashMap mapOfAllDetails = new HashMap();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT count(o.orderId) as Todays_Deliverys FROM orderdetails o WHERE o.dueDate = CURDATE()");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Deliverys", map.get("Todays_Deliverys"));
		}
		
		query = session.createSQLQuery("SELECT count(o.orderId) as Todays_Orders FROM orderdetails o WHERE o.orderDate = CURDATE()");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Orders", map.get("Todays_Orders"));
		}
		
		query = session.createSQLQuery("SELECT sum(o.totalQuantity) as QTY FROM orderdetails o WHERE o.orderDate = CURDATE()");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("Todays_Cloths", map.get("QTY"));
		}
		
		query = session.createSQLQuery("SELECT sum(o.totalAmount) as todays_Amount FROM orderdetails o WHERE o.orderDate = CURDATE()");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("todays_Amount", map.get("todays_Amount"));
		}
		
		query = session.createSQLQuery("SELECT sum(o.totalAmount) as months_Amount FROM orderdetails o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE()) AND YEAR(o.orderDate) = YEAR(CURRENT_DATE())");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		list = query.list();
		for (HashMap map : list) {
			mapOfAllDetails.put("months_Amount", map.get("months_Amount"));
		}
		
		session.close();
		System.out.println("mapOfAllDetails is => "+mapOfAllDetails);
		return mapOfAllDetails;
	}

	@Override
	public void updateReamainingPayment(int cId, double NewAmt) {
		System.out.println("Inside update Reamaining Payment");
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("update customerdetailmodel c set c.cAmount='"+NewAmt+"' where c.cId="+cId+"");
		query.executeUpdate();
		session.close();
		System.out.println("Reamaining Payment Updated...");
	}
}
