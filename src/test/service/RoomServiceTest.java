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

import javax.validation.ConstraintViolationException;
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
        Assert.assertNotNull("first room in the conference category",roomService.findByName("100A"));
        roomService.create("100B",new BigDecimal("500.00"),50,1);
        Assert.assertNotNull("second room in the conference category",roomService.findByName("100B"));
        Assert.assertTrue("At this point, 2 rooms should be present in DB", roomService.findAll().size() == 2);
    }

    @Test
    public void shouldNotCreateRoomForNullName() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create(null,new BigDecimal("500.00"),50,1);
    }

    @Test
    public void shouldNotCreateRoomForEmptyName() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create("",new BigDecimal("500.00"),50,1);
    }

    @Test
    public void shouldNotCreateRoomForNullChargePerHour() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create("100A",null,50,1);
    }

    @Test
    public void shouldNotCreateRoomForEmptyChargePerHour() {
        thrown.expect(NumberFormatException.class);
        roomService.create("100A",new BigDecimal(""),50,1);
    }

    @Test
    public void shouldNotCreateRoomForZeroNoOfSeats() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create("100A",new BigDecimal("500.55"),0,1);
    }

    @Test
    public void shouldNotCreateRoomForNegativeNoOfSeats() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create("100A",new BigDecimal("500.55"),-1,1);
    }

    @Test
    public void shouldNotCreateRoomForNoOfSeatsBeyondTheUpperLimit() {
        thrown.expect(ConstraintViolationException.class);
        roomService.create("100A",new BigDecimal("500.55"),10000,1);
    }

}
