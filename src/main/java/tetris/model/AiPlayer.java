package tetris.model;

import neuralnetwork.TetrisNN;
import neuralnetwork.TetrisNNResult;
import tetris.input.KeyboardInput;

public class AiPlayer extends Player {

    private KeyboardInput keyboardInput;

    private TetrisNN tetrisNN;
    private TetrisNNResult tetrisNNResult;

    public AiPlayer(KeyboardInput keyboardInput) {
        this.keyboardInput = keyboardInput;
        this.tetrisNN = new TetrisNN();
        this.tetrisNN.create("mk1");
    }

    @Override
    public void update(Game game) {
        this.tetrisNNResult = this.tetrisNN.predict(game);
    }

    @Override
    public boolean left() {
        return this.tetrisNNResult.left();
    }

    @Override
    public boolean right() {
        return this.tetrisNNResult.right();
    }

    @Override
    public boolean drop() { return this.tetrisNNResult.drop(); }

    @Override
    public boolean rotate() {
        return this.tetrisNNResult.rotate();
    }

    @Override
    public String toString() {
        return "AI Player (" + this.tetrisNN.getName() + ")";
    }

}
