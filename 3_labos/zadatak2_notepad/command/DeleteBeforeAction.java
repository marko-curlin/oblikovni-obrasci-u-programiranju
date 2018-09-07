package notepad.command;

import java.util.ArrayList;

import notepad.Location;
import notepad.TextEditorModel;

public class DeleteBeforeAction implements EditAction {

	private TextEditorModel model;
	private ArrayList<String> oldLines;
	private Location oldLocation;
	
	public DeleteBeforeAction(TextEditorModel model) {
		this.model = model;
	}

	@Override
	public void executeDo() {
		Location cursorLocation = model.getCursorLocation();
		ArrayList<String> lines = model.getLines();
		oldLocation = new Location(cursorLocation);
		oldLines = new ArrayList<String>(lines);
		if(model.getSelectionRange() != null){
			model.deleteSelected();
			return;
		}
		int curRow = cursorLocation .getRow();
		int curColumn = cursorLocation.getColumn();
		model.moveCursorLeft();
		if(curColumn == 0){
			if(curRow == 0) {
				return;
			}
			String curLine = lines.remove(curRow);
			lines.set(curRow - 1, lines.get(curRow - 1).concat(curLine));
		} else {
			String curLine = lines.get(curRow);
			StringBuilder sb = new StringBuilder(curLine);
			sb.deleteCharAt(curColumn - 1);
			String result = sb.toString();
			lines.set(curRow, result);
		}
		model.updateText();
//		model.updateCursor();
	}

	@Override
	public void executeUndo() {
		model.setLines(oldLines);
		model.setCursorLocation(oldLocation);
	}

}
