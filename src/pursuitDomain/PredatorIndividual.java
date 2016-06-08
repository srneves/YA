package pursuitDomain;

import ga.GeneticAlgorithm;
import ga.RealVectorIndividual;
import gui.MainFrame;


public class PredatorIndividual extends RealVectorIndividual<PursuitDomainProblem, PredatorIndividual> {

    private Environment environment;
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
        
        //environment.setPredatorsWeights(this.genome);
        //System.out.println(String.valueOf(genome));
        //setPredatorsWeights();
        //somatorio da distancia dos predadores
        //a dividir pelo numchatches +1
        //environment.setPredatorsWeights(this.genome);
        //int localFitness = 0;
        //compute fitness after they run: get the iterations, final distance and maybe average distance to prey;
        //localFitness += environment.computePredatorsPreyDistanceSum();
        //todo;
        //}
        //this.fitness = localFitness;
        return fitness;
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
