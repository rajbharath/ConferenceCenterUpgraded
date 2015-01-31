package test.service;

import main.service.TestTransaction;
import main.service.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateUser() {
        userService.create("raj", "raj115@bharath.com", "abcd1234");
        Assert.assertTrue(userService.findByMail("raj115@bharath.com").size() > 0);
    }

    @Test
    public void shouldNotCreateUserForNullName() {
        thrown.expect(ConstraintViolationException.class);
        userService.create(null, "raj115@bharath.com", "abcd1234");
    }

    @Test
    public void shouldNotCreateUserForTooLongName() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("abcdefghijklmnopqrstuvwxyz", "raj115@bharath.com", "abcd1234");
    }

    @Test
    public void shouldNotCreateUserForEmptyName() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("", "raj115@bharath.com", "abcd1234");
    }


    @Test
    public void shouldNotCreateUserForNullMail() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", null, "abcd1234");
    }

    @Test
    public void shouldNotCreateUserForInvalidMail() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", "rakj@njasd", "abcd1234");
    }

    @Test
    public void shouldNotCreateUserForEmptyMail() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", "", "abcd1234");
    }

    @Test
    public void shouldNotCreateUserForNullPassword() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", "rakj@njasd.com", null);
    }

    @Test
    public void shouldNotCreateUserForShortPassword() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", "rakj@njasd.com", "1324567");
    }

    @Test
    public void shouldNotCreateUserForEmptyPassword() {
        thrown.expect(ConstraintViolationException.class);
        userService.create("raj", "rakj@njasd.com", "");
    }
}

