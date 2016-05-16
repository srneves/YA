package pursuitDomain;

import java.awt.Color;

public class Prey extends Agent{

    final private double restProbability;
    
    public Prey(Cell cell, double restProbability){
        super(cell, Color.RED);
        this.restProbability = restProbability;
    }
    
    @Override
    public void act(Environment environment) {
        //TODO
    }    
}
