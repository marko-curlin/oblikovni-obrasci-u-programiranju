package zadatak_4;

import java.util.List;

public class LinearInterpolationPercentile implements Percentile<Integer> {

	
	public Integer givePercentile(List<Integer> sequence, int p) {
		
		int N = sequence.size();
		
		double positionPercentile_i = 0, positionPercentile_i_1 = 0;
		int j = 1;
		for(; j <= N; j++){
			positionPercentile_i_1 = 100*(j-0.5)/N;
			
			if(p < positionPercentile_i_1){
				break;
			}
			
			positionPercentile_i = positionPercentile_i_1;
		}
		
		if (positionPercentile_i == 0){
			return sequence.get(0);
		}
		if(j > N){
			return sequence.get(N-1);
		}
		
		Integer returnValue = (int) Math.round(sequence.get(j-1 - 1) + N*(p-positionPercentile_i)*
				(sequence.get(j-1) - sequence.get(j-1 - 1))/100);
		
		return returnValue;
	}
	
}