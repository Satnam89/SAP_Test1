package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

import javax.ws.rs.core.Response;

public class UserResourceUnitTest {

    UserResource userResource;
    UserDao userDao;

    @Test
    public void getUsersTest() {
        userResource = new UserResource();
        userDao = UserDao.getUserDao();
        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void findUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);

        Response response = userResource.findUser("");
        Assert.assertEquals(400, response.getStatus());
        
        Response response1 = userResource.findUser("John");
        Assert.assertEquals(409, response1.getStatus());
        
        Response response2 = userResource.findUser("fake user");
        Assert.assertEquals(200, response2.getStatus());
        
    }
    
    @Test
    public void deleteUsersTest() {

        userResource = new UserResource();
        userDao = UserDao.getUserDao();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);

        Response response = userResource.deleteUser("");
        Assert.assertEquals(400, response.getStatus());
        
        Response response1 = userResource.deleteUser("John");
        Assert.assertEquals(409, response1.getStatus());
        
        Response response2 = userResource.deleteUser("fake user");
        Assert.assertEquals(200, response2.getStatus());
        
    }
    
    @Test
    public void addUsersTest() {

        userResource = new UserResource();
        
        Response response = userResource.addUser("", "fake@user.com",
        		Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response.getStatus());
        
    }
    @Test
    public void updateUserTest() {

    	userResource = new UserResource();
        userDao = UserDao.getUserDao();
        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);
        
        Response response = userResource.updateUser("", "fake@user.com",
        		Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response.getStatus());
        
        Response response1 = userResource.updateUser("fake user", "fake@user.com",
        		Arrays.asList("admin", "master"));
        Assert.assertEquals(200, response1.getStatus());

    }
}
