package zadatak_5;

import java.util.Collections;
import java.util.List;

public class MedijanElemenata implements Promatrac {

	@Override
	public void update(List<Integer> kolekcija) {
		Collections.sort(kolekcija);
		
		int size = kolekcija.size();
		
		System.out.print("Medijan elemenata kolecije je ");
		if(size % 2 == 1){
			System.out.println(kolekcija.get(size/2));
		} else {
			System.out.println((kolekcija.get(size/2 - 1) + kolekcija.get(size/2)) / 2.);
		}

	}

}
