package notepad.command;

import java.util.ArrayList;

import notepad.Location;
import notepad.TextEditorModel;

public class DeleteAfterAction implements EditAction {

	private TextEditorModel model;
	private ArrayList<String> oldLines;
	private Location oldLocation;
	
	public DeleteAfterAction(TextEditorModel model) {
		this.model = model;
	}
	
	@Override
	public void executeDo() {
		Location cursorLocation = model.getCursorLocation();
		ArrayList<String> lines = model.getLines();
		oldLocation = new Location(cursorLocation);
		oldLines = new ArrayList<String>(lines);
		if(model.getSelectionRange()!= null){
			model.deleteSelected();
			return;
		}
		int curRow = cursorLocation.getRow();
		int curColumn = cursorLocation.getColumn();
		if(curRow == lines.size() - 1 && curColumn == lines.get(lines.size() - 1).length()){
			return;
		}
		String curLine = lines.get(curRow);
		StringBuilder sb = new StringBuilder(curLine);
		if(curLine.length() == curColumn){
			String nextLine = lines.get(curRow + 1);
			sb.append(nextLine);
			lines.remove(curRow + 1);
		} else {
			sb.deleteCharAt(curColumn);
		}
		String result = sb.toString();
		lines.set(curRow, result);
		model.updateText();
	}

	@Override
	public void executeUndo() {
		model.setLines(oldLines);
		model.setCursorLocation(oldLocation);
	}

}
