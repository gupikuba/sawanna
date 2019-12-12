package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

import java.util.Set;

public class Lion extends Animal {

    private static Type type = Type.Lion;

    public Lion(Cell cell) {
        super(cell);
    }

    public boolean canEat(Type type){
        if(type == Type.Antelope)
            return true;
        return false;
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void nextWeek() {
        super.nextWeek();
        if(super.getAge()>12 * Savanna.YEAR)
            super.die();
    }

    @Override
    public boolean canBeEaten() {
        return false;
    }

    @Override
    public Animal reproduction() {
        return new Lion(super.getCell());
    }
}
