/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pursuitDomain;

import ga.GeneticAlgorithm;

/**
 *
 * @author Joao
 */
public class PredatorRandom extends Predator{
    
    public PredatorRandom(Cell cell, int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {
        super(cell, inputLayerSize, hiddenLayerSize, outputLayerSize);
    }

    @Override
    public void act(Environment environment) {
        buildPerception(environment);
        execute(Action.values()[GeneticAlgorithm.random.nextInt(4)], environment);
    }
    
}
