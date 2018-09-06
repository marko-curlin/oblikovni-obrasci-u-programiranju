package zadatak_4;

import java.util.ArrayList;
import java.util.List;

/*
 * Generates integers in the interval [start, finish), 
 * with the increment given in the constructor.
 */
public class SequentialGenerator implements SequenceGenerator<Integer> {

	Integer start, finish, increment;

	public SequentialGenerator(Integer start, Integer finish, Integer increment) {
		this.start = start;
		this.finish = finish;
		this.increment = increment;
	}

	@Override
	public List<Integer> generate() {

		List<Integer> returnList = new ArrayList<>();

		for (Integer i = start; i < finish; i += increment) {
			returnList.add(i);
		}

		return returnList;
	}

}
