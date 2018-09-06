package zadatak_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZapisUTextDatoteku implements Promatrac {

	private Path izlazPut;
	
	public ZapisUTextDatoteku(Path izlazPut) {
		this.izlazPut = izlazPut;
		try {
			Files.write(izlazPut, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ZapisUTextDatoteku(String izlaz) {
		this(Paths.get(izlaz));
	}
	
	@Override
	public void update(List<Integer> kolekcija) {

		List<String> ispis = new ArrayList<>();
		
		for(Integer element : kolekcija){
			ispis.add(element.toString());
		}
		
		try {
			LocalDateTime ldt = LocalDateTime.now();
			String time = ldt.toString();
			Files.write(izlazPut, time.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
			Files.write(izlazPut, "\n".getBytes(), StandardOpenOption.APPEND);
			Files.write(izlazPut, ispis, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}

}
