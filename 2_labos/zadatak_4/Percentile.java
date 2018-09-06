package zadatak_4;

import java.util.List;

public interface Percentile<T> {
	
	public T givePercentile(List<T> sequence, int p);
}
