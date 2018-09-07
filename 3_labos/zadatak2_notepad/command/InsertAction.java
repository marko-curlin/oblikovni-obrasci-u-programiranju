package notepad.command;

import java.util.ArrayList;

import notepad.Location;
import notepad.TextEditorModel;

public class InsertAction extends ReverseAction {

	private String input;
	
	public InsertAction(TextEditorModel model, char c) {
		super(model);
		this.input = String.valueOf(c);
	}
	
	public InsertAction(TextEditorModel model, String text) {
		super(model);
		this.input = text;
	}
	
	public void insert(char c){
		Location cursorLocation = model.getCursorLocation();
		ArrayList<String> lines = model.getLines();
		if(model.getSelectionRange() != null){
			model.deleteSelected();
			model.setSelectionRange(null);
		}
		int curRow = cursorLocation.getRow();
		int curColumn = cursorLocation.getColumn();
		String curLine = lines.get(curRow);
		if(c == '\n'){
			String newLine = curLine.substring(curColumn);
			lines.add(curRow + 1, newLine);
			curLine = curLine.substring(0, curColumn);
		} else {
			curLine = curLine.substring(0, curColumn) + c + curLine.substring(curColumn);
		}
		lines.set(curRow, curLine);
		model.updateText();
		model.moveCursorRight();
	}
	
	@Override
	public void executeDo() {
		savePreviousState();
		int length = input.length();
		for(int i = 0; i < length; i++){
			char c = input.charAt(i);
			insert(c);
		}
	}
	
}
