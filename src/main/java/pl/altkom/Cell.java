package pl.altkom;

import pl.altkom.savanna_items.Type;
import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.plants.Grass;
import pl.altkom.savanna_items.plants.Plant;
import pl.altkom.savanna_items.plants.Tree;

import java.util.*;


public class Cell {
    private Savanna savanna;
    private List<Animal> animals = new ArrayList<>();
    private Set<Type> types = new HashSet<>();
    private  int x, y;
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

    //animal moving in to the cell, is adding to animals list and to types set if absent
    public void moveAnimal(Animal animal){animals.add(animal);types.add(animal.getType());}


    //adding new animal to cell and to savanna
    public void addAnimal(Animal animal){
        animals.add(animal);
        savanna.addAnimal(animal);
        types.add(animal.getType());
    }

    //updating animals number in cell
    public void updateAnimalNum(){animalNum = animals.size();}

    //returning cell coordinates in savanna
    public int getX() { return x; }
    public int getY() { return y; }

    //animals are moving through savanna
    public void moveAnimals(){
        for(int i = 0; i < animalNum; i++){
            animals.get(i).move(savanna);
        }

        //every animal above animalNum is animal which made its move already so it doesn't move again
        //and is part of a new animals list
        //set of types is also updating
        List<Animal> lTemp = new ArrayList<>();
        Set<Type> sTemp = new HashSet<>();
        for(int i = animalNum; i < animals.size(); i++) {
            Animal a = animals.get(i);
            lTemp.add(a);
            sTemp.add(a.getType());
        }
        animals = lTemp;
        types = sTemp;
    }

    //each animal can reproduce only once per turn
    private void reproduction(){
        Set s = new HashSet();
        for(int i = 0;i < animalNum; i++){
            //animal goes to the set(if can't next loop step is going)
            if(s.add(i)) {
                Animal a1 = animals.get(i);
                //if set doesn't contain animal and animal has the same type and different sex
                // than a1 they reproduce and a2 goes to the set and savanna get new animal
                for (int j = 0; j < animalNum; j++) {
                    if(!s.contains(j)){
                        Animal a2 = animals.get(j);
                        if(a1.getType()==a2.getType() && a1.getSex()!=a2.getSex()) {
                            Animal a = a1.reproduction();
                            addAnimal(a);
                            savanna.addBornAnimal(a);
                            s.add(j);
                        }
                    }
                }
            }
        }

    }

   //animal are eating
    private void eat() {
        for(int i = 0;i < animalNum; i++){
            Animal a1 = animals.get(i);
            a1.nextWeek();
            //if animal lives after calling nextWeek() method then first
            //is checked whether can eat cell's plant, if yes then next loop step is going
            if(!a1.isAlive())continue;
            if(a1.canEat(getPlant().getType()) && getPlant().canBeEaten()){
                a1.eat(getPlant());
                continue;
            }
            //if not is checked if animal can eat another animal
            for(int j = 0; j < animalNum; j++){
                Animal a2 = animals.get(j);
                if(a2.canBeEaten() && a1.canEat(a2.getType())){
                    savanna.addEatenAnimal(a2);
                    a1.eat(a2);
                    break;
                }
            }
        }
    }

    //updating animals list and animalTypes map depending on whether animal is alive or not
    private void updateAnimalList() {
        List<Animal> lTemp = new ArrayList<>();
        Set<Type> sTemp = new HashSet<>();
        for(Animal a : animals)
            if(a.isAlive()) {
                lTemp.add(a);
                sTemp.add(a.getType());
            }
            else{
               savanna.removeAnimal(a);
            }

        animals = lTemp;
        types = sTemp;
    }

    //next week of animal live
    public void nextWeek(){
        reproduction();
        eat();
        updateAnimalList();
        updateAnimalNum();
    }

    public Set<Type> getTypes(){return types;}
}
