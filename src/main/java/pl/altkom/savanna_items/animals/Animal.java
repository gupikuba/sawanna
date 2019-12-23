package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

import java.util.Random;
import java.util.Set;

public abstract class Animal implements Food{
    private static Random random = new Random();
    private final static int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    private Cell cell;
    private boolean alive = true;
    private boolean sex;
    private int weeksWithoutEat = 0;


    public Animal(Cell cell){
        this.cell = cell;
        sex = random.nextBoolean();
    }

    //animals move randomly 0-3 cells (0-3 steps) in one of four directions in one step
    //step can be done if after it animal is still in savanna's border if not is randomized until successful
    public void move(Savanna savanna){
        int distance = random.nextInt(4);
        int x = cell.getX(), y = cell.getY();
        while(distance-->0){
            int[]t;
            do {
                t = moves[random.nextInt(4)];
            }while(!(x+t[0]>=0 && x+t[0]<savanna.getCols() &&
                    y+t[1]>=0 && y+t[1]<savanna.getRows()));
            x += t[0];
            y += t[1];
        }
        savanna.getCells()[x][y].moveAnimal(this);
    };


    public void nextWeek(){
        weeksWithoutEat++;

        if(weeksWithoutEat>3) {
            //System.out.println("\n[*][*][*] "+this+" umiera z glodu [*][*][*]\n");
            die();
        }
    }

    public void eat(Food food){
        weeksWithoutEat = 0;
        food.beEaten();
    };

    @Override
    public void beEaten() {
        alive = false;
    }


    public void die(){alive = false;}

    public boolean isAlive() {return alive; }

    public Cell getCell() {
        return cell;
    }

    public boolean getSex() {
        return sex;
    }

    //checking if set contains food animal can eat
    public boolean isThereFood(Set<Type> s ){
        for(Type t : s)
            if(canEat(t))
                return true;

            return false;
    }

    public abstract boolean canEat(Type type);

    public abstract Type getType();

    public abstract Animal reproduction();
}
