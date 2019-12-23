package pl.altkom.savanna_items.plants;

import pl.altkom.savanna_items.Type;

public class Acacia extends Tree {
    private static int n = 0;
    private int num;
    private static Type type = Type.Acacia;

    public Acacia() {
        super(4, 8);
        num = ++n;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Akacja "+num;
    }
    public String shortName(){return "|A|";}
}
