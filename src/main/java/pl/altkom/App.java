package pl.altkom;

import pl.altkom.savanna_items.Type;
import pl.altkom.savanna_items.animals.Animal;
import pl.altkom.savanna_items.animals.Lion;

import java.util.*;

public class App
{
    public static void main( String[] args )
    {
        List<String >l = new ArrayList<>();
        String s = "fidjfid", s2 = "kuba";
        l.add(s);
        l.add(s2);
        l.add(s);
        System.out.println(l);
        for(int i = 0;i<l.size(); i++){
            System.out.println(l.get(i));
            if(i==1)l.add(l.get(i));
        }
        List<String>temp = new ArrayList<>();
        for(int i = 2; i<l.size(); i++)
            temp.add(l.get(i));
        l = temp;
        System.out.println(l);

        System.out.println(l);

    }
}
