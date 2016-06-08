package pursuitDomain;

import ga.RealVectorIndividual;
import gui.MainFrame;
import gui.PanelParameters;


public class PredatorIndividual extends RealVectorIndividual<PursuitDomainProblem, PredatorIndividual> {

    
    public PredatorIndividual(PursuitDomainProblem problem, int size /*COMPLETE?*/) {
        super(problem, size);  
        
       
        //COMPLETE?
    }

    public PredatorIndividual(PredatorIndividual original) {
        super(original);
        //COMPLETE
    }

    @Override
    public double computeFitness() {
        Environment env = problem.getEnvironment();
        env.setPredatorsWeights(genome);
        fitness = 0;
        for (int i = 0; i < problem.getNumEvironmentSimulations(); i++){
            env.initializeAgentsPositions(i);
            env.simulate();
            fitness = env.computePredatorsPreyDistanceSum();
        }    
       return fitness;
    }

    public double[] getGenome(){
        return genome.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
        sb.append("\nNumero de Simulacoes: ");
        sb.append(problem.getNumEvironmentSimulations());
        
        //COMPLETE
        return sb.toString();
    }

    /**
     *
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(PredatorIndividual i) {
        //TODO
        if(this.fitness > i.fitness)
            return -1;
        if(this.fitness < i.fitness)
            return 1;
        return 0;
    }

    @Override
    public PredatorIndividual clone() {
        return new PredatorIndividual(this);
    }
}
