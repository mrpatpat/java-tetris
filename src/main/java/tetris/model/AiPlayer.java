package tetris.model;

import tetris.input.KeyboardInput;

public class AiPlayer extends Player {

    private KeyboardInput keyboardInput;

    public AiPlayer(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
    }

    @Override
    public void update(Game game) {

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
    public boolean drop() { return true; /* just for testing :) */ }

    @Override
    public boolean rotate() {
        return keyboardInput.rotate();
    }

    @Override
    public String toString() {
        return "AI Player";
    }

}
