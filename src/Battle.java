import java.util.concurrent.Executor;

public class Battle {
    public void fight(Character player, Character monster, Realm.FightCallback fightCallback) {
        Runnable confrontation = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println(" ход " + turn);
                if (turn % 2 != 0) {
                    isFightEnded = makeHIt(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHIt(player, monster, fightCallback);
                }
                turn++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }
        };
        Executor executor = (Runnable::run);
        executor.execute(confrontation);
    }

    private boolean makeHIt(Character victim, Character fighter, Realm.FightCallback fightCallback) {
        int hit = fighter.attack();
        int victimHealth = victim.getHp() - hit;
        if (hit != 0) {
            System.out.printf("%s нанес удар в %d единиц ", fighter.getName(), hit);
            System.out.printf("и у %s осталось %d единиц здоровья \n", victim.getName(), victimHealth);
        } else {
            System.out.printf(" %s промахнулся \n", fighter.getName());
        }
        if (victimHealth <= 0 && victim instanceof Player) {
            System.out.println("++++ Вы погибли ++++");
            fightCallback.fightLost();
            return true;
        } else if (victimHealth <= 0) {
            System.out.println("!!!!! Победа!!!!!");
            int goldEarned = (int) (Math.random() * victim.getExperience() + 2);
            System.out.printf("Вы получаете %d опыта и %d золота \n" +
                    "У вас осталось %d очков здоровья", victim.getExperience(), goldEarned, fighter.getHp());
            fighter.setExperience(fighter.getExperience() + victim.getExperience());
            ((Player) fighter).gold += goldEarned;
            checkLvlUp(fighter);
            fightCallback.fightWin();
            return true;
        } else {
            victim.setHp(victimHealth);
            return false;
        }
    }

    private void checkLvlUp(Character character) {
        if (character.getExperience() >= 40 && Realm.getLevel() == 2) {
            System.out.println("\n!!!!!!!ПОЗДРАВЛЯЕМ!!!!!!\nВаш уровень 3!");
            System.out.println("Выши характеристики выросли! Проверьте!");
            Realm.setLevel(3);
            character.setMaxHp(300);
            character.setHp(character.getMaxHp());
            character.setStrength(100);
            character.setAgility(70);
        } else if (character.getExperience() >= 10 && Realm.getLevel() == 1) {
            System.out.println("\n!!!!!!!ПОЗДРАВЛЯЕМ!!!!!!\nВаш уровень 2!");
            System.out.println("Выши характеристики выросли! Проверьте!");
            Realm.setLevel(2);
            character.setMaxHp(200);
            character.setHp(character.getMaxHp());
            character.setStrength(50);
            character.setAgility(50);
        }
    }
}
