package tetris.model;

import neuralnetwork.TetrisNN;
import neuralnetwork.TetrisNNLearningSet;
import tetris.input.KeyboardInput;

public class HumanPlayer extends Player {

    private KeyboardInput keyboardInput;
    private double[] inputArrayAtLoopStart;

    public HumanPlayer(KeyboardInput keyboardInput, TetrisNN nn) {
        super(nn);
        this.keyboardInput = keyboardInput;
    }

    @Override
    public void updateLoopStart(Game game) {
        this.inputArrayAtLoopStart = TetrisNN.getInputArrayFromGame(game);
    }

    @Override
    public void updateLoopEnd(Game game) {
        System.out.println("AI: Learning this move because build height got better...");
        this.getNn().learn(new TetrisNNLearningSet(
                left(),
                right(),
                drop(),
                rotate(),
                inputArrayAtLoopStart
        ));
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
