package com.Barcode;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BarcodeDaoInterface")
public class BarcodeDaoClass implements BarcodeDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	
	@Override
	public List<String> getAllOrderList() {
		List<String> OrdersList= new ArrayList<String>();
		session=sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("select c.cId,o.oId,o.amountPaid,o.amountRemaining,o.dueDate,o.invoiceNo,o.orderDate,o.orderStatus,o.totalAmount,o.totalQuantity,o.orderId from orderdetails o,customerdetailmodel c where o.cId=c.cId");
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		OrdersList=query.list();
		session.close();
		return OrdersList;
	}
	
}
