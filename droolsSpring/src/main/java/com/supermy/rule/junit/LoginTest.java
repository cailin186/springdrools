package com.supermy.rule.junit;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.supermy.rule.service.LoginServiceImpl;


public class LoginTest {
	@Test
	public void testLogin(){

		long start = System.currentTimeMillis();
	   	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	   	
    	LoginServiceImpl loginServiceImpl= (LoginServiceImpl) context.getBean( "loginService" );
    	
    	StatefulKnowledgeSession kstateless = (StatefulKnowledgeSession) context.getBean( "ksession1" );
    	System.out.println("耗时----"+(System.currentTimeMillis()-start));
    	loginServiceImpl.checkLogin(kstateless);
    	System.out.println("aa");
		long end = System.currentTimeMillis();

	}
}
