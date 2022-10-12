package com.company.Presentationù;

import com.company.Dao.Idao;
import com.company.Metier.Imetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class main2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Scanner scanner=new Scanner(new File("config.txt"));
        String d=scanner.nextLine();
        System.out.println(d);
        Class cDao=Class.forName(d);
        Idao dao=(Idao) cDao.getDeclaredConstructor().newInstance();
        System.out.println(dao.getdata() );


        String metierclassName=scanner.nextLine();
        Class cMetier=Class.forName(metierclassName);
        Imetier metier=(Imetier) cMetier.getDeclaredConstructor().newInstance();

        Method m=cMetier.getMethod("setDao",Idao.class);
        m.invoke(metier,dao); // excute la methode
        System.out.println("Resultat"+metier.calculer() );

        /**
         * Intro
         * Enoce
         * conception
         * code source git hub
         * capture
         * d ecran
         * conclustuin
         * ****/




    }
}
