package co.in.sandi.dao.impl;

import co.in.sandi.dao.UserDAO;
import co.in.sandi.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void insertUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserById(int userId) {
        return (User) sessionFactory.
                getCurrentSession().
                get(User.class, userId);
    }

    @Override
    public User getUser(String username) {
        /*Query query = sessionFactory.
                getCurrentSession().
                createQuery("from User where userName = :userName");
        query.setParameter("userName", username);
        return (User) query.list().get(0);*/
        return new User();
    }

    @Override
    public List<User> getUsers() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
