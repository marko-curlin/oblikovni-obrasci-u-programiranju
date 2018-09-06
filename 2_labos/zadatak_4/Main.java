package zadatak_4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		SequenceGenerator<Integer> generator = new SequentialGenerator(0,10,2);
		Percentile<Integer> percentile = new NearestRankPercentile<>();
		
		DistributionTester<Integer> tester = new DistributionTester<>(generator, 
				percentile);
		
		tester.run();

		Percentile<Integer> percentile1 = new LinearInterpolationPercentile();
		List<Integer> testList = new ArrayList<>();
		testList.add(1);
		testList.add(10);
		testList.add(50);
		Collections.sort(testList);
		
		int testResult = percentile1.givePercentile(testList, 80);
		
		System.out.println("Test result(50)= " + testResult);
		
	}

}
