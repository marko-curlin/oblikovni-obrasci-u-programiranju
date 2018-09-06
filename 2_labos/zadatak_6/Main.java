package zadatak_6;

public class Main {

	public static void main(String[] args) {

		Sheet s = new Sheet(5,5);
		
		s.set("A1", "A3");
		s.set("A2", "5");
		s.set("A3", "A2");
		System.out.println(s);
		
		s.set("A1","4");
		s.set("A4","A1+A3");
		System.out.println(s);
		
		s.set("A1", "A3");
		System.out.println(s);
	}

}
