package mcm.edu.ph.act3_tofutech;

public class Monster extends GameUnit{

    public Monster(){}
    public Monster(String unitName, int baseHealth, int minDPT, int maxDPT){
        super.unitName = unitName;
        super.baseHealth = baseHealth;
        super.minDPT = minDPT;
        super.maxDPT = maxDPT;
    }
}
