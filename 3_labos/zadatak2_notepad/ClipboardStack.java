package notepad;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClipboardStack {
	
	private Stack<String> texts = new Stack<>();
	private List<ClipboardObserver> observers = new ArrayList<>();
	
	public void addClipboardObserver(ClipboardObserver observer){
		observers.add(observer);
	}
	
	public void removeClipboardObserver(ClipboardObserver observer){
		observers.remove(observer);
	}
	
	public void push(String text){
		texts.push(text);
	}
	
	public String pop(){
		return texts.pop();
	}
	
	public String peek(){
		return texts.peek();
	}
	
	public boolean isEmpty(){
		return texts.isEmpty();
	}
	
}
