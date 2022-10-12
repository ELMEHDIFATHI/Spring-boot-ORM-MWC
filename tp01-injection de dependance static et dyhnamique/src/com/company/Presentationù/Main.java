package com.company.Presentationù;

import com.company.Metier.MetierImpl;
import com.company.version2.Daoimpl2;

public class Main {

    public static void main(String[] args) {
        /*
        *
        * injection de depandance avec l instantian static
        *
        *
        * */
        //DaoImpl d2=new DaoImpl();
        Daoimpl2 d=new Daoimpl2();
        MetierImpl M=new MetierImpl();
        M.setDao(d);
        System.out.println(    M.calculer());

        /*
        */





    }
}
