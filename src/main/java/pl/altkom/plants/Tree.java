package pl.altkom.plants;

public abstract class Tree implements Plant {
    int height;
    int branches;

    @Override
    public void grow() {
        height += 1;
        if(height<5){
            branches++;
        }else {
            branches+=2;
        }
    }

    @Override
    public void beEaten() {
        branches--;
    }
}
