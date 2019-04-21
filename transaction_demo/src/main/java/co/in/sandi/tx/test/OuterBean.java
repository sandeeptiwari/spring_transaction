package co.in.sandi.tx.test;

import co.in.sandi.model.User;

public interface OuterBean {

	void testRequired(User user);
	
	void testRequiresNew(User user);

}
