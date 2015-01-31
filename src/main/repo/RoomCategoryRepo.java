package main.repo;

import main.model.Room;
import main.model.RoomCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomCategoryRepo {

    @Autowired
    SessionFactory sessionFactory;

    public RoomCategory findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        RoomCategory roomCategory = (RoomCategory) session.createQuery("from RoomCategory R where id = :id").setParameter("id", id).uniqueResult();
        return roomCategory;
    }
}
