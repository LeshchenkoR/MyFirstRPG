import java.io.IOException;

public abstract class Character {
    public String name;
    private int maxHp;
    private int hp;
    private int agility;
    private int strength;
    private int experience;

    public Character(String name, int maxHp, int agility, int strength, int experience) {
        this.name = name;
        this.maxHp = maxHp;
        this.agility = agility;
        this.strength = strength;
        this.experience = experience;
        this.hp = maxHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public abstract int attack();

    public void charInfo() throws IOException {
        System.out.println("Имя существа " + getName() + "\tHP:" + getHp() + "/" + getMaxHp());
        System.out.println("XP: " + getExperience());
    }

}
