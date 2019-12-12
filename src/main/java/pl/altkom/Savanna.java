package pl.altkom;

import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.plants.Acacia;
import pl.altkom.savanna_items.plants.Grass;

import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Savanna {
    public static final int YEAR = 56;
    private int week = 0;
    private Cell[][] cells;
    private int rows;
    private int cols;

    public Savanna(int rows, int cols, int trees) {
        this.rows = rows;
        this.cols = cols;
        Random random = new Random();
        cells = new Cell[rows][cols];
        for (int i = 0; i < trees; i++) {
            int r, c;
            do {
                r = random.nextInt(rows);
                c = random.nextInt(cols);
            } while (cells[r][c] != null);
            cells[r][c] = Cell.withTree(this, r, c,new Acacia());
        }

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (cells[r][c] == null) {
                    cells[r][c] = Cell.withGrass(this, r, c, new Grass());
                }
            }
        }
    }
    public int getWeek(){return week;}

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public void nextWeek() {
        week++;
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].getPlant().grow();
            }
        }
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].nextWeek();
            }
        }
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].moveAnimals();
            }
        }
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++){
                cells[r][c].updateAnimalNum();
            }
        }
    }

}

