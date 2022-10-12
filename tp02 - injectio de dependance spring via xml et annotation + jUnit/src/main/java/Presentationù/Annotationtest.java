package Presentationù;

import Metier.Imetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Annotationtest {
    public static void main(String[] args) {
        ApplicationContext c=
                new AnnotationConfigApplicationContext("Dao","Metier");
        Imetier m=c.getBean(Imetier.class);
        System.out.println("resultat"+m.calculer());


    }
}
