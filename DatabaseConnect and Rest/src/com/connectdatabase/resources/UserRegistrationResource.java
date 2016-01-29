package com.connectdatabase.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.anubhav.dao.UserRegistrationDao;
import com.connectdatabase.dto.AddressDto;
import com.connectdatabase.dto.AuthorizationDto;
import com.connectdatabase.dto.UserIdDto;
import com.connectdatabase.dto.UserRegistrationDto;

import oracle.net.jdbc.TNSAddress.Address;

@Path("/registration")
public class UserRegistrationResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(UserRegistrationDto dto){
		UserRegistrationDao dao=new UserRegistrationDao();
		return dao.registerUser(dto);
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserRegistrationDto getUserDetails(@PathParam ("userId") String userId){
		UserRegistrationDao dao=new UserRegistrationDao();
		return dao.getUserDetails(userId);
	}
	
	@POST
	@Path("/verify")
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyUser(UserRegistrationDto dto){
		System.out.println("in resource, id is"+dto.getUserId());
		UserRegistrationDao dao=new UserRegistrationDao();
		return dao.verifyUser(dto);
	} 
	
	@POST
	@Path("/password")
	@Produces(MediaType.APPLICATION_JSON)
	public String changePassword(AuthorizationDto dto){
		UserRegistrationDao dao=new UserRegistrationDao();
		String message=dao.changePassword(dto);
		return message;
	}
	
	@POST
	@Path("/userId")
	@Produces(MediaType.APPLICATION_JSON)
	public String changeUserId(UserIdDto dto){
		UserRegistrationDao dao=new UserRegistrationDao();
		String message=dao.changeUserId(dto);
		return message;
	}
	
	@POST
	@Path("/address/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String changeAddress(@PathParam ("userId") String userId, AddressDto dto){
		UserRegistrationDao dao=new UserRegistrationDao();
		String message;
		message=dao.changeAddress(dto, userId);
		return message;
	}

}
