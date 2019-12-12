package pl.altkom.savanna_items.plants;

import pl.altkom.savanna_items.Type;

public class Grass implements Plant {
    private static Type type= Type.Grass;

    private int size = 4;
    @Override
    public void grow() {
        size += 2;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void beEaten() {
        size -= 2;
    }

    @Override
    public boolean canBeEaten() {
        return size >= 2;
    }

}
