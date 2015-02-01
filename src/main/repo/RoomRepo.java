package main.repo;

import main.model.Room;
import main.model.RoomCategoryName;
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
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(room);
    }

    public Room findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = (Room) session.createQuery("from Room R where id = :id").setParameter("id", id).uniqueResult();
        return room;
    }

    public List<Room> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Room> rooms = session.createQuery("from Room").list();
        return rooms;
    }

    public void update(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.update(room);
    }

    public List<Room> findByCategory(RoomCategoryName category) {
        Session session = sessionFactory.getCurrentSession();
        List<Room> rooms = session.createQuery("from Room R where R.roomCategory.category= :category").setParameter("category",category).list();
        return rooms;
    }

    public Room findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Room room = (Room) session.createQuery("from Room R where R.name = :name").setParameter("name",name).uniqueResult();
        return room;
    }
}
