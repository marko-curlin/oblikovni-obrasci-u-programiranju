package notepad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import notepad.command.DeleteAfterAction;
import notepad.command.DeleteBeforeAction;
import notepad.command.DeleteRangeAction;
import notepad.command.EditAction;
import notepad.command.InsertAction;

public class TextEditorModel {

	private ArrayList<String> lines = new ArrayList<String>();
	private LocationRange selectionRange;
	private Location cursorLocation = new Location();
	private ClipboardStack stack = new ClipboardStack();
	private List<CursorObserver> cursorObservers = new ArrayList<>();
	private List<TextObserver> textObservers = new ArrayList<>();
	private UndoManager undoManager = UndoManager.instance();
	

	public ArrayList<String> getLines() {
		return lines;
	}

	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
		updateText();
	}
	
	public TextEditorModel(String input){
		for(String line : input.split("\n")){
			lines.add(line);
		}
	}
	
	public ClipboardStack getStack() {
		return stack;
	}

	public Location getCursorLocation() {
		return cursorLocation;
	}
	
	public void setCursorLocation(Location cursorLocation) {
		this.cursorLocation = cursorLocation;
		updateCursor();
	}
	
	public Iterator<String> allLines(){
		return lines.iterator();
	}
	
	public Iterator<String> linesRange(int index1, int index2){
		return lines.subList(index1 - 1, index2 - 1).iterator();
	}
	
	public void addCursorObserver(CursorObserver observer){
		cursorObservers.add(observer);
	}
	
	public void deleteCursorObserver(CursorObserver observer){
		cursorObservers.remove(observer);
	}
	
	public void addTextObserver(TextObserver observer){
		textObservers.add(observer);
	}
	
	public void deleteTextObserver(TextObserver observer){
		textObservers.remove(observer);
	}
	
	public void updateCursor() {
		for(CursorObserver observer : cursorObservers){
			observer.updateCursorLocation(cursorLocation);
		}
		System.out.println(cursorLocation);
	}
	
	public void updateText() {
		for(TextObserver observer : textObservers){
			observer.updateText();
		}
	}
	
	public void moveCursorLeft(){
		int curColumn = cursorLocation.getColumn();
		if(curColumn > 0){
			cursorLocation.move(0, -1);
		} else {
			int upperRow = cursorLocation.getRow() - 1;
			if(upperRow == -1)
				return;
			cursorLocation.setColumn(lines.get(upperRow).length());
			cursorLocation.setRow(upperRow);
		}
		updateCursor();
	}

	public void moveCursorRight(){
		int curRow = cursorLocation.getRow();
		int curColumn = cursorLocation.getColumn();
		if(curColumn < lines.get(curRow).length()){
			cursorLocation.move(0, 1);
		} else {
			if(curRow == lines.size() - 1)
				return;
			cursorLocation = new Location(0, curRow + 1);
		}
		updateCursor();
	}
	
	public void moveCursorUp(){
		int curRow = cursorLocation.getRow();
		if(curRow > 0){
			cursorLocation.move(-1, 0);
			if(cursorLocation.getColumn() > lines.get(curRow - 1).length()){
				cursorLocation.setColumn(lines.get(curRow - 1).length());
			}
		} else {
			cursorLocation.setColumn(0);
		}
		updateCursor();
	}
	
	public void moveCursorDown(){
		int curRow = cursorLocation.getRow();
		if(curRow < lines.size() - 1){
			cursorLocation.move(1, 0);
			if(cursorLocation.getColumn() > lines.get(curRow + 1).length()){
				cursorLocation.setColumn(lines.get(curRow + 1).length());
			}
		} else {
			cursorLocation.setColumn(lines.get(curRow).length());
		}
		updateCursor();
	}

	public void deleteBefore(){
		EditAction action = new DeleteBeforeAction(this);
		action.executeDo();
		undoManager.push(action);
	}

	public void deleteAfter(){
		EditAction action = new DeleteAfterAction(this);
		action.executeDo();
		undoManager.push(action);
	}
	
	public void deleteSelected() {
		if(selectionRange != null){
			deleteRange(selectionRange);
			selectionRange = null;
		}
	}
	
	private void deleteRange(LocationRange r){
		EditAction action = new DeleteRangeAction(this, r);
		action.executeDo();
		undoManager.push(action);
	}
	
	public LocationRange getSelectionRange(){
		return selectionRange;
	}
	
	public void setSelectionRange(LocationRange range){
		selectionRange = range;
	}
	
	public void insert(char c){
		EditAction action = new InsertAction(this, c);
		action.executeDo();
		undoManager.push(action);
	}
	
	public void insert(String text){
		EditAction action = new InsertAction(this, text);
		action.executeDo();
		undoManager.push(action);
	}

	public String getSelectedText() {
		if(selectionRange == null){
			return null;
		}
		String result = "";
		Location start = selectionRange.getOrderedRange().getStart();
		Location finish = selectionRange.getOrderedRange().getFinish();
		int startRow = start.getRow();
		int finishRow = finish.getRow();
		for(int curRow = startRow; curRow <= finishRow; curRow++){
			if(curRow == startRow){
				String curLine = lines.get(curRow);
				if(curRow == finishRow){
					result = curLine.substring(start.getColumn(), finish.getColumn());
					return result;
				} else {
					result += curLine.substring(start.getColumn()) + "\n";
				}
			} else if(curRow != finishRow){
				result += lines.get(curRow) + "\n";
			} else if(curRow == finishRow){
				result += lines.get(finishRow).substring(0, finish.getColumn());
			}
		}
		return result;
	}


}
