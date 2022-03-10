package Goods;

import java.util.ArrayList;
import java.util.List;

public class ShowCase {
    public List<Potion> showCaseList;

    public ShowCase() {
        this.showCaseList = new ArrayList<>();
        showCaseList.add(new Potion(10, 10));
        showCaseList.add(new Potion(25, 20));
        showCaseList.add(new Potion(50, 40));
        showCaseList.add(new Potion(100, 80));
    }

    public void print() {
//        for (Potion potion : showCaseList) {
//            System.out.println(potion);

        for (int i = 0; i < showCaseList.size(); i++) {
            System.out.print(i+1 + "\t");
            System.out.println(showCaseList.get(i));
        }
    }
}

