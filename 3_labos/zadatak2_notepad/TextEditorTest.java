package notepad;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TextEditorTest {

	public static void main(String[] args) {
	    
		TextEditorModel model = new TextEditorModel("iiiii1\neeeee2\nAAAAA3\n\nBBBBB3\nasdfg3"
				+ "\nEEEEE3\nIIIII3\nOOOOO3\nUUUUU3"
				+ "\nCCCCC3\nDDDDD3\nRRRRR3\nTTTTT3");
		
		TextEditor editor = new TextEditor(model);
		
		JFrame frame = new JFrame("Notepad");
		frame.setSize(960, 960);
		frame.add(editor, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		file.add(open);
		file.add(save);
		file.add(exit);
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);

		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem cut = new JMenuItem("Cut");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem paste = new JMenuItem("Paste");
		JMenuItem pasteAndTake = new JMenuItem("Paste and Take");
		JMenuItem deleteSelection = new JMenuItem("Delete selection");
		JMenuItem clearDocument = new JMenuItem("Clear document");
		
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
