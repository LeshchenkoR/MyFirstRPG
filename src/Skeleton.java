public class Skeleton extends Character {

    public Skeleton(String name, int maxHp, int agility, int strength, int experience) {
        super(name, maxHp, agility, strength, experience);
    }

    public int attack() {
        if (getAgility() * 3 > (int) (Math.random() * 100)) {
            return getStrength();
        } else return 0;
    }
}
