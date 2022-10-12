package version2;


import Dao.Idao;

public class Daoimpl2 implements Idao {
    @Override
    public double getdata() {
        System.out.println("version de capteur");
        double temp=Math.random()*12;
        return temp;
    }
}
