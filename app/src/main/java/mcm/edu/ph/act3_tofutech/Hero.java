package mcm.edu.ph.act3_tofutech;

public class Hero extends GameUnit{

    public Hero(){}
    public Hero(String unitName, int baseHealth, int minDPT, int maxDPT){
        super.unitName = unitName;
        super.baseHealth = baseHealth;
        super.minDPT = minDPT;
        super.maxDPT = maxDPT;
    }
}
