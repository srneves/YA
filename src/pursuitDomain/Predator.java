package pursuitDomain;

import ga.GeneticAlgorithm;
import java.awt.Color;
import java.util.List;
import java.util.Random;

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
    
    public void actRandom(Environment environment){
        Random rnd = new Random();
        execute(Action.values()[rnd.nextInt(4)], environment);
    }
    
    void actAdhoc(Environment environment) {
        execute(decideAdhoc(environment), environment);
    }

    //Predators' coordinates relative to the Prey
    private void buildPerception(Environment environment) {
        
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
    
    private Action decideAdhoc(Environment environment) {
        Prey prey =  environment.getPrey();
        int verticalDist = calculatePredatorPreyDistance(this.cell.getLine(), prey.cell.getLine());
        int horizontalDist = calculatePredatorPreyDistance(this.cell.getColumn(), prey.cell.getColumn());
        
        if(verticalDist > horizontalDist){
            if(horizontalDist > 0){
                return Action.EAST;
            }else{
                return Action.WEST;
            }
        }else{
            if(verticalDist > 0){
                return Action.SOUTH;
            }else{
                return Action.NORTH;
            }
        }
    }
    
    private int calculatePredatorPreyDistance(int predatorCellValue, int preyCellValue) {
        return preyCellValue - predatorCellValue;
    }

    private void execute(Action action, Environment environment) {
        Cell nextCell;
        if (action == Action.NORTH) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST) {
            nextCell = environment.getWestCell(cell);
        } else {
            nextCell = environment.getEastCell(cell);
        }

        if (!nextCell.hasAgent()) {
            setCell(nextCell);
        }
    }

    /**
     * Initializes the network's weights
     * 
     * @param weights vector of weights comming from the individual.
     */
    public void setWeights(double[] weights) {
        //TODO
        for(int i = 0; i < inputLayerSize; i++){
            weights[i] = GeneticAlgorithm.random.nextDouble();
        }
    }
    
    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     *
     */
    private void forwardPropagation() {
        //TODO
    }
}
