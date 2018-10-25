import java.util.Random;

public class LevyFlight {
    private final Random rand;
    private final double beta;
    private double sigmaU;
    private double sigmaV;

    public LevyFlight(){
        rand = new Random();
        beta = 3/2;
    }

    public LevyFlight init(){
        calculateSigmaU();
        sigmaV = 1.0;
        return this;
    }

    public double doubleValue(){
        double u = getGaussianDistribution(0.0, sigmaU);
        double v = getGaussianDistribution(0.0, sigmaV);
        return u / Math.pow(Math.abs(v),1/beta);
    }

    private void calculateSigmaU(){
        double numerator = new Gamma().calculate((1 + beta) * Math.sin(Math.PI*beta/2));
        double denominator = new Gamma().calculate(((1 + beta)/2) * beta * Math.pow(2,(beta-1)/2));
        sigmaU = Math.pow(numerator/denominator, 1/beta);
    }

    private double getGaussianDistribution(double mean, double standardDeviation){
        return rand.nextGaussian() * standardDeviation + mean;
    }


}
