package hr.fer.zemris.ooup.lab2.model;

public class Main {

	public static void main(String[] args) {
		
		Animal parrot = null, tiger = null;
		try {
			parrot = AnimalFactory.newInstance("Parrot", "Modrobradi");
		} catch (Exception e) {
			System.err.println("Ne mogu stvoriti papigu!");
			e.printStackTrace();
		}
		try {
			tiger = AnimalFactory.newInstance("Tiger", "Strasko");
		} catch (Exception e) {
			System.err.println("Ne mogu stvoriti tigra!");
			e.printStackTrace();
		}
		
		parrot.animalPrintGreeting();
		tiger.animalPrintGreeting();
		
		parrot.animalPrintMenu();
		tiger.animalPrintMenu();

	}

}
