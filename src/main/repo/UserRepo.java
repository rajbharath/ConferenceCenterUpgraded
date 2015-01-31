package main.repo;

import main.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepo {
    @Autowired
    SessionFactory sessionFactory;

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    public List<User> findByMail(String mail) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria  = session.createCriteria(User.class);
        criteria.add(Restrictions.like("mail","%"+mail+"%"));
        List<User> users = criteria.list();
        return users;
    }

    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User U where id = :id").setParameter("id", id).uniqueResult();
        return user;
    }

    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        return users;
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
}
