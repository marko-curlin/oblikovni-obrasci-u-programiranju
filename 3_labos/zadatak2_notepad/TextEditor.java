package notepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Iterator;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class TextEditor extends JComponent implements CursorObserver, TextObserver {

	protected TextEditorModel model;
	private InputMap inputMap = getInputMap();
	private ActionMap actionMap = getActionMap();
	private final Integer SIZE = 18;
	private final int spacing = 5;
	private static final String BACKSPACE = "backspace";
	private static final String DEL = "del";
	private static final String DOWN = "down";
	private static final String UP = "up";
	private static final String RIGHT = "right";
	private static final String LEFT = "left";
	private static final String MARK_LEFT = "markLeft";
	private static final String MARK_RIGHT = "markRight";
	private static final String MARK_DOWN = "markDown";
	private static final String MARK_UP = "markUp";
	private static final String SHIFT = "shift";
	private static final String ENTER = "enter";
	private static final String CTRL_C = "ctrl+c";
	private static final String CTRL_X = "ctrl+x";
	private static final String CTRL_V = "ctrl+v";
	private static final String CTRL_SHIFT_V = "ctrl+shift+v";
	private static final String CTRL_Z = "ctrl+z";
	private static final String CTRL_Y = "ctrl+y";
	
	public TextEditor(TextEditorModel model) {
		this.model = model;
		model.addCursorObserver(this);
		model.addTextObserver(this);
		inputMap.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
		inputMap.put(KeyStroke.getKeyStroke("UP"), UP);
		inputMap.put(KeyStroke.getKeyStroke("DOWN"), DOWN);
		inputMap.put(KeyStroke.getKeyStroke("DELETE"), DEL);
		inputMap.put(KeyStroke.getKeyStroke("BACK_SPACE"), BACKSPACE);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.SHIFT_DOWN_MASK), MARK_LEFT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.SHIFT_DOWN_MASK), MARK_RIGHT);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.SHIFT_DOWN_MASK), MARK_UP);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.SHIFT_DOWN_MASK), MARK_DOWN);
		inputMap.put(KeyStroke.getKeyStroke("shift SHIFT"), SHIFT);
		inputMap.put(KeyStroke.getKeyStroke("ENTER"), ENTER);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), CTRL_C);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), CTRL_X);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK), CTRL_V);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK), CTRL_SHIFT_V);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), CTRL_Z);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK), CTRL_Y);
		

		actionMap.put(CTRL_Z, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(CTRL_Z);
				UndoManager.instance().undo();
			}
		});
		actionMap.put(CTRL_Y, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(CTRL_Y);
				UndoManager.instance().redo();
			}
		});
		actionMap.put(CTRL_C, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedText = model.getSelectedText();
				if(selectedText != null)
					model.getStack().push(selectedText);
			}
		});
		actionMap.put(CTRL_X, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedText = model.getSelectedText();
				if(selectedText != null){
					model.getStack().push(selectedText);
					model.deleteSelected();
				}
			}
		});
		actionMap.put(CTRL_V, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pastedText = model.getStack().peek();
				if(pastedText != null){
					model.insert(pastedText);
				}
			}
		});
		actionMap.put(CTRL_SHIFT_V, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pastedText = model.getStack().pop();
				if(pastedText != null)
					model.insert(pastedText);
			}
		});
		actionMap.put(SHIFT, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setSelectionRange(null);
				System.out.println("range = null");
			}
		});
		actionMap.put("left", new MoveAction(() -> model.moveCursorLeft()));
		actionMap.put(RIGHT, new MoveAction(() -> model.moveCursorRight()));
		actionMap.put(UP, new MoveAction(() -> model.moveCursorUp()));
		actionMap.put(DOWN, new MoveAction(() -> model.moveCursorDown()));
		actionMap.put(DEL, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.deleteAfter();
			}
		});
		actionMap.put(BACKSPACE, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.deleteBefore();
			}
		});
		actionMap.put(MARK_LEFT, new MarkAction(() -> model.moveCursorLeft()));
		actionMap.put(MARK_RIGHT, new MarkAction(() -> model.moveCursorRight()));
		actionMap.put(MARK_UP, new MarkAction(() -> model.moveCursorUp()));
		actionMap.put(MARK_DOWN, new MarkAction(() -> model.moveCursorDown()));
		actionMap.put(ENTER, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("pozvan ENTER");
				model.insert('\n');
			}
		});
		
		
		for(char ch = 'A'; ch <= 'Z'; ch++) {
		    // upper case
		    final char c = ch;
		    KeyStroke capital = KeyStroke.getKeyStroke("typed " + c);
		    inputMap.put(capital, Character.toString(c));
		    actionMap.put(Character.toString(c), new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.insert(Character.toUpperCase(c));
				}
			});

		    // lower case
		    KeyStroke little = KeyStroke.getKeyStroke("typed " + Character.toLowerCase(c));
		    inputMap.put(little, Character.toString(Character.toLowerCase(c)));
		    actionMap.put(Character.toString(Character.toLowerCase(c)), new AbstractAction() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					model.insert(Character.toLowerCase(c));
				}
			});
		}
		
		int[][] characters = new int[][] {
            {'?', '%', '~', '|'},
            {' ', ',', '-', '.', '/'},
            {';', '=', '[', '\\', ']'}, 
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {'*', '+', ',', '-', '.', '/', '&', '*', '\"', '<', '>', '{', '}', '`', '\''},
            {'@', ':', '^', '$', '!', '(', '#', '+', ')', '_'},
            // if you're so inclined: add even more rows to the bottom
            {'¡', '€', '\u02ff'},
            {'š','đ','č','ć','ž','Š','Đ','Č','Ć','Ž'}
		};

		for(int[] range : characters) 
			for(int i = 0; i < range.length; i++) {
			    char charForKey = (char)range[i];
			    KeyStroke keyboardKey = KeyStroke.getKeyStroke(charForKey);
			    inputMap.put(keyboardKey, charForKey);
			    actionMap.put(charForKey, new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						model.insert(charForKey);
					}
				});
			}
		
	}
	
	private class MarkAction extends AbstractAction{
		private Runnable cmd;
		public MarkAction(Runnable cmd){
			this.cmd = cmd;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			LocationRange selected = model.getSelectionRange();
			if(selected == null){
				Location start = new Location(model.getCursorLocation());
				cmd.run();
				Location finish = model.getCursorLocation();
				model.setSelectionRange(new LocationRange(start, finish));
			} else {
				cmd.run();
				Location finish = model.getCursorLocation();
				model.setSelectionRange(new LocationRange(selected.getStart(), finish));
			}
		}
	}
	
	private class MoveAction extends AbstractAction{
		private Runnable cmd;
		public MoveAction(Runnable cmd){
			this.cmd = cmd;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			model.setSelectionRange(null);
			cmd.run();
			// has to be called for edge cases where the 
			// curosor is at the end or start of the file
			repaint();
			
		}
	}

	public void paint(Graphics g){
		int x = 0, y = SIZE;
		g.setFont(Font.decode(Font.MONOSPACED + " " + SIZE));
		int fontHeight = g.getFontMetrics().getHeight();
		int fontWidth = g.getFontMetrics().charWidth(' ');
		LocationRange selected = model.getSelectionRange();
		if(selected == null){
			for(Iterator<String> it = model.allLines(); it.hasNext(); y += SIZE + spacing){
				String line = it.next();
				g.drawString(line, x, y);
			}
		} else {
			selected = selected.getOrderedRange();
			int curRow = 0;
			Location start = selected.getStart();
			Location finish = selected.getFinish();
			int startRow = start.getRow();
			int lastRow = finish.getRow();
			boolean markingBegan = false;
	 		for(Iterator<String> it = model.allLines(); it.hasNext(); curRow++, y += SIZE + spacing){
				String line = it.next();
				if(curRow == startRow){
					g.drawString(line.substring(0, start.getColumn()), x, y);
					if(curRow == lastRow){
						if(line.substring(start.getColumn(), finish.getColumn()).length() != 0){
							AttributedString as = new AttributedString(line.substring(start.getColumn(), finish.getColumn()));
							as.addAttribute(TextAttribute.BACKGROUND, Color.RED);
							as.addAttribute(TextAttribute.FONT, Font.decode(Font.MONOSPACED + " " + SIZE));
							
							g.drawString(as.getIterator(), start.getColumn() * fontWidth, y);
						}
						g.drawString(line.substring(finish.getColumn()), finish.getColumn() * fontWidth, y);
					} else {
						if(line.substring(start.getColumn()).length() != 0){
							AttributedString as = new AttributedString(line.substring(start.getColumn()));
							as.addAttribute(TextAttribute.BACKGROUND, Color.RED);
							as.addAttribute(TextAttribute.FONT, Font.decode(Font.MONOSPACED + " " + SIZE));
							
							g.drawString(as.getIterator(), start.getColumn() * fontWidth, y);
						}
						markingBegan = true;
					}
					continue;
				} else if(curRow == lastRow){
					if(finish.getColumn() > 0){
						AttributedString as = new AttributedString(line.substring(0, finish.getColumn()));
						as.addAttribute(TextAttribute.BACKGROUND, Color.RED);
						as.addAttribute(TextAttribute.FONT, Font.decode(Font.MONOSPACED + " " + SIZE));
						
						g.drawString(as.getIterator(), x, y);
					}
					g.drawString(line.substring(finish.getColumn()), finish.getColumn() * fontWidth, y);
					markingBegan = false;
				}
				else if(markingBegan && line.length() != 0){
					AttributedString as = new AttributedString(line);
					as.addAttribute(TextAttribute.BACKGROUND, Color.RED);
					as.addAttribute(TextAttribute.FONT, Font.decode(Font.MONOSPACED + " " + SIZE));
					
					g.drawString(as.getIterator(), x, y);
				} else {
					g.drawString(line, x, y);
				}
				
			}
		}	
		Location loc = model.getCursorLocation();
		int row = loc.getRow();
		int column = loc.getColumn();
		int DELTA = 2;	//hardcoded value, found through trial an error
		if(column != 0){
			g.drawLine(column * fontWidth - 1, row*(fontHeight - DELTA) + 2, column*fontWidth - 1, (row+1)*(fontHeight - DELTA));
		} else {
			g.drawLine(column * fontWidth, row*(fontHeight - DELTA) + 2, column*fontWidth, (row+1)*(fontHeight - DELTA));
		}
	}

	@Override
	public void updateCursorLocation(Location loc) {
		repaint();
	}

	@Override
	public void updateText() {
		repaint();
	}
	
	public static void main(String[] args) {
	    
		TextEditorModel model = new TextEditorModel("iiiii1\neeeee2\nAAAAA3\n\nBBBBB3\nasdfg3"
				+ "\nEEEEE3\nIIIII3\nOOOOO3\nUUUUU3"
				+ "\nCCCCC3\nDDDDD3\nRRRRR3\nTTTTT3");
		
		TextEditor editor = new TextEditor(model);
		
		JFrame frame = new JFrame("Notepad");
		frame.setSize(960, 960);
		frame.add(editor, BorderLayout.CENTER);
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
