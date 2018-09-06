package zadatak_4;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSequence implements SequenceGenerator<Integer> {

	int n;
	
	public FibonacciSequence(int n) {
		this.n = n;
	}
	
	@Override
	public List<Integer> generate() {
		
		List<Integer> returnList = new ArrayList<>();
		
		for(Integer i = 1; i <= n; i++){
			returnList.add(getNFibonnaci(i));
		}
		
		return returnList;
	}

	private static int getNFibonnaci(int i){
		
		if(i == 0){
			return 0;
		} else if(i == 1){
			return 1;
		} else {
			return getNFibonnaci(i-1) + getNFibonnaci(i-2);
		}
		
	}
}
