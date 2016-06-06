package pursuitDomain;

import ga.RealVectorIndividual;


public class PredatorIndividual extends RealVectorIndividual<PursuitDomainProblem, PredatorIndividual> {

    private Environment environment;
    public PredatorIndividual(PursuitDomainProblem problem, int size , Environment environment /*COMPLETE?*/) {
        super(problem, size);   
        this.environment = environment;
        //COMPLETE?
    }

    public PredatorIndividual(PredatorIndividual original) {
        super(original);
        //COMPLETE
    }

    @Override
    public double computeFitness() {
        //setPredatorsWeights();
        int distancia = environment.computePredatorsPreyDistanceSum();
        
        return distancia;
    }

    public double[] getGenome(){
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfitness: ");
        sb.append(fitness);
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
        if(this.computeFitness() > i.computeFitness())
            return 1;
        if(this.computeFitness() < i.computeFitness())
            return -1;
        return 0;
    }

    @Override
    public PredatorIndividual clone() {
        return new PredatorIndividual(this);
    }
}
