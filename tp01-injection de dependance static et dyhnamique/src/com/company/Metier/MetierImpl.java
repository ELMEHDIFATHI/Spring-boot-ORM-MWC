package com.company.Metier;

import com.company.Dao.Idao;

public class MetierImpl implements Imetier {

    private Idao dao;
    @Override
    public double calculer() {
        double temp=dao.getdata();
        return temp*100;
    }

    public void setDao(Idao dao) {
        this.dao = dao;
    }
}
