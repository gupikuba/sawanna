package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

public class Antelope extends Animal implements Food {
    private static int n = 0;
    private int num;
    private static Type type = Type.Antelope;

    //every antelope has his own number
    public Antelope(Cell cell) {
        super(cell);
       num = ++n;
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
    public boolean canBeEaten() {
        return super.isAlive();
    }

    @Override
    public Animal reproduction() {
        Antelope a = new Antelope(super.getCell());
        //System.out.println("\n(@)(@)(@) rodzi się "+ a + " (@)(@)(@)\n");
        return a;
    }

    @Override
    public String toString() {
        return "antylopa " + num;
    }

}
