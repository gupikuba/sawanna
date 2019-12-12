package pl.altkom.savanna_items.plants;

import pl.altkom.savanna_items.Food;
import pl.altkom.savanna_items.Type;

public interface Plant extends Food {
    public void grow();
    public Type getType();
}
