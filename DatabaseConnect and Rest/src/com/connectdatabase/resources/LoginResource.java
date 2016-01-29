package com.connectdatabase.resources;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.anubhav.dao.LoginDao;
import com.connectdatabase.dto.LoginDto;

@Path("/verify")
public class LoginResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyUser(LoginDto dto){
		System.out.println("inside login resource"+ dto.getUserid());
		LoginDao dao=new LoginDao();
		return dao.verifyUser(dto);
	}
	
	
}
