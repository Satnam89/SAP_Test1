package com.h2rd.refactoring.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Repository;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.utility.Constants;

@Path("/users")
@Repository
public class UserResource {

	public UserDao userDao;

	@GET
	@Path("add/")
	public Response addUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("role") List<String> roles) {

		String returnObject;
		int value;
		if (name == null || name.equals("")) {
			returnObject = Constants.NAME_MANDATORY;
			value = 400;
		} else {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setRoles(roles);
			if (userDao == null) {
				userDao = UserDao.getUserDao();
			}
			if (userDao.saveUser(user)) {
				returnObject = Constants.USER_ADD_SUCCESS;
				value = 200;
			} else {
				returnObject = Constants.USER_ADD_FAILURE;
				value = 409;
			}
		}

		return Response.status(value).entity(returnObject).build();
	}

	@GET
	@Path("update/")
	public Response updateUser(@QueryParam("name") String name, @QueryParam("email") String email,
			@QueryParam("role") List<String> roles) {

		String returnObject;
		int value;
		if (name == null || name.equals("")) {
			returnObject = Constants.NAME_MANDATORY;
			value = 400;
		} else {
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setRoles(roles);

			if (userDao == null) {
				userDao = UserDao.getUserDao();
			}

			if (userDao.updateUser(user)) {
				returnObject = Constants.USER_UPDATE_SUCCESS;
				value = 200;
			} else {
				returnObject = Constants.USER_UPDATE_FAILURE;
				value = 409;
			}

		}
		return Response.status(value).entity(returnObject).build();
	}

	@GET
	@Path("delete/")
	public Response deleteUser(@QueryParam("name") String name) {

		String returnObject;
		int value;
		if (name == null || name.equals("")) {
			returnObject = Constants.NAME_MANDATORY;
			value = 400;
		} else {

			User user = new User();
			user.setName(name);
			if (userDao == null) {
				userDao = UserDao.getUserDao();
			}

			if (userDao.deleteUser(user)) {
				returnObject = Constants.USER_DELETE_SUCCESS;
				value = 200;
			} else {
				returnObject = Constants.USER_DELETE_FAILURE;
				value = 409;
			}
			userDao.deleteUser(user);
		}
		return Response.status(value).entity(returnObject).build();
	}

	@GET
	@Path("find/")
	public Response getUsers() {
		
		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}
		List<User> users = userDao.getUsers();
		System.out.println(users);
		if (users == null) {
			users = new ArrayList<User>();
		}

		GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {
		};
		return Response.status(200).entity(usersEntity).build();
	}

	@GET
	@Path("search/")
	public Response findUser(@QueryParam("name") String name) {

		if (userDao == null) {
			userDao = UserDao.getUserDao();
		}

		String returnObject;
		int value;
		if (name == null || name.equals("")) {
			returnObject = Constants.NAME_MANDATORY;
			value = 400;
		} else {
			User user = userDao.findUser(name);
			if (user == null) {
				returnObject = Constants.USER_SEARCH_FAILURE;
				value = 409;
			} else {
				returnObject = user.toString();
				value = 200;
			}

		}

		return Response.status(value).entity(returnObject).build();
	}
}
