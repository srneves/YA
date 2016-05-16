package pursuitDomain;

import java.awt.Color;
import java.util.List;
import java.util.Random;

public class Prey extends Agent{

    final private double restProbability;
    private Random rnd;
    
    public Prey(Cell cell, double restProbability){
        super(cell, Color.RED);
        this.restProbability = restProbability;
        rnd = new Random();
    }
    
    @Override
    public void act(Environment environment) {
        if( rnd.nextInt(1) > restProbability){
            List<Cell> freeCells = environment.getFreeSorroundingCells(cell);
            Cell nextCell = freeCells.get(rnd.nextInt(freeCells.size()));
        }
    }
}
