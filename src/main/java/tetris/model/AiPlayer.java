package tetris.model;

import neuralnetwork.TetrisNN;
import neuralnetwork.TetrisNNLearningSet;
import neuralnetwork.TetrisNNResult;
import tetris.input.KeyboardInput;

public class AiPlayer extends Player {

    private TetrisNN tetrisNN;
    private TetrisNNResult tetrisNNResult;

    private int heightAtLoopStart;
    private int scoreAtLoopStart;
    private double[] inputArrayAtLoopStart;

    public AiPlayer() {
        this.tetrisNN = new TetrisNN();
        this.tetrisNN.create("mk1");
    }

    @Override
    public void updateLoopStart(Game game) {
        this.heightAtLoopStart = game.getBuildHeight();
        this.scoreAtLoopStart = game.getTotalScore();
        this.inputArrayAtLoopStart = TetrisNN.getInputArrayFromGame(game);
        this.tetrisNNResult = this.tetrisNN.predict(game);
    }

    @Override
    public void updateLoopEnd(Game game) {

        if(game.getBuildHeight() < heightAtLoopStart) {
            System.out.println("AI: Learning this move because build height got better...");
            this.tetrisNN.learn(new TetrisNNLearningSet(
                    tetrisNNResult.left(),
                    tetrisNNResult.right(),
                    tetrisNNResult.drop(),
                    tetrisNNResult.rotate(),
                    inputArrayAtLoopStart
            ));
        }

        if(game.getBuildHeight() <= heightAtLoopStart && game.getTotalScore() > scoreAtLoopStart) {
            System.out.println("AI: Learning this move because build height did not get worse by placing this piece...");
            this.tetrisNN.learn(new TetrisNNLearningSet(
                    tetrisNNResult.left(),
                    tetrisNNResult.right(),
                    tetrisNNResult.drop(),
                    tetrisNNResult.rotate(),
                    inputArrayAtLoopStart
            ));
        }

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
