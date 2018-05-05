package tetris.model;

import neuralnetwork.TetrisNN;

public abstract class Player {

    private TetrisNN nn;

    public Player(TetrisNN nn) {
        this.nn = nn;
    }

    public TetrisNN getNn() {
        return nn;
    }

    public abstract void updateLoopStart(Game game);
    public abstract void updateLoopEnd(Game game);

    public abstract boolean left();

    public abstract boolean right();

    public abstract boolean drop();

    public abstract boolean rotate();

    @Override
    public abstract String toString();

}
