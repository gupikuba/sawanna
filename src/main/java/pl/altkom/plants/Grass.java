package pl.altkom.plants;

public class Grass implements Plant {
    int size = 0;
    @Override
    public void grow() {
        size += 2;
    }

    @Override
    public void beEaten() {
        size -= 2;
    }
}
