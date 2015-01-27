package main.repo;

import main.model.Room;
import main.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Room room) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(room);
        transaction.commit();
        session.close();
    }

    public Room findById(int id) {
        Session session = sessionFactory.openSession();
        Room room = (Room) session.createQuery("from Room R where id = :id").setParameter("id", id).uniqueResult();
        session.close();
        return room;
    }

    public List<Room> findAll() {
        Session session = sessionFactory.openSession();
        List<Room> rooms = session.createQuery("from Room").list();
        session.close();
        return rooms;
    }

    public void update(Room room) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(room);
        transaction.commit();
        session.close();
    }
}
