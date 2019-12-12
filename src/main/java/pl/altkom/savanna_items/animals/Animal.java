package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;
import pl.altkom.Savanna;
import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

import java.util.Random;

public abstract class Animal implements Food{
    private static Random random = new Random();
    private final static int[][] moves = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    private Cell cell;
    private boolean alive = true;
    private boolean wasMove = false;
    private boolean sex;
    private int weeksWithoutEat = 0;
    private int age;

    public Animal(Cell cell){
        this.cell = cell;
        sex = random.nextBoolean();
    }
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
        savanna.getCells()[x][y].addAnimal(this);
    };
    public void eat(Food food){
        weeksWithoutEat = 0;
        food.beEaten();
    };

    public void nextWeek(){
        weeksWithoutEat++;
        if(weeksWithoutEat>3)
            die();
        age++;
    }
    public int getAge(){return age;}
    public abstract boolean canEat(Type type);
    public abstract Type getType();
    @Override
    public void beEaten() {
        alive = false;
    }

    public void die(){alive = false;}

    public boolean isAlive() {return alive; }

    public abstract Animal reproduction();

    public Cell getCell() {
        return cell;
    }

    public boolean getSex() {
        return sex;
    }
}
