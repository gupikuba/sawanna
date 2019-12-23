package pl.altkom;

import pl.altkom.savanna_items.Type;
import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.animals.Lion;

import java.util.*;

/*
Kuba Cyran
the program simulates life in the savanna
in order rows, columns, number of trees and days are given as main function arguments
 */
public class App
{
    public static void main( String[] args ) {
        try{
            int rows = Integer.parseInt(args[0]), cols = Integer.parseInt(args[1]),
                    trees = Integer.parseInt(args[2]), days = Integer.parseInt(args[3]);

            Savanna savanna = new Savanna(rows, cols, trees);
            System.out.println("stan poczatkowy: \n#############################################################################################\n"
                    + savanna.summary() +
                    "#############################################################################################\n");

            for (int i = 0; i < days; i++)
                savanna.nextWeek();
        }catch (Exception e){
            System.out.println("Błąd. Niepoprawne argumenty");
        }

    }
}
