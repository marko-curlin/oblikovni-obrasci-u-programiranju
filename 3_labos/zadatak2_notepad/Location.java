package notepad;

public class Location implements Comparable<Location>{

	private int column, row;
	
	public Location(int column, int row){
		this.column = column;
		this.row = row;
	}

	public Location(){
		this(0, 0);
	}
	
	public Location(Location location){
		this.column = location.column;
		this.row = location.row;
	}
	
	public void move(int x, int y){
		row += x;
		column += y;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public String toString() {
		return "Location [column=" + column + ", row=" + row + "]";
	}

	@Override
	public int compareTo(Location other) {
		if(this.row < other.row){
			return 1;
		} else if(this.row == other.row){
			if(this.column < other.column){
				return 1;
			} else if(this.column == other.column){
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
}
