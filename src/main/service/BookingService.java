package main.service;

import main.model.Booking;
import main.model.Room;
import main.model.User;
import main.repo.BookingRepo;
import main.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    UserRepo userRepo;


    public void create(User user,Room room,Date fromDate,Date toDate,BigDecimal amount){
        Booking booking = new Booking();
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

}
