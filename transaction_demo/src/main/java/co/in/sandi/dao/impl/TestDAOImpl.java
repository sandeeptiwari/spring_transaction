package co.in.sandi.dao.impl;

import co.in.sandi.dao.TestDAO;
import co.in.sandi.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDAOImpl implements TestDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

}
