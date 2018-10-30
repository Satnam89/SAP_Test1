package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {
	public User createUser()
	{	
		
		User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(new ArrayList<String>());
        return integration;
	}
	
	@Test
	public void createUserTest() {
		UserResource userResource = new UserResource();
		User integration=createUser();
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		UserResource userResource = new UserResource();
		User user = createUser();
		
		userResource.addUser(user.getName(), user.getEmail(), user.getRoles());

        user.setName("integration");
        user.setEmail("updated@integration.com");
        user.setRoles(new ArrayList<String>());
        
        Response response = userResource.updateUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}
	@Test
	public void deleteUserTest() {
		UserResource userResource = new UserResource();

        User user=createUser();
        user.setName("ToDelete");
        userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        String name=user.getName();
        Response response = userResource.deleteUser(name);
        Assert.assertEquals(200, response.getStatus());
        Response response1 = userResource.findUser(name);
        Assert.assertEquals(409, response1.getStatus());
        
	}
}
