package main.repo;

import main.model.Booking;
import main.model.Room;
import main.model.RoomCategoryName;
import main.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(booking);
        transaction.commit();
        session.close();
    }

    public Booking findById(int id) {
        Session session = sessionFactory.openSession();
        Booking booking = (Booking) session.createQuery("from Booking B where id = :id").setParameter("id", id).uniqueResult();
        session.close();
        return booking;
    }

    public void update(Booking booking) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(booking);
        transaction.commit();
        session.close();
    }

    public List<Booking> findByUser(User user) {
        Session session = sessionFactory.openSession();
        List<Booking> bookings = session.createQuery("from Booking B where B.user= :user").setParameter("user",user).list();
        session.close();
        return bookings;
    }
}
