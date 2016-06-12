package statistics;

import experiments.ExperimentEvent;
import ga.GAEvent;
import ga.GAListener;
import ga.GeneticAlgorithm;
import ga.Individual;
import ga.Problem;
import utils.Maths;

public class StatisticBestAverage<E extends Individual, P extends Problem<E>> implements GAListener  {
    
    private final double[] values, sumIterations;
    private int run;
    
    public StatisticBestAverage(int numRuns) {
        values = new double[numRuns];
        sumIterations = new double[numRuns];
    }

    @Override
    public void generationEnded(GAEvent e) {    
    }

    @Override
    public void runEnded(GAEvent e) {
        GeneticAlgorithm<E, P> ga = e.getSource();
        sumIterations[run] = ga.getBestInRun().getIterations();
        values[run++] = ga.getBestInRun().getFitness();
    }

    @Override
    public void experimentEnded(ExperimentEvent e) {

        double average = Maths.average(values);
        double sd = Maths.standardDeviation(values, average);
        double avg_it = Maths.average(sumIterations);
        
        utils.FileOperations.appendToTextFile("stats_results/statistic_average_fitness.xls", e.getSource() + "\tAVG FITNESS:" + average + "\tSD:" + sd + "\tAVG ITER.:" + avg_it + "\r\n");
    }    
}
