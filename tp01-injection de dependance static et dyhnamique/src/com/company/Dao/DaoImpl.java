package com.company.Dao;

import com.company.Dao.Idao;

public class DaoImpl implements Idao {
    @Override
    public double getdata() {
        System.out.println("version de base de donne");
        double temp=Math.random()*10;
        return temp;
    }
}

