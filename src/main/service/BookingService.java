package main.service;

import main.model.Booking;
import main.model.MaintenanceSchedule;
import main.model.Room;
import main.model.User;
import main.repo.BookingRepo;
import main.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    HolidayService holidayService;

    @Autowired
    MaintenanceScheduleService maintenanceScheduleService;

    public void create(int userId, int roomId, Date fromDate, Date toDate, BigDecimal amount) throws Exception {
        Booking booking = new Booking();
        User user = userService.findById(userId);
        Room room = roomService.findBy(roomId);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setFromDate(fromDate);
        booking.setToDate(toDate);
        booking.setAmount(amount);
        if (!isAvailable(roomId, fromDate, toDate)) throw new Exception("Room is not available");
        bookingRepo.save(booking);
    }

    public List<Booking> findByUser(int userId) {
        User user = userRepo.findById(userId);
        return bookingRepo.findByUser(user);
    }

    public boolean isAvailable(int roomId, Date fromDate, Date toDate) {
        if (hasHolidayBetween(fromDate, toDate)) return false;
        if (hasMaintenanceBetween(roomId, fromDate, toDate)) return false;
        if (isBookedBetween(roomId, fromDate, toDate)) return false;
        return true;
    }

    private boolean hasHolidayBetween(Date fromDate, Date toDate) {
        if (holidayService.findAllBetween(fromDate, toDate).size() > 0) return true;
        return false;
    }

    private boolean hasMaintenanceBetween(int roomId, Date fromDate, Date toDate) {
        List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleService.findBetween(roomId, fromDate, toDate);
        for (MaintenanceSchedule s : maintenanceSchedules)
            System.out.println("room : " + s.getRoom() + " fromdate : " + s.getFromDate() + " todate : " + s.getToDate());
        if (maintenanceSchedules.size() > 0) return true;
        return false;
    }

    private boolean isBookedBetween(int roomId, Date fromDate, Date toDate) {
        Room room = roomService.findBy(roomId);
        List<Booking> bookings = bookingRepo.findBookingByConflicts(room,fromDate, toDate);
        return bookings.size() > 0;
    }
}
