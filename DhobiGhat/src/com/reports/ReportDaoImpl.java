package com.reports;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reportDaoInterface")
public class ReportDaoImpl implements ReportDaoInterface {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<String> searchOrderId(String keyword) {
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("select distinct(o.orderId) from CustomerOrderModel o where o.orderId like :keyword ");
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> searchOrderDate(String keyword, String mobileNo) {
		System.out.println("INSIDE Dao serch orderDate "+keyword+" mobileNo "+mobileNo);
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select distinct(o.orderDate) from CustomerOrderModel o where o.orderDate like :keyword ");
		/*Query query = session.createQuery("select distinct(c.orderDate) from CustomerOrderModel c inner join CustomerDetailModel cd on cd.cId=d.cId where c.orderDate like :keyword and cd.cMobile='"+mobileNo+"'");*/
		query.setString("keyword", "%" + keyword + "%");
		System.out.println("query: " + query);
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}

	@Override
	public List<String> getDailyCollectionList(String dateForDailyCollection) {
		System.out.println("INSIDE Dao  dateForDailyCollection "+dateForDailyCollection);
		List<String> result = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select qrOrderId from OrderDetails");
		result = query.list();
		System.out.println("result: " + result);
		session.close();
		return result;
	}
}
