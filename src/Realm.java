import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Realm {
    private static final String MENU = """
            1. Посетить торговца.
            2. Отправиться в темный лес.
            3. Посмотреть характеристики.
            4. Подлечиться.
            5. Выйти из игры.""";

    static Scanner scan = new Scanner(System.in);
    static Random random = new Random();
    static Player player;

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void takeRest() {
        clearConsole();
        System.out.println("---------------------");
        System.out.println("СОДЕРЖИМОЕ АПТЕЧКИ");
        System.out.println("---------------------");
        if (player.potionsNum > 0) {
            System.out.println("У вас " + player.potionsNum + " пузырьков лекарства");
            System.out.println("Хотите подлечиться?\n 1. Да\n 2. Нет");
            int choice = Choice.readInt("-> ", 2);
            if (choice == 1) {
                if (player.getHp() == player.getMaxHp()) {
                    System.out.println("Вам лечение не требуется!");
                } else {
                    System.out.println("Здоровье восстановлено до максимума");
                    player.setHp(player.getMaxHp());
                    player.potionsNum -= 1;
                }
            } else System.out.println("Ну, как хотите...");
        } else System.out.println("Нет лекарств");
    }

    private static int level = 1;

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lvl) {
        level = lvl;
    }

    static Character createMonster() {
        String[] strings = {"Карликовый", "Гигантский", "Раненый", "Одноногий", "Разъяренный", "Толстый", "Вооруженный"};
        int i = random.nextInt(strings.length);
        if (random.nextBoolean()) {
            return new Goblin(strings[i] + " гоблин", 80, 20, 10, 4);
        } else {
            return new Skeleton(strings[i] + " скелет", 100, 5, 20, 6);
        }
    }

    public static class FightCallback {
        void fightWin() {
            System.out.println();
            System.out.println("Продолжим?");
        }

        void fightLost() {
            System.out.println("Game over \n");
            System.exit(0);
        }
    }

    public static void printIntro() {
        clearConsole();
        String intros[] = {"В", "ЭТИХ", "БЕГУЩИХ", "СТРОКАХ", "ДОЛЖНО", "БЫТЬ", "ОПИСАНИЕ", "ВСЕЛЕННОЙ",
                "В КОТОРОЙ", "ИГРОКУ", "ПРЕДСТОИТ", "ВЫЖИТЬ!"};
        for (int i = 0; i < intros.length; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(intros[i]);
        }
    }

    public static void startGame() {
        printIntro();
        System.out.println("\n Введите имя своего героя");
        player = new Player(scan.nextLine());
        clearConsole();
        System.out.println("Приветствуем тебя, герой!\nКакими будут твои первые шаги в этом мире?");
    }

    public static void main(String[] args) throws IOException {

        startGame();
        Battle battle = new Battle();

        int route = 0;
        do {
            System.out.println("\nЧто вы хотите сделать?");
            System.out.println(MENU);
            String str = scan.nextLine();
            try {
                route = Integer.parseInt(str);
                switch (route) {
                    case 1:
                        clearConsole();
                        new Merchant(player).sell();
                        break;
                    case 2:
                        clearConsole();
                        Character opponent = createMonster();
                        System.out.println("Вам встретился " + opponent.getName() + ": здоровье " + opponent.getHp() +
                                ", сила " + opponent.getStrength());
                        System.out.println("Ваши действия? \n 1. Уничтожить!\n 2. Постараться обойти");
                        int choice = Choice.readInt("-> ", 2);
                        if (choice == 1) {
                            battle.fight(player, opponent, new FightCallback());
                        } else if (choice == 2) {
                            System.out.println(opponent.getName() + " вас не заметил и вы покидаете опасное место");
                            break;
                        } else {
                            System.out.println("Пока вы ковырялись, вас заметили! К бою!");
                            battle.fight(player, opponent, new FightCallback());
                        }
                        break;
                    case 3:
                        clearConsole();
                        player.charInfo();
                        break;
                    case 4:
                        clearConsole();
                        takeRest();
                        break;
                    case 5:
                    default:
                        break;
                }
            } catch (NumberFormatException exc) {
                System.out.println("Введите число");
            }
        } while (route != 5);
    }
}