package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

	private List<User> users = new ArrayList<User>();

	private static UserDao userDao;

	public static UserDao getUserDao() {
		// lazy initialization
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public boolean saveUser(User user) {
		return users.add(user);
	}

	public List<User> getUsers() {
		return users;
		
	}

	public boolean deleteUser(User userToDelete) {

		for (User user : users) {
			if (user.equals(userToDelete)) {
				return users.remove(user);
			}
		}
		return false;
	}
	public boolean updateUser(User userToUpdate) {

		boolean status=false;
		for (User user : users) {
			if (user.equals(userToUpdate)) {
				user.setEmail(userToUpdate.getEmail());
				user.setRoles(userToUpdate.getRoles());
				status = true;
				break;
			}
		}
		return status;
	}

	public User findUser(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
}
