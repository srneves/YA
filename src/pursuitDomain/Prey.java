package pursuitDomain;

import ga.GeneticAlgorithm;
import java.awt.Color;
import java.util.Random;

public class Prey extends Agent{

    final private double restProbability;
   
    
    public Prey(Cell cell, double restProbability){
        super(cell, Color.RED);
        this.restProbability = restProbability;
       
    }
    
    @Override
    public void act(Environment environment) {
        if( GeneticAlgorithm.random.nextDouble() > restProbability){
            execute(decide(), environment);
        }
    }
    
    private Action decide() {
        Action[] actions = Action.values();
        return actions[GeneticAlgorithm.random.nextInt(4)];
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
}
