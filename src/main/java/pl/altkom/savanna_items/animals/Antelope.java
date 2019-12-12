package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

public class Antelope extends Animal implements Food {
    private static Type type = Type.Antelope;
    public Antelope(Cell cell) {
        super(cell);
    }

    @Override
    public boolean canEat(Type type) {
        if(type == Type.Grass)
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
        if(super.getAge() > 15 * Savanna.YEAR)
            super.die();
    }

    @Override
    public boolean canBeEaten() {
        return super.isAlive();
    }

    @Override
    public Animal reproduction() {
        return new Antelope(super.getCell());
    }
}
