package com.supermy.rule.junit;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class RuleInExcelTest {

	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
        execlDirectTest();
      //  execlSpringTest();
	}


    public static void execlDirectTest() {
    	
    long start  = System.currentTimeMillis();

        DecisionTableConfiguration dtableconfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        
        System.out.println("1==="+(System.currentTimeMillis()-start));
		dtableconfiguration.setInputType(DecisionTableInputType.XLS);
        System.out.println("2==="+(System.currentTimeMillis()-start));
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        System.out.println("3==="+(System.currentTimeMillis()-start));
		kbuilder.add(ResourceFactory.newClassPathResource("rules/RuleInExcel.xls",RuleInExcelTest.class),ResourceType.DTABLE, dtableconfiguration);
        System.out.println("4==="+(System.currentTimeMillis()-start));

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
	    System.out.println("5==="+(System.currentTimeMillis()-start));
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	    System.out.println("6==="+(System.currentTimeMillis()-start));
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	    System.out.println("7==="+(System.currentTimeMillis()-start));
//        Map<String,Integer> params=new HashMap<String,Integer>();
//        params.put("age",3);
        //ksession.execute(Arrays.asList(new Object[]{params}));
//        ksession.insert(income);

        ksession.fireAllRules();
        ksession.dispose();
        
        long end = System.currentTimeMillis();
        
        
        System.out.println((end-start));
    }
  
    public static void execlSpringTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("drools/spring-drools.xml");
        StatefulKnowledgeSession ksession = (StatefulKnowledgeSession) context.getBean( "ksession-excel" );
        ksession.fireAllRules();
        ksession.dispose();
    }

}