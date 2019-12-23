package pl.altkom;

import pl.altkom.savanna_items.Type;
import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.animals.AnimalProvider;
import pl.altkom.savanna_items.plants.Acacia;
import pl.altkom.savanna_items.plants.Grass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Savanna {
    private Map<Type, Integer> animalTypes = new HashMap<>();
    private Map<Type, Integer> deadAnimalsPerTurn = new HashMap<>();
    private Map<Type, Integer> deadAnimals = new HashMap<>();
    private Map<Type, Integer> bornAnimalsPerTurn = new HashMap<>();
    private Map<Type, Integer> bornAnimals = new HashMap<>();
    private Map<Type, Integer> eatenAnimalsPerTurn = new HashMap<>();
    private Map<Type, Integer> eatenAnimals = new HashMap<>();

    private int week = 0;
    private Cell[][] cells;
    private int rows;
    private int cols;

    //creating savanna which rows on cols dimension and trees number of trees
    public Savanna(int rows, int cols, int trees) {
        this.rows = rows;
        this.cols = cols;
        Random random = new Random();
        cells = new Cell[rows][cols];

        // trees are randomly inserting in savanna
        for (int i = 0; i < trees; i++) {
            int r, c;
            do {
                r = random.nextInt(rows);
                c = random.nextInt(cols);
            } while (cells[r][c] != null);
            cells[r][c] = Cell.withTree(this, r, c,new Acacia());
        }

        //all rest cells which have no tree are creating with grass
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (cells[r][c] == null) {
                    cells[r][c] = Cell.withGrass(this, r, c, new Grass());
                }

                //there is 2/3 chance of animal will be insert in the cell
                if(random.nextInt(3)>0)
                    cells[r][c].addAnimal(AnimalProvider.provide(cells[r][c]));
            }
            //updating animalNum in every cell
            for(int c = 0; c < cols; c++)
                cells[r][c].updateAnimalNum();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell[][] getCells() {
        return cells;
    }

    //adding animal to the savanna, animals are divide into types
    public void addAnimal(Animal a){
        Integer i = animalTypes.get(a.getType());
        animalTypes.put(a.getType(), i==null ? 1 : i+1);
    }

    //removing animal from savanna, decreasing type counter by one
    public void removeAnimal(Animal a){
        addDeadAnimal(a);
        Integer i = animalTypes.get(a.getType());
        if(i>0){
            animalTypes.put(a.getType(), i-1);
        }
    }


    public void addToMap(Animal a, Map<Type, Integer> m1, Map<Type, Integer> m2){
        Type t = a.getType();
        Integer i = m1.get(t);
        m1.put(t, i==null ? 1 : i+1);

        i = m2.get(t);
        m2.put(t, i==null ? 1 : i+1);
    }

    public void addDeadAnimal(Animal a){
        addToMap(a, deadAnimals, deadAnimalsPerTurn);
    }
    public void addBornAnimal(Animal a){
        addToMap(a, bornAnimals, bornAnimalsPerTurn);
    }

    public void addEatenAnimal(Animal a){
        addToMap(a, eatenAnimals, eatenAnimalsPerTurn);
    }

    //conducting simulation of one day in savanna
    public void nextWeek() {
        week++;
        System.out.println("---------------------------------------------------------------------------------------------\nweek "+week);

        //...AnimalsPerTurn are clearing up
        deadAnimalsPerTurn.clear();
        bornAnimalsPerTurn.clear();
        eatenAnimalsPerTurn.clear();

        //every plant in cells is growing and every cell calls his nextWeek() method
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].getPlant().grow();
                cells[r][c].nextWeek();
            }
        }

        //animals from every cell are moving through the savanna
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].moveAnimals();
            }
        }

        //updating animalNum in every cell
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].updateAnimalNum();
            }
        }
        //week summary is printed
        System.out.println(summary());
    }

    //function return string describing types and their amount in map
    public String summary(Map<Type, Integer>m){
        String s = "";
        for(Map.Entry<Type, Integer> e : m.entrySet()){
            s += ""+e.getValue()+" "+e.getKey()+", ";
        }
        return s.length()==0 ? "brak" : s.substring(0,s.length()-2);
    }

    //function joins summary(Map) method from all maps in savanna
    public String summary(){
        String s = "\n";
        s += "zwierzeta urodzone w ciagu tego tygodnia: "+summary(bornAnimalsPerTurn)+"\n";
        s += "zwierzeta, które zginely w ciągu tego tygodnia: "+summary(deadAnimalsPerTurn)+", w tym zjedzonych: "
                + summary(eatenAnimalsPerTurn)+"\n\n";
        s += "laczna liczba urodzonych zwierzat: "+summary(bornAnimals)+"\n";
        s += "laczna liczba zwierzat, ktore zginely: "+summary(deadAnimals)+", w tym zjedzonych: "
                + summary(eatenAnimals)+"\n";
        s += "liczba zwierzat: " + summary(animalTypes)+
                "\n---------------------------------------------------------------------------------------------\n";

        return s;
    }

}

