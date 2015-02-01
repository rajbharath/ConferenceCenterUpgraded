package test.service;

import main.service.RoomService;
import main.service.TestTransaction;
import main.service.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class RoomServiceTest {
    @Autowired
    RoomService roomService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldCreateRoom() {
        roomService.create("100A",new BigDecimal("500.00"),50,1);
        Assert.assertNotNull(roomService.findByName("100A"));
    }


}
