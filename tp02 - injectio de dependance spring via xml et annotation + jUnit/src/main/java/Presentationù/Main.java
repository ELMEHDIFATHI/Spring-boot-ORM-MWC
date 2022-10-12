package Presentationù;

import Dao.Idao;
import Metier.MetierImpl;

import version2.Daoimpl2;

public class Main {

    public static void main(String[] args) {
        //DaoImpl d2=new DaoImpl();
        Daoimpl2 d=new Daoimpl2();
        MetierImpl M=new MetierImpl(d);
        //M.setDao((Idao) d);
        System.out.println(    M.calculer());

        /*
        */





    }
}
