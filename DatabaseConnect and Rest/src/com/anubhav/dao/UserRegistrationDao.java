package com.anubhav.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import com.anubhav.entity.UserRegistration;
import com.connectdatabase.dto.AddressDto;
import com.connectdatabase.dto.AuthorizationDto;
import com.connectdatabase.dto.UserIdDto;
import com.connectdatabase.dto.UserRegistrationDto;

import oracle.net.jdbc.TNSAddress.Address;
import sessionUtil.SessionUtility;

public class UserRegistrationDao {

	/**
	 * This method accepts the userId for a particular user and returns the user
	 * details.
	 * 
	 * @param userId
	 * @return
	 */
	public UserRegistrationDto getUserDetails(String userId) {
		SessionUtility sessionUtil = new SessionUtility();
		Session session = sessionUtil.startSession();
		Transaction t;
		t = session.beginTransaction();
		UserRegistration userReg = new UserRegistration();
		Criteria criteria = session.createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId", userId));
			List results = criteria.list();
			if(results.size()!=0){
			Iterator ite = results.iterator();
			UserRegistrationDto dto = new UserRegistrationDto();
	
			while (ite.hasNext()) {
				userReg = (UserRegistration) ite.next();
				System.out.println("firstname is " + userReg.getFirstName());
				dto.setFirstName(userReg.getFirstName());
				dto.setMiddleName(userReg.getMiddleName());
				dto.setLastName(userReg.getLastName());
				dto.setAddress1(userReg.getAddress1());
				dto.setAddress2(userReg.getAddress2());
				dto.setAge(userReg.getAge());
				dto.setCity(userReg.getCity());
				dto.setState(userReg.getState());
				dto.setPhoneNumber(userReg.getPhoneNumber());
				dto.setZipcode(userReg.getZipcode());
			}
			sessionUtil.closeSession(session);
			return dto;
		}
			System.out.println("No user found");
		return null;
	}

	/**
	 * This method accepts the user details in the form of a Dto and registers
	 * the user.
	 * 
	 * @param dto
	 * @return
	 */
	public String registerUser(UserRegistrationDto dto) {

		SessionUtility sessionUtil = new SessionUtility();
		Session session = sessionUtil.startSession();
		Transaction t;
		t = session.beginTransaction();
		UserRegistration userReg = new UserRegistration();
		userReg.setFirstName(dto.getFirstName());
		userReg.setLastName(dto.getLastName());
		userReg.setMiddleName(dto.getMiddleName());
		userReg.setAddress1(dto.getAddress1());
		userReg.setAddress2(dto.getAddress2());
		userReg.setAge(dto.getAge());
		userReg.setCity(dto.getCity());
		userReg.setState(dto.getState());
		userReg.setUserId(dto.getUserId());
		userReg.setPhoneNumber(dto.getPhoneNumber());
		userReg.setPassword(dto.getPassword());

		System.out.println(userReg.getUserId());
		session.save(userReg);
		// session.saveOrUpdate("userReg", dto.getUserId());
		t.commit();

		sessionUtil.closeSession(session);
		return "success";
	}

	/**
	 * This method is implemented to provide the user to change in profile
	 * password by passing the userid
	 * 
	 * @param dto
	 * @return
	 */
	public String changePassword(AuthorizationDto dto) {
		System.out.println("inside change Password");
		String message = null;
		SessionUtility sessionUtil = new SessionUtility();
		Session session = sessionUtil.startSession();
		Transaction t = session.beginTransaction();

		Criteria criteria = session.createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId", dto.getUserId()));
		List results = criteria.list();
		UserRegistration userReg = new UserRegistration();
		if (results.size() != 0) {
			Iterator ite = results.iterator();
			
			while (ite.hasNext()) {
				userReg = (UserRegistration) ite.next();
				if (userReg.getPassword().equals(dto.getOld_password())) {
					userReg.setPassword(dto.getNew_password());
				}
				else{
					message = "Passwords don't match";
				}
			}
		}else{
			message = "User not found";
		}
		session.save(userReg);
		t.commit();
		sessionUtil.closeSession(session);
		return message;
	}

	/**
	 * This method provides the authentication capability to the application by
	 * validating the user credentials
	 * 
	 * @param dto
	 * @return
	 */
	public String verifyUser(UserRegistrationDto dto) {
		SessionUtility sessionUtil = new SessionUtility();
		Session session = sessionUtil.startSession();
		Transaction t;
		t = session.beginTransaction();
		UserRegistration userReg = new UserRegistration();
		Criteria criteria = session.createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId", dto.getUserId()));
		List results = criteria.list();
		Iterator ite = results.iterator();

		if (results.size() != 0) {
			while (ite.hasNext()) {
				userReg = (UserRegistration) ite.next();
			}
		}
		if (dto.getPassword().equals(userReg.getPassword())) {
			sessionUtil.closeSession(session);
			return "success";

		}
		sessionUtil.closeSession(session);
		return "failure";
	}
	
	public String changeUserId(UserIdDto dto){
		String message;
		SessionUtility sessionUtil=new SessionUtility();
		Session session=sessionUtil.startSession();
		Transaction t=session.beginTransaction();
		UserRegistration userReg=new UserRegistration();
		Criteria criteria=session.createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId",dto.getOld_userId()));
		List results=criteria.list();
		Iterator ite=results.iterator();
		
		if(results.size()!=0){
			while(ite.hasNext()){
				userReg=(UserRegistration)ite.next();
			}
			if(dto.getPassword().equals(userReg.getPassword())){
				userReg.setUserId(dto.getNew_userId());
				message="userId successfully changed";
			}else{
				message="passwords don't match";
				}
		}else{
		message="no user found";
		}
		t.commit();
		session.save(userReg);
		sessionUtil.closeSession(session);
		return message;
	}
	
	/**
	 * This method accepts userId and the new address details in the form of a Dto
	 * and changes the address for this user 
	 * @param dto
	 * @param userId
	 * @return
	 */
	public String changeAddress(AddressDto dto, String userId){
		String message;
		SessionUtility sessionUtil=new SessionUtility();
		Session session=sessionUtil.startSession();
		Transaction t=session.beginTransaction();
		Criteria criteria=session.createCriteria(UserRegistration.class);
		criteria.add(Restrictions.eq("userId", userId));
		List results=criteria.list();
		Iterator ite=results.iterator();
		UserRegistration userReg=new UserRegistration();
		
		if(results.size()!=0){
			while(ite.hasNext()){
				userReg=(UserRegistration)ite.next();
			}
				if(userReg.getPassword().equals(dto.getPassword())){
					
					userReg.setAddress1(dto.getAddress1());
					userReg.setAddress2(dto.getAddress2());
					userReg.setCity(dto.getCity());
					userReg.setState(dto.getState());
					userReg.setZipcode(dto.getZipcode());
					
					message="Address successfully changed";
				}else{
					message="passwords don't match";
				}
		}else{
			message="User not found";
		}
		t.commit();
		session.save(userReg);
		sessionUtil.closeSession(session);
		return message;
	}
}
