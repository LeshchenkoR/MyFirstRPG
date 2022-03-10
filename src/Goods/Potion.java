package Goods;

public class Potion {
    private int health;
    private int cost;

    public Potion(int health, int cost) {
        this.health = health;
        this.cost = cost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Зелье лечения +" + health + ", цена = " + cost + " золотых";
    }
}
