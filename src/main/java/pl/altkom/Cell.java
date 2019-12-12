package pl.altkom;

import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.plants.Grass;
import pl.altkom.savanna_items.plants.Plant;
import pl.altkom.savanna_items.plants.Tree;

import java.util.*;


public class Cell {
    private Savanna savanna;
    private List<Animal> animals = new ArrayList<>();
    private final int x, y;
    private final Plant plant;
    private int animalNum;

    private Cell(Savanna savanna,int x, int y, Plant plant) {
        this.savanna = savanna;
        this.x = x;
        this.y = y;
        this.plant = plant;
    }

    public static Cell withGrass(Savanna savanna, int y, int x, Grass grass) {
        return new Cell(savanna, y, x,grass);
    }

    public static Cell withTree(Savanna savanna, int y, int x, Tree tree) {
        return new Cell(savanna, y, x,tree);
    }

    public Plant getPlant() {
        return plant;
    }

    public void addAnimal(Animal animal){animals.add(animal);}
    public void updateAnimalNum(){animalNum = animals.size();}
    public int getX() { return x; }
    public int getY() { return y; }
    public void moveAnimals(){
        for(int i = 0; i < animalNum; i++){
            animals.get(i).move(savanna);
        }
        List<Animal> temp = new ArrayList<>();
        for(int i = animalNum; i < animals.size(); i++)
            temp.add(animals.get(i));

        animals = temp;
    }
    public void nextWeek(){
        if(savanna.getWeek()%6==0)
            reproduction();

        for(int i = 0;i < animalNum; i++){
            Animal a1 = animals.get(i);
            a1.nextWeek();
            if(!a1.isAlive())continue;
            if(a1.canEat(getPlant().getType()) && getPlant().canBeEaten()){
                a1.eat(getPlant());
                continue;
            }
            for(int j = 0; j < animalNum; j++){
                Animal a2 = animals.get(j);
                if(a2.canBeEaten() && a1.canEat(a2.getType())){
                    a1.eat(a2);
                    break;
                }
            }
        }
        List<Animal> temp = new ArrayList<>();
        for(Animal a : animals)
            if(a.isAlive())
                temp.add(a);

            animals = temp;
            updateAnimalNum();
    }

    private void reproduction(){
        Set s = new HashSet();
        for(int i = 0;i < animalNum; i++){
            if(s.add(i)) {
                Animal a1 = animals.get(i);
                for (int j = 0; j < animalNum; j++) {
                    if(!s.contains(j)){
                        Animal a2 = animals.get(j);
                        if(a1.getType()==a2.getType() && a1.getSex()!=a2.getSex()) {
                            addAnimal(a1.reproduction());
                            s.add(j);
                        }
                    }
                }
            }
        }

    }

}
