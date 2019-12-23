package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

import java.util.Set;

public class Lion extends Animal {
    private static int n = 0;
    private int num;
    private static Type type = Type.Lion;

    //every new lion has his own number
    public Lion(Cell cell) {
        super(cell);
        num = ++n;
    }

    //if lions move like other animals they die very fast
    //so if lion is in two cells range from cell where his food is
    //then he goes there if not then animal.move() function is called
    @Override
    public void move(Savanna savanna) {

            int x = super.getCell().getX(), y = super.getCell().getY(),
                    length = savanna.getRows(), width = savanna.getCols();

            for(int i = -2; i < 2; i++){
                for(int j = -2; j < 2; j++){
                    if(Math.abs(i)+Math.abs(j)<=2 && x+j>=0 && x+j<width
                    && y+i>=0 && y+i<length && isThereFood(savanna.getCells()[y+i][x+j].getTypes())){
                        savanna.getCells()[y+i][x+j].moveAnimal(this);
                        return;
                    }
                }
            }
            super.move(savanna);
    }

    public boolean canEat(Type type){
        if(type == Type.Antelope || type==Type.Giraffe)
            return true;
        return false;
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean canBeEaten() {
        return false;
    }

    @Override
    public Animal reproduction() {
        Lion l = new Lion(super.getCell());
        //System.out.println("\n(@)(@)(@) rodzi się "+ l + " (@)(@)(@)\n");
        return l;
    }

    @Override
    public String toString() {
        return "lew " + num;
    }
}
