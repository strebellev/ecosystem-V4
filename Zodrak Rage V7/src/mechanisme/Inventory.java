package mechanisme;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Inventory {
	PApplet parent;
	ArrayList<PVector> inventory;
	
	//des int pour chaque objets et pour objets total
	
	
	int mode;
	
	boolean[] keys=new boolean[4];
	
	boolean okay = true;
	
	public Inventory(PApplet p){
		parent = p;
		inventory = new ArrayList<PVector>();
		p.registerMethod("keyEvent", this);
	}
	
	public void display(PApplet p, PVector pos){

		switch(mode){
			case 0:
				break;
			case 1:
				//display inventory
				p.fill(255,0,0);
				p.rectMode(PConstants.CENTER);
				p.rect(pos.x+20, pos.y+20, 200, 200);
				p.text("test", pos.x, pos.y);
				p.noFill();
				break;
		}
	}
	
	public void pressI(PApplet p){
		if(keys[0] == true ){
			mode = (mode+1)%2;
		}
	}
	
	public void keyEvent(KeyEvent e){
		int keyCode = e.getKeyCode();
		char keyChar = e.getKey();
		boolean shiftDown = e.isShiftDown();
		boolean ctrlDown = e.isControlDown();
		int keyID = e.getAction();
		if(keyID == KeyEvent.PRESS){
			keyPressedProcess(keyCode, keyChar, shiftDown, ctrlDown);
		}
		if(keyID == KeyEvent.RELEASE){
			keyReleasedProcess(keyCode, keyChar, shiftDown, ctrlDown);
		}
	}
	

	protected void keyPressedProcess(int keyCode, char keyChar, boolean shiftDown, boolean ctrlDown){
		if (keyChar=='i') keys[0]=true;
	}
	
	protected void keyReleasedProcess(int keyCode, char keyChar, boolean shiftDown, boolean ctrlDown){
		if (keyChar=='i') keys[0]=false;
	}

	
	
}
