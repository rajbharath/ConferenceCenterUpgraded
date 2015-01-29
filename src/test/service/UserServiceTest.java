package test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import main.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-test.xml")
public class UserServiceTest {


    @Test
    public void shouldCreateUser() {
       Assert.assertTrue(false);
    }

}
