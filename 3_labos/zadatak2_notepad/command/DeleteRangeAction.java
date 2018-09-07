package notepad.command;

import java.util.ArrayList;

import notepad.Location;
import notepad.LocationRange;
import notepad.TextEditorModel;

public class DeleteRangeAction extends ReverseAction {

	LocationRange r;
	
	public DeleteRangeAction(TextEditorModel model, LocationRange r) {
		super(model);
		this.r = r;
	}

	@Override
	public void executeDo() {
		savePreviousState();
		ArrayList<String> lines = model.getLines();
		Location start = r.getOrderedRange().getStart();
		Location finish = r.getOrderedRange().getFinish();
		int startRow = start.getRow();
		int finishRow = finish.getRow();
		for(int curRow = finishRow; curRow >= startRow; curRow--){
			if(curRow == startRow && curRow == finishRow){
				String curLine = lines.get(curRow);
				String result = curLine.substring(0, start.getColumn()) + curLine.substring(finish.getColumn());
				lines.set(curRow, result);
			} else if(curRow != finishRow && curRow != startRow){
				lines.remove(curRow);
			} else if(curRow == finishRow){
				String result = lines.get(startRow).substring(0, start.getColumn()) + lines.get(finishRow).substring(finish.getColumn());
				lines.set(startRow, result);
				lines.remove(finishRow);
			}
		}
		model.setCursorLocation(start);
		model.updateText();
	}

}
