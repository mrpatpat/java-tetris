package neuralnetwork;

public class TetrisNNResult {

    private boolean left;
    private boolean right;
    private boolean drop;
    private boolean rotate;

    public TetrisNNResult(boolean left, boolean right, boolean drop, boolean rotate) {
        this.left = left;
        this.right = right;
        this.drop = drop;
        this.rotate = rotate;
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

}
