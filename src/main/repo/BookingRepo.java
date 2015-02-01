package main.repo;

import main.model.Booking;
import main.model.MaintenanceSchedule;
import main.model.Room;
import main.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BookingRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(booking);
    }

    public Booking findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Booking booking = (Booking) session.createQuery("from Booking B where id = :id").setParameter("id", id).uniqueResult();
        return booking;
    }

    public void update(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.update(booking);
    }

    public List<Booking> findByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<Booking> bookings = session.createQuery("from Booking B where B.user= :user").setParameter("user", user).list();
        return bookings;
    }

    public List<Booking> findBookingByConflicts(Room room, Date fromDate, Date toDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select * from booking b where b.room_id = :room and ((b.from_date <= :toDate and b.from_date >= :fromDate) or (b.to_date > :fromDate and b.to_date < :toDate))").addEntity(Booking.class).setParameter("room",room).setParameter("fromDate",fromDate).setParameter("toDate",toDate);
        return query.list();
    }
}
