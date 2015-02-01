package main.repo;

import main.model.Holiday;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HolidayRepo {
    @Autowired
    SessionFactory sessionFactory;

    public void save(Holiday holiday) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(holiday);
    }

    public List<Holiday> findAllBetween(Date fromDate, Date toDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Holiday.class);
        criteria.add(Restrictions.between("holiday", fromDate, toDate));
        return criteria.list();
    }
}
