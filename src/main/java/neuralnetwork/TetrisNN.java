package neuralnetwork;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.core.learning.error.MeanSquaredError;
import org.neuroph.nnet.ConvolutionalNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.comp.Dimension2D;
import org.neuroph.nnet.learning.ConvolutionalBackpropagation;
import tetris.model.BoardCell;
import tetris.model.Game;

import java.util.ArrayList;
import java.util.List;

public class TetrisNN {

    private NeuralNetwork nn;
    private String name;

    public void create(String name) {
        this.name = name;
        //this.nn = new Perceptron(200, 4);

        int maxIter = 30000; // Integer.parseInt(args[0]);
        double learningRate = 5; //  Double.parseDouble(args[2]);

        ConvolutionalNetwork convolutionNetwork = new ConvolutionalNetwork.Builder()
                .withInputLayer(10, 10, 1)
                .withConvolutionLayer(5, 5, 1)
                .withPoolingLayer(8, 16)
                .withConvolutionLayer(5, 5, 1)
                .withPoolingLayer(6, 12)
                .withConvolutionLayer(5, 5, 1)
                .withFullConnectedLayer(4)
                .build();

        ConvolutionalBackpropagation backPropagation = new ConvolutionalBackpropagation();
        backPropagation.setLearningRate(learningRate);
        backPropagation.setMaxIterations(maxIter);

        convolutionNetwork.setLearningRule(backPropagation);

        this.nn = convolutionNetwork;
        this.nn.randomizeWeights();

    }

    public void load(String name) {
        this.name = name;
        this.nn = NeuralNetwork.load(name+".nnet");
    }

    public void save() {
        this.nn.save(this.name+".nnet");
    }

    public TetrisNNResult predict(Game game) {
        this.nn.setInput(TetrisNN.getInputArrayFromGame(game));
        this.nn.calculate();
        return this.getResultFromOutputArray(this.nn.getOutput());
    }

    public void learn(TetrisNNLearningSet learningSet) {
        this.nn.learn(learningSet.toDataSet());
    }

    private TetrisNNResult getResultFromOutputArray(double[] output) {


        //all the same
        if(output[0] == output[1] && output[1] == output[2] && output[2] == output[3]){
            switch ((int)(Math.random()*4.0)) {
                case 0: return new TetrisNNResult(true, false, false, false);
                case 1: return new TetrisNNResult(false, true, false, false);
                case 2: return new TetrisNNResult(false, false, true, false);
                case 3: return new TetrisNNResult(false, false, false, true);
            }
        }
        System.out.println(output[0] + " " + output[1] + " " + output[2] + " " +output[3] + " ");

        // we have a winner button
        double max = 0;
        TetrisNNResult result = new TetrisNNResult(false, false, false, false);
        for (int i = 0; i < output.length; i++) {
            if(max < output[i]){
                max = output[i];
                switch (i) {
                    case 0: result = new TetrisNNResult(true, false, false, false); break;
                    case 1: result = new TetrisNNResult(false, true, false, false); break;
                    case 2: result = new TetrisNNResult(false, false, true, false); break;
                    case 3: result = new TetrisNNResult(false, false, false, true); break;
                }
            }

        }
        return result;
    }

    public static double[] getInputArrayFromGame(Game game) {
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
