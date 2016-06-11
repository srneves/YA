/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pursuitDomain;

/**
 *
 * @author Joao
 */
public class PredatorAdhoc extends Predator {
    
    public PredatorAdhoc(Cell cell, int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {
        super(cell, inputLayerSize, hiddenLayerSize, outputLayerSize);
    }

    @Override
    public void act(Environment environment) {
        buildPerception(environment);
        execute(decide(environment), environment);
    }
    
    private Action decide(Environment environment) {
        Prey prey =  environment.getPrey();
        int verticalDist = calculatePredatorPreyDistance(prey, true);
        int horizontalDist = calculatePredatorPreyDistance(prey, false);
        
        if(verticalDist < horizontalDist){
            if(horizontalDist >= 1){
                return Action.EAST;
            }else{
                return Action.WEST;
            }
        }else{
            if(verticalDist >= 1){
                return Action.SOUTH;
            }else{
                return Action.NORTH;
            }
        }
    }
}
