package zadatak_6;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Cell implements Observer, Comparable<Cell>{

	private String name;
	private Sheet sheet;
	private String exp;
	private int value;
	private Set<Observer> observers = new TreeSet<>();
	
	public Cell(String exp, List<Observer> observers){
		this(exp);
		if(observers != null){
			this.observers.addAll(observers);
		}
	}

	public Cell(String exp) {
		this.exp = exp;
	}
	
	public Cell(){
		this("0");
	}
	
	public Cell(Sheet sheet, char x, char y) {
		this();
		x += 'A';
		y += '1';
		this.name = x + "" + y;
		this.sheet = sheet;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getExp(){
		return exp;
	}
	
	public void setValue(int value) {
		if(this.value == value)
			return;
		this.value = value;
		notifyObservers();
	}
	
	public int getValue() {
		return value;
	}
	
	public Set<Observer> getObservers() {
		return observers;
	}
	
	public void notifyObservers() {
		for(Observer i : observers){
		    i.update(this);
		}
	}

	public void setObserver(List<Observer> observers){
		this.observers.addAll(observers);
	}
	
	public void setObserver(Observer observer){
		this.observers.add(observer);
	}
	
	public void removeObserver(Observer observer){
		this.observers.remove(observer);
	}
	
	public void removeObserver(List<Observer> observers){
		this.observers.removeAll(observers);
	}
	
	public void clearObservers(){
		this.observers.clear();
	}

	@Override
	public String toString() {
		return name;
	}

	public void update(Cell cell){
		sheet.evaluate(this);
	}

	@Override
	public int compareTo(Cell o) {
		return name.compareTo(o.name);
	}
	
//	@Override
//	public String toString() {
//		return name;
//	}
	
}
