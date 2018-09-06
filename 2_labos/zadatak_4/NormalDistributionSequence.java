package zadatak_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalDistributionSequence implements SequenceGenerator<Double> {

	double mean, deviation;
	int n;
	
	public NormalDistributionSequence(double mean, double deviation, int n) {
		this.mean = mean;
		this.deviation = deviation;
		this.n = n;
	}
	
	@Override
	public List<Double> generate() {
		
		Random rand = new Random();
		
		List<Double> returnList = new ArrayList<>();
		
		for(int i = 0; i < n; i++){
			returnList.add(rand.nextGaussian()*deviation-mean);
		}
		
		
		return returnList;
	}

}
