package Dao;


import org.springframework.stereotype.Component;

@Component("dao") // cree une instannce de class
public class DaoImpl implements Idao {
    @Override
    public double getdata() {
        System.out.println("version de base de donne");
        double temp=Math.random()*10;
        return temp;
    }
}

