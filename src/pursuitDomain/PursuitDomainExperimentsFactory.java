package pursuitDomain;

import experiments.*;
import ga.GAListener;
import ga.GeneticAlgorithm;
import ga.geneticOperators.*;
import ga.selectionMethods.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import statistics.StatisticBestAverage;
import statistics.StatisticBestInRun;

public class PursuitDomainExperimentsFactory extends ExperimentsFactory {

    private int populationSize;
    private int maxGenerations;
    private double delta;
    private SelectionMethod<PredatorIndividual, PursuitDomainProblem> selection;
    private Recombination<PredatorIndividual> recombination;
    private Mutation<PredatorIndividual> mutation;
    private PursuitDomainProblem problem;
    private Experiment<PursuitDomainExperimentsFactory, PursuitDomainProblem> experiment;

    public PursuitDomainExperimentsFactory(File configFile) throws IOException {
        super(configFile);
    }

    @Override
    public Experiment buildExperiment() throws IOException {
        numRuns = Integer.parseInt(getParameterValue("Runs"));
        populationSize = Integer.parseInt(getParameterValue("Population size"));
        maxGenerations = Integer.parseInt(getParameterValue("Max generations"));

        //SELECTION
        if (getParameterValue("Selection").equals("tournament")) {
            int tournamentSize = Integer.parseInt(getParameterValue("Tournament size"));
            selection = new Tournament<>(populationSize, tournamentSize);
        }

        //RECOMBINATION
        double recombinationProbability = Double.parseDouble(getParameterValue("Recombination probability"));
        switch(getParameterValue("Recombination")){
            case "one_cut":
                recombination = new RecombinationOneCut<>(recombinationProbability);
                break;
            case "two_cuts":
                recombination = new RecombinationTwoCuts<>(recombinationProbability);
                break;
            case "uniform":
                recombination = new RecombinationUniform<>(recombinationProbability);
        }

        //MUTATION
        double mutationProbability = Double.parseDouble(getParameterValue("Mutation probability"));
        delta = Double.parseDouble(getParameterValue("Delta"));
        if (getParameterValue("Mutation").equals("uniform_distribution")) {
            mutation = new MutationDouble<>(mutationProbability, delta);
        }

        //PROBLEM 
        problem = PursuitDomainProblem.buildProblemFromFile(new File(getParameterValue("Problem file")));

        String textualRepresentation = buildTextualExperiment();

        experiment = new Experiment<>(this, numRuns, problem, textualRepresentation);

        statistics = new ArrayList<>();
        for (String statisticName : statisticsNames) {
            ExperimentListener statistic = buildStatistic(statisticName);
            statistics.add(statistic);
            experiment.addExperimentListener(statistic);
        }

        return experiment;
    }

    @Override
    public GeneticAlgorithm generateGAInstance(int seed) {
        GeneticAlgorithm<PredatorIndividual, PursuitDomainProblem> ga = 
                new GeneticAlgorithm<>(
                    populationSize,
                    maxGenerations,
                    selection,
                    recombination,
                    mutation,
                    new Random(seed));

        for (ExperimentListener statistic : statistics) {
            ga.addGAListener((GAListener) statistic);
        }

        return ga;
    }

    private ExperimentListener buildStatistic(String statisticName) {
        switch(statisticName){
            case "BestIndividual":
                return new StatisticBestInRun();
            case "BestAverage":
                return new StatisticBestAverage(numRuns);
        }        
        return null;
    }

    private String buildTextualExperiment() {
        StringBuilder sb = new StringBuilder();
        sb.append(filename+"\t\t");
        sb.append("CONTROLLER:" + problem.getController() + "\t");
        sb.append("POP SIZE:" + populationSize + "\t");
        sb.append("MAX GENS:" + maxGenerations + "\t");
        sb.append("SELECTION:" + selection + "\t");
        sb.append("RECOMBINATION:" + recombination + "\t");
        sb.append("MUTATION:" + mutation + "\t");
        return sb.toString();
    }
}
