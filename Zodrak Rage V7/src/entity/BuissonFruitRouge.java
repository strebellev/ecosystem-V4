package entity;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class BuissonFruitRouge {
	PApplet parent;
	ArrayList<PVector> buissonfruitrouge;
	public PVector position;
	
	int r;
	
	PImage img;
	
	@SuppressWarnings("deprecation")
	public BuissonFruitRouge(PImage tempImg,PApplet p,PVector l){
		position = l.get();
		parent = p;
		r = 32;
		buissonfruitrouge = new ArrayList<PVector>();
		img = tempImg;
	}
	
	@SuppressWarnings("deprecation")
	public void add(PVector l){
		buissonfruitrouge.add(l.get());
	}
	
	public void display(PApplet p){
		/*
		p.ellipseMode(PConstants.CENTER);
		p.fill(0);
		p.ellipse(position.x, position.y, r, r);
		*/
		p.image(img, position.x-16, position.y-16);
	}
	
	public ArrayList<PVector> getBuissonFruitRouge(){
		return buissonfruitrouge;
	}
	
	public PVector position(){
		return position;
	}
	
	public float getR(){
		return r;
	}
}
