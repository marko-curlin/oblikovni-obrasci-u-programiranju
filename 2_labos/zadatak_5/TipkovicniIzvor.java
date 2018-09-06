package zadatak_5;

import java.util.Scanner;

public class TipkovicniIzvor implements Izvor {

	private Scanner sc = new Scanner(System.in);
	@Override
	public int sljedeciBroj() {
		try{
			return sc.nextInt();
		}catch(Exception e){
			return -1;
		}
	}
	
	@Override
	public void close() {
		
		sc.close();
		
	}
	
	

}
