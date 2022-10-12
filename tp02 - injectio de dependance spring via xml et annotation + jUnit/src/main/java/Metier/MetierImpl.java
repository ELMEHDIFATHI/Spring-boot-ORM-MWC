package Metier;


import Dao.Idao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements Imetier {

  //  @Autowired
    private Idao dao;

    public MetierImpl(Idao dao) {
        this.dao = dao;
    }

    @Override
    public double calculer() {
        double temp=dao.getdata();
        return temp*100;
    }
/*
    public void setDao(Idao dao) {
        this.dao = dao;
    }
    */

}
