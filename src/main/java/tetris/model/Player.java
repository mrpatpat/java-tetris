package tetris.model;

public abstract class Player {

    public abstract void updateLoopStart(Game game);
    public abstract void updateLoopEnd(Game game);

    public abstract boolean left();

    public abstract boolean right();

    public abstract boolean drop();

    public abstract boolean rotate();

    @Override
    public abstract String toString();

}
