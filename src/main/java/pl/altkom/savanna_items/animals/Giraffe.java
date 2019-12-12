package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Type;

public class Giraffe extends Animal {

    public Giraffe(Cell cell) {
        super(cell);
    }

    @Override
    public boolean canEat(Type type) {
        if(type==Type.Acacia)
            return true;
        return false;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Animal reproduction() {
        return null;
    }

    @Override
    public void nextWeek() {
        super.nextWeek();
        if(super.getAge() > 20 * Savanna.YEAR)
            super.die();
    }

    @Override
    public boolean canBeEaten() {
        return false;
    }
}
