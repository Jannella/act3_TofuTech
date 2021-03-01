package mcm.edu.ph.act3_tofutech;

public class GameUnit {
    String unitName;
    int baseHealth;
    int minDPT;
    int maxDPT;

    public GameUnit(){}
    public GameUnit(String unitName, int baseHealth, int minDPT, int maxDPT){
        this.unitName = unitName;
        this.baseHealth = baseHealth;
        this.minDPT = minDPT;
        this.maxDPT = maxDPT;
    }
}
