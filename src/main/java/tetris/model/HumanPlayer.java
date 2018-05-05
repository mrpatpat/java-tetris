package tetris.model;

import tetris.input.KeyboardInput;

public class HumanPlayer extends Player {

    private KeyboardInput keyboardInput;

    public HumanPlayer(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    @Override
    public void updateLoopStart(Game game) {

    }

    @Override
    public void updateLoopEnd(Game game) {

    }

    @Override
    public boolean left() {
        return keyboardInput.left();
    }

    @Override
    public boolean right() {
        return keyboardInput.right();
    }

    @Override
    public boolean drop() {
        return keyboardInput.drop();
    }

    @Override
    public boolean rotate() {
        return keyboardInput.rotate();
    }

    @Override
    public String toString() {
        return "Human Player";
    }

}
