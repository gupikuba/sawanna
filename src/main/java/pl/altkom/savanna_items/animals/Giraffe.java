package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Type;

public class Giraffe extends Animal {
    private static int n = 0;
    private int num;
    private static Type type = Type.Giraffe;

    //every giraffe has his own number
    public Giraffe(Cell cell) {
        super(cell);
        num = ++n;
    }


    @Override
    //if giraffes move like other animals they die very fast
    //so if giraffe is in 11 cells range from cell where his food is
    //then he goes there if not then animal.move() function is called
    public void move(Savanna savanna) {
        int x = super.getCell().getX(), y = super.getCell().getY(),
                length = savanna.getRows(), width = savanna.getCols();

        for(int i = -11; i < 11; i++){
            for(int j = -11; j < 11; j++){
                if(Math.abs(i)+Math.abs(j)<=11 && x+j>=0 && x+j<width
                        && y+i>=0 && y+i<length && canEat(super.getCell().getPlant().getType())){
                    savanna.getCells()[y+i][x+j].moveAnimal(this);
                    return;
                }
            }
        }
        super.move(savanna);
    }

    @Override
    public boolean canEat(Type type) {
        if(type==Type.Acacia)
            return true;
        return false;
    }

    @Override
    public Type getType() {
        return Type.Giraffe;
    }

    @Override
    public Animal reproduction() {
        Giraffe g = new Giraffe(super.getCell());
        //System.out.println("\n(@)(@)(@) rodzi się "+ g + " (@)(@)(@)\n");
        return g;
    }

    @Override
    public boolean canBeEaten() {
        return super.isAlive();
    }

    @Override
    public String toString(){
        return "zyrafa "+num;
    }

}
