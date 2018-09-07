package notepad;

import java.util.Stack;

import notepad.command.EditAction;

public class UndoManager {

	private Stack<EditAction> undoStack = new Stack<>();
	private Stack<EditAction> redoStack = new Stack<>();

	private static UndoManager instance = null;
	
	private UndoManager(){
		super();
	}
	
	public static UndoManager instance(){
		if(instance == null){
			instance = new UndoManager();
		}
		return instance;
	}
	
	public void undo(){
		if(!undoStack.isEmpty()){
			EditAction action = undoStack.pop();
			action.executeUndo();
			redoStack.push(action);
		}
	}
	
	public void redo(){
		if(!redoStack.isEmpty()){
			EditAction action = redoStack.pop();
			action.executeDo();
			undoStack.push(action);
		}
	}
	
	public void push(EditAction c){
		redoStack.removeAllElements();
		undoStack.push(c);
	}

}
