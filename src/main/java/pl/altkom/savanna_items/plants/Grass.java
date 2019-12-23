package pl.altkom.savanna_items.plants;

import pl.altkom.savanna_items.Type;

public class Grass implements Plant {
    private static Type type= Type.Grass;

    public Grass(){}
    private int amount = 10;
    @Override
    public void grow() {
        amount += 4;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void beEaten() {
        amount -= 1;
    }


    @Override
    public boolean canBeEaten() {
        if(amount==0)
            System.out.println("\n### za malo trawy do jedzenia ###\n");
        return amount >= 1;
    }

}
