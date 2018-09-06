package zadatak_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatotecniIzvor implements Izvor {

	Scanner sc;
	
	public DatotecniIzvor(String putanja) {
		try {
			sc = new Scanner(new File(putanja));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		sc.close();
	}

	@Override
	public int sljedeciBroj() {

		return sc.hasNextInt() ? sc.nextInt() : -1;
	}

}
