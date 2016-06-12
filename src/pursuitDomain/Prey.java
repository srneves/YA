package pursuitDomain;

import ga.GeneticAlgorithm;
import java.awt.Color;

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
}
