package zadatak_5;

import java.util.List;

public class SumaElemenata implements Promatrac {

	@Override
	public void update(List<Integer> kolekcija) {
		
		int suma = 0;
		
		for(int i : kolekcija){
			suma+=i;
		}
		
		System.out.println("Suma elemenata u kolekciji je " + suma);

	}

}
