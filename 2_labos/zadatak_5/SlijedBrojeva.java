package zadatak_5;

import java.util.ArrayList;
import java.util.List;

public class SlijedBrojeva {

	private List<Integer> kolekcija = new ArrayList<>();
	private Izvor izvor;
	private List<Promatrac> promatraci = new ArrayList<>();
	
	public void dodajPromatraca(Promatrac promatrac){
		promatraci.add(promatrac);
	}
	
	public void izbaciPromatraca(Promatrac promatrac){
		promatraci.remove(promatrac);
	}
	
	public SlijedBrojeva(Izvor izvor, List<Promatrac> promatraci){
		this.izvor = izvor;
		if(promatraci != null){
			this.promatraci.addAll(promatraci);
		}
	}
	
	public void kreni() throws InterruptedException{
		while(true){
			int sljedeciBroj = izvor.sljedeciBroj();
			if(sljedeciBroj < 0){
				break;
			}
			kolekcija.add(sljedeciBroj);
			update();
			
			Thread.sleep(1000);
		}
		System.out.println("Izvor je iscrpljen.");
	}

	private void update() {
		for(Promatrac p : promatraci){
			p.update(kolekcija);
		}
		//testni ispis kolekcije
		//System.out.println("testni ispis kolekcije: " + kolekcija);
	}
	
	
}
