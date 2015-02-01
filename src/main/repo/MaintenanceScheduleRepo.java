package main.repo;

import main.model.Booking;
import main.model.Holiday;
import main.model.MaintenanceSchedule;
import main.model.Room;
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
public class MaintenanceScheduleRepo {
    @Autowired
    SessionFactory sessionFactory;

    public void save(MaintenanceSchedule maintenanceSchedule) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(maintenanceSchedule);
    }

    public List<MaintenanceSchedule> findAllBetween(Room room,Date fromDate, Date toDate) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select id,from_date,to_date,room_id,description from maintenance_schedule M where M.room_id = :room and ((m.from_date <= :toDate and m.from_date >= :fromDate) or (m.to_date > :fromDate and m.to_date < :toDate))").addEntity(MaintenanceSchedule.class).setParameter("room",room).setParameter("fromDate",fromDate).setParameter("toDate",toDate);
        return query.list();
    }
}
