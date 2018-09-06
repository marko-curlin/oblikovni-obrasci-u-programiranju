package zadatak_4;

import java.util.List;

public class DistributionTester<T> {

	SequenceGenerator<T> generator;
	Percentile<T> percentile;
	
	public DistributionTester(SequenceGenerator<T> generator, Percentile<T> percentile){
		this.generator = generator;
		this.percentile = percentile;
	}

	public void run() {
		
		List<T> sequence = generator.generate();
		
		for(int i = 10; i < 100; i += 10){
			
			T printResult = percentile.givePercentile(sequence, i);
			
			System.out.println(i + "-ti percentil= " + printResult);
			
		}
		
		
	}
	
	
}
