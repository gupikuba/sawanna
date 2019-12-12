package pl.altkom.savanna_items.plants;

import pl.altkom.savanna_items.Type;

public class Acacia extends Tree {
    private static Type type = Type.Acacia;

    public Acacia() {
        super(4, 8);
    }

    @Override
    public Type getType() {
        return type;
    }
}
