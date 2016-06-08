package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.RealVectorIndividual;

public class MutationDouble <I extends RealVectorIndividual> extends Mutation<I> {

    private final double delta;
    
    public MutationDouble(double probability, double delta/*COMPLETE?*/) {
        super(probability);
        this.delta =delta;
        //COMPLETE
    }

    @Override
    public void run(I ind) {
        //TODO
        int numGenes = ind.getNumGenes();

        for (int i = 0; i < numGenes; i++) {
            double rand = GeneticAlgorithm.random.nextDouble();

            if (rand < probability) {
                ind.setGene(i, ind.getGene(i) + (GeneticAlgorithm.random.nextDouble() * 2 - 1) * delta);
            }
        }
    }
    
    @Override
    public String toString(){
        return "Uniform distribution mutation (" + probability /* + COMPLETE?*/;
    }
}