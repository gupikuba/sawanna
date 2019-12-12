package pl.altkom.savanna_items.plants;

public abstract class Tree implements Plant {
    int height;
    int branches;

    public Tree(int height, int branches) {
        this.height = height;
        this.branches = branches;
    }

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

    @Override
    public boolean canBeEaten() {
        return branches > 0;
    }
}
