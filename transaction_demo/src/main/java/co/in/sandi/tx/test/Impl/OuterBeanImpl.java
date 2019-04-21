package co.in.sandi.tx.test.Impl;

import co.in.sandi.config.AppConfig;
import co.in.sandi.dao.TestDAO;
import co.in.sandi.model.User;
import co.in.sandi.tx.test.InnerBean;
import co.in.sandi.tx.test.OuterBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterBeanImpl implements OuterBean {
	private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Autowired
	private TestDAO testDAO;
	
	@Autowired
	private InnerBean innerBean;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequired(User user) {
		testDAO.insertUser(user);
		try{
			innerBean.testRequired();
		} catch(RuntimeException e){
			logger.error("OuterBeanImpl :: "+e.getMessage()+" Status ");
			//throw e;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequiresNew(User user) {
		testDAO.insertUser(user);
		try{
			innerBean.testRequiresNew();
		} catch(Exception e){
			// handle exception
		}
	}

}
