public class Player extends Character {
    int gold, potionsNum;

    public Player(String name) {
        super(name, 100, 20, 20, 0);
        this.potionsNum = 1;
        this.gold = 1;
    }

    @Override
    public void charInfo() {
        System.out.println("---------------------------");
        System.out.println("ХАРАКТЕРИСТИКИ  ПЕРСОНАЖА");
        System.out.println("---------------------------");
        System.out.print("Имя героя: " + getName() + " (уровень " + Realm.getLevel() + ")"
                + "\nСила: " + getStrength() + "\nЛовкость: " + getAgility()
                + "\nЗдоровье: " + getHp() + "/" + getMaxHp() + "\nОпыт: " + getExperience());
        System.out.println("\nЗолото: " + gold + "\nЛекарств: " + potionsNum);
    }

    public int attack() {
        if (getAgility() * 3 > (int) (Math.random() * 100)) {
            return getStrength();
        } else return 0;
    }
}
