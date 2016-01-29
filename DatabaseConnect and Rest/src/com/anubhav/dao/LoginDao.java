package com.anubhav.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import com.anubhav.entity.Login;
import com.connectdatabase.dto.LoginDto;

public class LoginDao {

	public String verifyUser(LoginDto dto) {
		System.out.println("inside login dao"+dto.getUserid());

		Session session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery("From Login where userid= :userid");
		q.setParameter("userid", dto.getUserid());
		List results = q.list();
		Login login = null;
		if (results.size() != 0) {
			Iterator ite = results.iterator();
			while (ite.hasNext()) {
				login = (Login) ite.next();
			}
		}
		if (dto.getPassword().equals(login.getPassword())) {
			return "success";
		} else {
			return "failure";
		}
	}
}