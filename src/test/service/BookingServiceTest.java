package test.service;

import junit.framework.Assert;
import main.service.BookingService;
import main.service.RoomService;
import main.service.TestTransaction;
import main.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class BookingServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    BookingService bookingService;

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    private int userId;
    private int roomId;
    private Date fromDate;
    private Date toDate;
    private Date greaterThanToDate;

    @Before
    public void setUp() {
        userService.create("raj", "raj@thoughtworks.com", "12345678");
        userId = userService.findByMail("raj@thoughtworks.com").getId();
        roomService.create("202", new BigDecimal("500.00"), 40, 1);
        roomId = roomService.findByName("202").getId();
        fromDate = Calendar.getInstance().getTime();
        Calendar toDatecalendar = Calendar.getInstance();
        toDatecalendar.add(Calendar.HOUR_OF_DAY, 1);
        toDatecalendar.add(Calendar.DAY_OF_MONTH, 1);
        toDate = toDatecalendar.getTime();
        Calendar greaterThanToDateCalendar = Calendar.getInstance();
        greaterThanToDateCalendar.add(Calendar.HOUR_OF_DAY, 1);
        greaterThanToDateCalendar.add(Calendar.DAY_OF_MONTH, 2);
        greaterThanToDate = toDatecalendar.getTime();
    }

    @Test
    public void shouldBookRoom() {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertNotNull("Booking failed",bookingService.findByUser(userId));
    }

    @Test
    public void shouldNotBookSameRoomTwiceInSameTime() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("could not execute statement");
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
    }

    @Test
    public void shouldBookingNotAvailable() {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertFalse(bookingService.isAvailable(roomId, fromDate, toDate));
    }

    @Test
    public void shouldBookingBeAvailable() {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertTrue(bookingService.isAvailable(roomId, toDate, greaterThanToDate));
    }
}
