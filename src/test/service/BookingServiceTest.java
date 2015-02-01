package test.service;

import junit.framework.Assert;
import main.service.*;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    HolidayService holidayService;

    @Autowired
    MaintenanceScheduleService maintenanceScheduleService;

    @Autowired
    SessionFactory sessionFactory;

    private int userId;
    private int roomId;
    private Date fromDate;
    private Date toDate;
    private Date greaterThanToDate;
    private Date holiday;
    private Date maintenanceStartDate;
    private Date maintenanceEndDate;


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

        Calendar holidayCalendar = Calendar.getInstance();
        holidayCalendar.add(Calendar.HOUR_OF_DAY, 1);
        holidayCalendar.add(Calendar.DAY_OF_MONTH, 4);
        holiday = holidayCalendar.getTime();
        holidayService.create(holiday, "Holiday");

        Calendar maintenanceDateCalendar = Calendar.getInstance();
        maintenanceDateCalendar.add(Calendar.DAY_OF_MONTH, 10);
        maintenanceDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        maintenanceDateCalendar.set(Calendar.MINUTE, 0);
        maintenanceStartDate = maintenanceDateCalendar.getTime();

        Calendar maintenanceEndDateCalendar = Calendar.getInstance();
        maintenanceEndDateCalendar.add(Calendar.DAY_OF_MONTH, 11);
        maintenanceEndDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        maintenanceEndDateCalendar.set(Calendar.MINUTE, 0);
        maintenanceEndDate = maintenanceEndDateCalendar.getTime();
        maintenanceScheduleService.create(roomId, maintenanceStartDate, maintenanceEndDate, "Its a scheduled maintenance");
    }

    @Test
    public void shouldBookRoom() throws Exception {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertNotNull("Booking failed", bookingService.findByUser(userId));
    }

    @Test
    public void shouldNotBookSameRoomTwiceInSameTime() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Room is not available");
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
    }

    @Test
    public void shouldBookingNotAvailable() throws Exception {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertFalse(bookingService.isAvailable(roomId, fromDate, toDate));

    }

    @Test
    public void shouldBookingBeAvailable() throws Exception {
        bookingService.create(userId, roomId, fromDate, toDate, new BigDecimal("40000.00"));
        Assert.assertTrue(bookingService.isAvailable(roomId, toDate, greaterThanToDate));
    }

    @Test
    public void shouldThrowExceptionBookingOnHoliday() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Room is not available");
        bookingService.create(userId, roomId, fromDate, holiday, new BigDecimal("40000.00"));
    }

    @Test
    public void shouldThrowExceptionBookingOnScheduledMaintenance() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Room is not available");
        bookingService.create(userId, roomId, maintenanceStartDate, maintenanceEndDate, new BigDecimal("40000.00"));
    }
}
