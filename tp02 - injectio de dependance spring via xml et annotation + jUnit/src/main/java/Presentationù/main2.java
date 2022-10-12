package Presentationù;



import Dao.Idao;
import Metier.Imetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class main2 {
    public static void main(String[] args) throws Exception {

        Scanner scanner=new Scanner(new File("config.txt"));
        String d=scanner.nextLine();
        System.out.println(d);
        Class cDao=Class.forName(d);
        Idao dao=(Idao) cDao.newInstance();
        System.out.println(dao.getdata() );


        String metierclassName=scanner.nextLine();
        Class cMetier=Class.forName(metierclassName);
        Imetier metier=(Imetier) cMetier .newInstance();

        Method m=cMetier.getMethod("setDao",Idao.class);
        m.invoke(metier,dao); // excute la methode
        System.out.println("Resultat"+metier.calculer() );



    }
}
