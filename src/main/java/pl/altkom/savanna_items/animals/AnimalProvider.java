package pl.altkom.savanna_items.animals;

import pl.altkom.Cell;

import java.util.Random;

public class AnimalProvider {
    public static Animal provide(Cell cell){
        Random random = new Random();
        int x = random.nextInt(5);

            if(x==0){ return new Lion(cell);}
            if(x==1){return new Giraffe(cell);}

        return new Antelope(cell);
    }
}
