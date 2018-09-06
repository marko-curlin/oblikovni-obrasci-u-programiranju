package zadatak_4;

import java.util.List;

public class NearestRankPercentile<T> implements Percentile<T> {

	@Override
	public T givePercentile(List<T> sequence, int p) {
		
		int n_p = (int)Math.ceil(p*sequence.size()/(double)100) - 1;
		return sequence.get(n_p);
	}

	
}
