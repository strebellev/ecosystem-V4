package entity;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;


public class Player2 extends PApplet {
	PApplet parent;
	ArrayList<PVector> player2;
	
	public PVector position;
	
	////test anim souris
	PVector centerPoint;
	////////////
	
	float r = 5;
	PVector velocity;
	PVector acceleration;
	float maxspeed;
	float maxforce;
	
	//////test anim/////
	int animLnumFrame = 9;
	int animRnumFrame = 9;
	int animUnumFrame = 9;
	int animDnumFrame = 9;
	
	int currentFrame = 0;
	
	PImage[] animLeft = new PImage[animLnumFrame];
	PImage[] animRight = new PImage[animRnumFrame];
	PImage[] animUp = new PImage[animUnumFrame];
	PImage[] animDown = new PImage[animDnumFrame];
	
	int previousDisplayTime;
	int deltaTime;
	////////////////////
	
	
	/////test mvt complex, fct !!!!
	boolean[] keys=new boolean[4];
	
	


	@SuppressWarnings("deprecation")
	public Player2(PVector l, PApplet p){
		parent = p;
		position = l.get();
		
	    acceleration = new PVector(0, 0);
	    velocity = new PVector(0, 0);
	    velocity.mult(5);
	    maxspeed = 1;
	    maxforce = (float) 0.15;
	    
	    ///test mvt complex
		p.registerMethod("keyEvent", this);
		
		//////test anim/////
		animLeft[0] = p.loadImage("res/player2/PlayerL0.png");
		animLeft[1] = p.loadImage("res/player2/PlayerL1.png");
		animLeft[2] = p.loadImage("res/player2/PlayerL2.png");
		animLeft[3] = p.loadImage("res/player2/PlayerL3.png");
		animLeft[4] = p.loadImage("res/player2/PlayerL4.png");
		animLeft[5] = p.loadImage("res/player2/PlayerL5.png");
		animLeft[6] = p.loadImage("res/player2/PlayerL6.png");
		animLeft[7] = p.loadImage("res/player2/PlayerL7.png");
		animLeft[8] = p.loadImage("res/player2/PlayerL8.png");
		
		animRight[0] = p.loadImage("res/player2/PlayerR0.png");
		animRight[1] = p.loadImage("res/player2/PlayerR1.png");
		animRight[2] = p.loadImage("res/player2/PlayerR2.png");
		animRight[3] = p.loadImage("res/player2/PlayerR3.png");
		animRight[4] = p.loadImage("res/player2/PlayerR4.png");
		animRight[5] = p.loadImage("res/player2/PlayerR5.png");
		animRight[6] = p.loadImage("res/player2/PlayerR6.png");
		animRight[7] = p.loadImage("res/player2/PlayerR7.png");
		animRight[8] = p.loadImage("res/player2/PlayerR8.png");
			
		animUp[0] = p.loadImage("res/player2/PlayerU0.png");
		animUp[1] = p.loadImage("res/player2/PlayerU1.png");
		animUp[2] = p.loadImage("res/player2/PlayerU2.png");
		animUp[3] = p.loadImage("res/player2/PlayerU3.png");
		animUp[4] = p.loadImage("res/player2/PlayerU4.png");
		animUp[5] = p.loadImage("res/player2/PlayerU5.png");
		animUp[6] = p.loadImage("res/player2/PlayerU6.png");
		animUp[7] = p.loadImage("res/player2/PlayerU7.png");
		animUp[8] = p.loadImage("res/player2/PlayerU8.png");
			
		animDown[0] = p.loadImage("res/player2/PlayerD0.png");
		animDown[1] = p.loadImage("res/player2/PlayerD1.png");
		animDown[2] = p.loadImage("res/player2/PlayerD2.png");
		animDown[3] = p.loadImage("res/player2/PlayerD3.png");
		animDown[4] = p.loadImage("res/player2/PlayerD4.png");
		animDown[5] = p.loadImage("res/player2/PlayerD5.png");
		animDown[6] = p.loadImage("res/player2/PlayerD6.png");
		animDown[7] = p.loadImage("res/player2/PlayerD7.png");
		animDown[8] = p.loadImage("res/player2/PlayerD8.png");
			
		deltaTime = 150; // animation speed -=fast +=slow
		previousDisplayTime = 0;
			
		////////////////////

		
	}
	
	@SuppressWarnings("deprecation")
	public void display(PApplet p,PVector MP){
		//parent.fill(255,0,0);
		//parent.stroke(255,0,0);
		//parent.noFill();
		//parent.ellipse(position.x, position.y, 400, 400);
		//parent.noStroke();
		//parent.fill(255,0,0);
		//parent.text("pos x " + position.x + "pos y " + position.y, position.x + 10,  position.y + 10);
		//parent.text("mp x " + MP.x + "mp y " + MP.y, position.x + 10,  position.y + 30);
		
		
		////test anim souris fct parfait mais à bouger pour le idle et mouvement
		centerPoint = new PVector(position.x,position.y);
		float angle = angleBetweenPV_PV(centerPoint, new PVector(MP.x, MP.y));
		angle=fixAngle(angle);
		
		int dir=0;
		float angleInDegrees=PApplet.degrees(angle);
		float scliceDegree = 360/4 ; 

		dir = 0;
		if       ( isBetween (angleInDegrees, 0*scliceDegree, 1*scliceDegree) ){
			dir = 0; //right
			//parent.text("right", position.x + 10,  position.y + 20);
		}
		else  if ( isBetween (angleInDegrees, 1*scliceDegree, 2*scliceDegree) ){
			dir = 1; //rightdown
			//parent.text("rightdown", position.x + 10,  position.y + 20);
		}
		else  if ( isBetween (angleInDegrees, 2*scliceDegree, 3*scliceDegree) ){
			dir = 2; //down
			//parent.text("down", position.x + 10,  position.y + 20);
		}

		else if  ( isBetween (angleInDegrees, 3*scliceDegree, 4*scliceDegree) ){
			dir = 3; //downleft
			//parent.text("downleft", position.x + 10,  position.y + 20);
		}
		
		//parent.text(angle +"\n" +PApplet.degrees(angle) + "\n" + dir + ": " + textFromDir[ dir ], position.x +10, position.y +40);
		
		///ici on met l'anim
		switch(dir){
		case 0:
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	p.image(animRight[currentFrame],position.x-22,position.y-22);
	    	break;
		case 1:
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	p.image(animDown[currentFrame],position.x-22,position.y-22);
	    	break;
		case 2:
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	p.image(animLeft[currentFrame],position.x-22,position.y-22);
	    	break;
		case 3:
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	p.image(animUp[currentFrame],position.x-22,position.y-22);
	    	break;
		}
	}
	
	String[] textFromDir = {
		"Right", 
		"Right,down", 
		"down", 
		"down, left", 
		"left", 
		"left up", 
		"up", 
		"up right", 
	}; 
	
	boolean isBetween(float inputValue, float down, float up) {
		return inputValue>down- 360/16 && inputValue<up- 360/16;
	}
	
	float angleBetweenPV_PV(PVector centerPV, PVector movingPV) {
		// calc angle : the core of the sketch 
		PVector d = new PVector();
		// calc angle
		// delta 
		d.x = movingPV.x - centerPV.x;
		d.y = movingPV.y - centerPV.y;
		// angle 
		float angle1 = PApplet.atan2(d.y, d.x);
		return angle1;
	} 

	float fixAngle(float angle1) {
		// if > 2xPI 
		if (angle1>PConstants.TWO_PI) angle1-=PConstants.TWO_PI; 
		if (angle1>PConstants.TWO_PI) angle1-=PConstants.TWO_PI; 

		// if < 0 (negative angles like -40 should be expressed as positive angles like 320)
		if (angle1<0.0) {
			angle1 = PConstants.TWO_PI + angle1;
		}
		if (angle1<0.0) angle1 = PConstants.TWO_PI + angle1;
			return angle1;
	}
	

	
	public void PlayerMvt(PApplet p){
		if(p.keyPressed){
			if(p.key == PConstants.CODED){
				if(p.keyCode == PConstants.UP){
					position.y -= 20;
				}
				if(p.keyCode == PConstants.DOWN){
					position.y += 20;
				}
				if(p.keyCode == PConstants.LEFT){
					position.x -= 20;
				}
				if(p.keyCode == PConstants.RIGHT){
					position.x += 20;
				}
			}
		}
	}
	
	public void update() {
		if (keys[0]) position.y += 20;  
	    if (keys[1]) position.y -= 20;  
	    if (keys[2]) position.x -= 20;  
	    if (keys[3]) position.x += 20;	
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
		if (keyChar=='s') keys[0]=true;
	    if (keyChar=='z') keys[1]=true;
	    if (keyChar=='q') keys[2]=true;
	    if (keyChar=='d') keys[3]=true;
	}
	
	protected void keyReleasedProcess(int keyCode, char keyChar, boolean shiftDown, boolean ctrlDown){
		if (keyChar=='s') keys[0]=false;
	    if (keyChar=='z') keys[1]=false;
	    if (keyChar=='q') keys[2]=false;
	    if (keyChar=='d') keys[3]=false;
	}
	
	
	

}
