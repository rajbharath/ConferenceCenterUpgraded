package main.service;

import main.model.Booking;
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

    public void create(int userId,int roomId,Date fromDate,Date toDate,BigDecimal amount){
        Booking booking = new Booking();
        User user = userService.findById(userId);
        Room room = roomService.findBy(roomId);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setFromDate(fromDate);
        booking.setToDate(toDate);
        booking.setAmount(amount);
        bookingRepo.save(booking);
    }

    public List<Booking> findByUser(int userId){
        User user = userRepo.findById(userId);
        return bookingRepo.findByUser(user);
    }

    public boolean isAvailable(int roomId, Date fromDate, Date toDate) {
        Room room = roomService.findBy(roomId);
        List<Booking> bookings = bookingRepo.findBookingByConflicts(room, fromDate, toDate);
        for(Booking b:bookings)
        System.out.print("room : " + b.getRoom()+" from date : " + b.getFromDate()+" to date : " + b.getToDate());
        return bookings.size() == 0;
    }
}
