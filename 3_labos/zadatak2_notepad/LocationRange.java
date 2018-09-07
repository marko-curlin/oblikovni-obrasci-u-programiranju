package notepad;

public class LocationRange {

	private Location start, finish;
	
	public LocationRange(Location start, Location finish){
		this.start = new Location(start);
		this.finish = new Location(finish);
	}
	
	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
	}

	public Location getFinish() {
		return finish;
	}

	public void setFinish(Location finish) {
		this.finish = finish;
	}
	
	public LocationRange getOrderedRange(){
		if(start.compareTo(finish) >= 0){
			return new LocationRange(start, finish);
		} else {
			return new LocationRange(finish, start);
		}
	}
	
//	public List<Integer> getAllRows(){
//		List<Integer> returnValue = new ArrayList<>();
//		int finishRow = finish.getRow();
//		for(int i = start.getRow(); i <= finishRow; i++){
//			returnValue.add(i);
//		}
//		return returnValue;
//	}

	@Override
	public String toString() {
		return "LocationRange [start=" + start + ", finish=" + finish + "]";
	}
	
}
