package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserDaoUnitTest {
    UserDao userDao;
    
    public User createUser() {
    	 User user = new User();
    	 user.setName("Fake Name");
         user.setEmail("fake@email.com");
         user.setRoles(Arrays.asList("admin", "master"));
         userDao.saveUser(user);
         return user;
    }

    @Test
    public void saveUserTest() {
    	
        userDao = UserDao.getUserDao();
    
        Assert.assertNotNull(userDao);
        
        User user = new User();
        Assert.assertNotNull(user);
        
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        
        boolean result=userDao.saveUser(user);
        Assert.assertTrue(result);
    }
    
    @Test
    public void deleteUserTest() {
        userDao = UserDao.getUserDao();
        User user=createUser();
        Assert.assertTrue(userDao.deleteUser(user));
    }
    
    @Test
    public void updateUserTest() {
        userDao = UserDao.getUserDao();
        User user=createUser();
        user.setEmail("newemail.com");
        user.setRoles(new ArrayList<String>());
        Assert.assertTrue(userDao.updateUser(user));
}
    @Test
    public void FindUserTest() {
        userDao = UserDao.getUserDao();
      
        createUser();
        User user1=userDao.findUser("Fake Name");
        Assert.assertNotNull(user1);
}
    @Test
    public void UserTest() {
    	userDao = UserDao.getUserDao();
        User user=new User();
        user.setEmail("fake@gmail.com");
        user.setName("Fake name");
        user.setRoles(Arrays.asList("admin", "master"));
        Assert.assertEquals(user.getEmail(), "fake@gmail.com");
        Assert.assertEquals(user.getName(), "Fake name");
        List<String> roles1 = Arrays.asList("admin", "master");
        Assert.assertEquals(user.getRoles(),roles1);
    }
    
}