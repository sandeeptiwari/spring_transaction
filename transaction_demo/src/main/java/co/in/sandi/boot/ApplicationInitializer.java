package co.in.sandi.boot;

import co.in.sandi.config.AppConfig;
import co.in.sandi.model.User;
import co.in.sandi.tx.UserManager;
import co.in.sandi.tx.impl.UserManagerImpl;
import co.in.sandi.tx.test.OuterBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationInitializer{
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public static void main(String...args){
        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        /*UserManager userManager = (UserManager)context.getBean("userManagerImpl");

        User user = new User();
        user.setUserName("John");
        user.setName("John Dev");*/

        OuterBean testBean = (OuterBean) context.getBean("outerBeanImpl");

        User user = new User();
        user.setUserName("Sandi15");
        user.setName("Sandeep kumar Tiwari");

        try{
            testBean.testRequired(user);
        } catch(Exception e){
            // catch exception raised from transaction rollback
            logger.error("Rollback transaction :: "+ e.getMessage());
            //throw e;
        }

        //testBean.testRequiresNew(user);
    }
}
