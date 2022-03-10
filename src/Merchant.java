public class Merchant {
   Player player;

    public Merchant(Player player) {
        this.player = player;
    }

    public void sell() {
        System.out.println("У торговца в наличии:");
        int price = (int) (Math.random() * (10 + player.potionsNum * 3) + 10 + player.potionsNum);
        System.out.println("- зелье лечения: " + price + " золотых");
        System.out.println("Хотите купить пузырек?\n 1. Да\n 2. Нет");
        int choice = Choice.readInt("-> ", 2);
        if (choice == 1) {
            if (player.gold >= price) {
                System.out.println("Вы купили зелье лечения за " + price + " золотых");
                player.potionsNum++;
                player.gold -=price;
            }else {
                System.out.println("У вас недостаточно золота");
            }
        }else System.out.println("Приходите когда передумаете");
    }
}
