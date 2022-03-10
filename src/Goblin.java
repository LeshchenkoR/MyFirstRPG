public class Goblin extends Character{

    public Goblin(String name, int maxHp, int agility, int strength, int experience) {
        super(name, maxHp, agility, strength, experience);
    }

    public int attack() {
        if (getAgility() * 3 > (int) (Math.random() * 100)) {
            return getStrength();
        } else return 0;
    }


}
