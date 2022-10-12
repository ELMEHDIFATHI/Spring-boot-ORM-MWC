package Presentationù;

import Metier.Imetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringusingXml {
    public static void main(String[] args) {
        ApplicationContext c=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Imetier m=(Imetier)c.getBean("metier");
        System.out.println("resulatat"+m.calculer());

    }
}
