package zadatak_6;

import java.util.ArrayList;
import java.util.List;

public class Sheet {

	private Cell[][] sheet;
	int x, y;
	
	public Sheet(int x, int y){
		sheet = new Cell[x][y];
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				sheet[i][j] = new Cell(this, (char)i, (char)j);
			}
		}
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		String returnString = new String();
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				returnString += "(" + i + ", " + j + ")=" + sheet[i][j].getExp() + ", " + sheet[i][j].getValue() + " " + sheet[i][j].getObservers() + "\n"; 
			}
		}
		return returnString;
	}
	
	
	public Cell call(String ref){

		int x = ref.charAt(0) - 'A';
		int y = ref.charAt(1) - '1';
		
		return sheet[x][y];	
	}
	
	public void set(String ref, String content){
		
		Cell changed = call(ref);
		changed.setExp(content);
		for(Observer cell : changed.getObservers()){
			((Cell)cell).removeObserver(changed);
		}
		evaluate(changed);
		return;
	}
	
	public List<Cell> getRefs(Cell cell){

		String exp = cell.getExp();
		
		String[] cellStrings = exp.split("\\W");
		
		List<Cell> returnList = new ArrayList<>();
		
		for(String ref : cellStrings){
			Cell referencedCell = call(ref);
			
			referencedCell.setObserver(cell);
			
			returnList.add(referencedCell);
		}
		
		return returnList;
	}
	
	public void evaluate(Cell cell){
		
		String exp = cell.getExp();
		
		if(exp.matches("\\d+")){
			cell.setValue(Integer.parseInt(exp));
			return;
		}
		
		List<Cell> refs = getRefs(cell);
		
		int result = 0;
		for(Cell i : refs){
			result += i.getValue();
		}
		
		cell.setValue(result);
		return;
	}
	
}
