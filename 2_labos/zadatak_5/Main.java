package zadatak_5;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Izvor izvor = new DatotecniIzvor("ulaz.txt");
		
		List<Promatrac> promatraci = new ArrayList<>();
		
		promatraci.add(new ProsjekElemenata());
		promatraci.add(new MedijanElemenata());
		promatraci.add(new SumaElemenata());
		promatraci.add(new ZapisUTextDatoteku("izlaz.txt"));
		
		SlijedBrojeva slijed = new SlijedBrojeva(izvor, promatraci);
		
		try {
			slijed.kreni();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		izvor.close();
	}

}
