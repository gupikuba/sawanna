package pl.altkom;

import pl.altkom.plants.Grass;
import pl.altkom.plants.Plant;
import pl.altkom.plants.Tree;

public class Cell {
    private final Plant plant;

    private Cell(Plant plant) {
        this.plant = plant;
    }

    public static Cell withGrass(Grass grass) {
        return new Cell(grass);
    }

    public static Cell withTree(Tree tree) {
        return new Cell(tree);
    }

    public Plant getPlant() {
        return plant;
    }
}
