package pursuitDomain;

import ga.RealVectorIndividual;

public class PredatorIndividual extends RealVectorIndividual<PursuitDomainProblem, PredatorIndividual> {
    
    private int iterations;
    
    public PredatorIndividual(PursuitDomainProblem problem, int size) {
        super(problem, size);
        this.iterations = 0;
    }

    public PredatorIndividual(PredatorIndividual original) {
        super(original);
        this.iterations = original.getIterations();
    }

    @Override
    public double computeFitness() {
        Environment env = problem.getEnvironment();
        env.setPredatorsWeights(genome);
        fitness = 0;
        iterations = 0;
        for (int i = 0; i < problem.getNumEvironmentSimulations(); i++){
            env.initializeAgentsPositions(i);
            iterations = env.simulate();
            fitness = (env.computePredatorsPreyDistanceSum() + iterations)/(env.getNumCatches() + 1);
        }
       return fitness;
    }

    public double[] getGenome(){
        return genome.clone();
    }

    @Override
    public int getIterations() {
        return iterations;
    }

    @Override
    public int getNumCatches() {
        return problem.getEnvironment().getNumCatches();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFitness: ");
        sb.append(fitness);
        sb.append("\nCatches: ");
        sb.append(problem.getEnvironment().getNumCatches());
        sb.append("\nNumber iterations (catch): ");
        sb.append(iterations);
        sb.append("\nNumber Simulations: ");
        sb.append(problem.getNumEvironmentSimulations());
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
