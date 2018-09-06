package zadatak_5;

import java.util.List;

public class ProsjekElemenata implements Promatrac {

	@Override
	public void update(List<Integer> kolekcija) {
		
		int suma = 0;
		
		for(int i : kolekcija){
			suma+=i;
		}
		
		System.out.println("Prosjek elemenata u kolekciji je " + (double)suma/kolekcija.size());


	}

}
