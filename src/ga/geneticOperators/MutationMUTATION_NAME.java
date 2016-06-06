package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.RealVectorIndividual;

public class MutationMUTATION_NAME <I extends RealVectorIndividual> extends Mutation<I> {

   
    public MutationMUTATION_NAME(double probability/*COMPLETE?*/) {
        super(probability);
        //COMPLETE
    }

    @Override
    public void run(I ind) {
        //TODO
        for(int i = 0; i < ind.getNumGenes(); i++){
            if(GeneticAlgorithm.random.nextDouble() < probability){
                ind.setGene(i, ind.getGene(i)+0.1);
            }
        }
    }
    
    @Override
    public String toString(){
        return "Uniform distribution mutation (" + probability /* + COMPLETE?*/;
    }
}