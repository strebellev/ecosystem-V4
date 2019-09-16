package animals;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class BearNid {
	PApplet parent;
	ArrayList<PVector> bearnid;
	public PVector position;
	
	int r;
	int territoire;
	int imgnum = 1;
	PImage[] imgNid = new PImage[imgnum];

	@SuppressWarnings("deprecation")
	public BearNid(PApplet p, PVector l){
		position = l.get();
		parent = p;
		r = 32;
		territoire = 1600;
		imgNid[0] = p.loadImage("res/animal/bear/terrier0.png");
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		bearnid.add(l.get());
	}

	public void display(PApplet p){
		p.ellipseMode(PConstants.CENTER);
		//p.fill(0);
		p.stroke(0);
		p.strokeWeight(2);
		//p.ellipse(position.x, position.y, territoire, territoire);
		p.noFill();
		p.noStroke();
		p.image(imgNid[0], position.x-22, position.y-22);
		p.text("blaireau", position.x+20, position.y);
	}

	public ArrayList<PVector> getBearNid(){
		return bearnid;
	}
	
	public PVector position(){
		return position;
	}
	
	public float getR(){
		return r;
	}
	
	public float getTerritoire(){
		return territoire;
	}

	
}
