package zadatak_4;

import java.util.ArrayList;
import java.util.List;

public class NormalDistributionSequenceInt implements SequenceGenerator<Integer> {

	NormalDistributionSequence doubleGenerator;
	
	public NormalDistributionSequenceInt(double mean, double deviation, int n) {
		doubleGenerator = new NormalDistributionSequence(mean, deviation, n);
	}
	
	@Override
	public List<Integer> generate() {
		
		List<Double> doubleList = doubleGenerator.generate();
		List<Integer> returnList = new ArrayList<>();
		
		for(Double element : doubleList){
			returnList.add((int)Math.round(element));
		}
	
		return returnList;
	}

}
