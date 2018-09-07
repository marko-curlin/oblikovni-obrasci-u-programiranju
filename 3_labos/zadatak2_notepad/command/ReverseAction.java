package notepad.command;

import java.util.ArrayList;

import notepad.Location;
import notepad.TextEditorModel;

public abstract class ReverseAction implements EditAction {

	protected TextEditorModel model;
	protected ArrayList<String> oldLines;
	protected Location oldLocation;
	
	public ReverseAction(TextEditorModel model) {
		this.model = model;
	}
	
	protected void savePreviousState(){
		Location cursorLocation = model.getCursorLocation();
		ArrayList<String> lines = model.getLines();
		oldLocation = new Location(cursorLocation);
		oldLines = new ArrayList<String>(lines);
	}

	@Override
	public void executeUndo() {
		model.setLines(oldLines);
		model.setCursorLocation(oldLocation);
	}

}
