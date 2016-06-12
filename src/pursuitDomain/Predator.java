package pursuitDomain;

import java.awt.Color;

public class Predator extends Agent {
   
    final private int inputLayerSize;
    final private int hiddenLayerSize;
    final private int outputLayerSize;

    /**
     * Network inputs array.
     */
    final private int[] inputs;
    /**
     * Hiddden layer weights.
     */
    final private double[][] w1;
    /**
     * Output layer weights.
     */
    final private double[][] w2;
    /**
     * Hidden layer activation values.
     */
    final private double[] hiddenLayerOutput;
    /**
     * Output layer activation values.
     */
    final private int[] output;

    public Predator(
            Cell cell,
            int inputLayerSize,
            int hiddenLayerSize,
            int outputLayerSize) {
        super(cell, Color.BLUE);
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;
        inputs = new int[inputLayerSize];
        inputs[inputs.length - 1] = -1; //bias entry
        w1 = new double[inputLayerSize][hiddenLayerSize]; // the bias entry for the hidden layer neurons is already counted in inputLayerSize variable
        w2 = new double[hiddenLayerSize + 1][outputLayerSize]; // + 1 due to the bias entry for the output neurons
        hiddenLayerOutput = new double[hiddenLayerSize + 1];
        hiddenLayerOutput[hiddenLayerSize] = -1; // the bias entry for the output neurons
        output = new int[outputLayerSize];
    }

    @Override
    public void act(Environment environment) {
        buildPerception(environment);
        execute(decide(), environment);
    }

    //Predators' coordinates relative to the Prey
    protected void buildPerception(Environment environment) {
       int i=0;
        for(Predator p : environment.getPredators()){
            inputs[i] = calculatePredatorPreyDistance(environment.getPrey(), true);
            inputs[i+1] = calculatePredatorPreyDistance(environment.getPrey(), false);
            i++;
        }
            
    }

    private Action decide() {
        forwardPropagation();
        //Here we are assuming that the output has two elements, only
        if (output[0] == 0 && output[1] == 0) {
            return Action.NORTH;
        } else if (output[0] == 0 && output[1] == 1) {
            return Action.SOUTH;
        } else if (output[0] == 1 && output[1] == 0) {
            return Action.WEST;
        }
        return Action.EAST;
    }
    
    public int calculatePredatorPreyDistance(Prey prey, boolean isVertical) {
        int value = 0;
        if(isVertical){
            value = Math.abs(prey.cell.getLine() - this.cell.getLine());
        }else{
            value = Math.abs(prey.cell.getColumn() - this.cell.getColumn());
        }
        if(value > 5){
            return (10 - value);
        }
        return value;
    }
    
    protected void execute(Action action, Environment environment) {
        Cell nextCell;
        switch(action){
            case NORTH:nextCell = environment.getNorthCell(cell);
                break;
            case SOUTH:nextCell = environment.getSouthCell(cell);
                break;
            case WEST:nextCell = environment.getWestCell(cell);
                break;
            default:nextCell = environment.getEastCell(cell);
                break;
        }
        if (!nextCell.hasAgent()) {
            setCell(nextCell);
        }
    }

    /**
     * Initializes the network's weights
     * 
     * @param weights vector of weights comming from the individual.
     * @param k integer to function as weights index.
     */
    public void setWeights(double[] weights, int k) {
        for(int i = 0;i < inputLayerSize;i++){
            for(int j = 0;j < hiddenLayerSize;j++){
                w1[i][j] = weights[k++];
            }
        }
        for(int i = 0;i < hiddenLayerSize+1;i++){
            for(int j = 0;j < outputLayerSize;j++){
                w2[i][j] = weights[k++];
            }
        }
    }
    
    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     *
     */
    private void forwardPropagation() {
        double x;
        for(int j = 0;j < hiddenLayerSize;j++){
            x = 0;
            for(int i = 0;i < inputLayerSize;i++){
                x += inputs[i] * w1[i][j];
            }
            hiddenLayerOutput[j] = 1/(1+Math.pow(Math.E, -x));
        }
        
        for(int j = 0;j < outputLayerSize;j++){
            x = 0;
            for(int i = 0;i < hiddenLayerSize+1;i++){
                x += hiddenLayerOutput[i] * w2[i][j];
            }
            output[j] = (int) Math.round(1/(1+Math.pow(Math.E, -x)));
        }
    }
}
