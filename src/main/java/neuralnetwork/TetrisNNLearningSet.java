package neuralnetwork;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import tetris.model.Game;

public class TetrisNNLearningSet {

    private boolean left;
    private boolean right;
    private boolean drop;
    private boolean rotate;
    private double[] mapArray;

    public TetrisNNLearningSet(boolean left, boolean right, boolean drop, boolean rotate, double[] mapArray) {
        this.left = left;
        this.right = right;
        this.drop = drop;
        this.rotate = rotate;
        this.mapArray = mapArray;
    }

    public boolean left() {
        return left;
    }

    public boolean right() {
        return right;
    }

    public boolean drop() {
        return drop;
    }

    public boolean rotate() {
        return rotate;
    }

    public double[] getMapArray() {
        return mapArray;
    }

    public DataSet toDataSet() {
        DataSet trainingSet = new DataSet(200, 4);
        trainingSet.addRow(new DataSetRow(
                mapArray,
                new double[]{
                        left ? 1:0,
                        right ? 1:0,
                        drop ? 1:0,
                        rotate ? 1:0,
                    }
                )
        );

        return trainingSet;
    }

}
