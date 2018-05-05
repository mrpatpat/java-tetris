package neuralnetwork;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import tetris.model.BoardCell;
import tetris.model.Game;

import java.util.ArrayList;
import java.util.List;

public class TetrisNN {

    private NeuralNetwork nn;
    private String name;

    public void create(String name) {
        this.name = name;
        this.nn = new Perceptron(200, 4);
    }

    public void load(String name) {
        this.name = name;
        this.nn = NeuralNetwork.load(name+".nnet");
    }

    public void save() {
        this.nn.save(this.name+".nnet");
    }

    public TetrisNNResult predict(Game game) {
        this.nn.setInput(getInputArrayFromGame(game));
        this.nn.calculate();
        return this.getResultFromOutputArray(this.nn.getOutput());
    }

    private TetrisNNResult getResultFromOutputArray(double[] output) {
        double limit = 0.9;
        return new TetrisNNResult(output[0] > limit, output[1] > limit, output[2] > limit, output[3] > limit);
    }

    private double[] getInputArrayFromGame(Game game) {
        BoardCell[][] cells = game.getBoardCells();
        int w = cells.length;
        int h = cells[0].length;
        int pos = 0;
        double[] flattenedBoard = new double[w*h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                flattenedBoard[pos++] = cells[i][j].isEmpty() ? 0:1;
            }
        }
        return flattenedBoard;
    }

    public String getName() {
        return name;
    }

}
