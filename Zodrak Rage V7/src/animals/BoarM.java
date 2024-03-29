package animals;

import java.util.ArrayList;

import mechanisme.DNAblaireau;
import mechanisme.DNAboar;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class BoarM {
	PApplet parent;
	ArrayList<PVector> boarm;
	
	public PVector position;
	
	DNAboar dnaboar;
	float xoff;
	float yoff;
	
	PVector velocity;
	PVector acceleration;
	
	float wandertheta;
	float maxforce;
	
	///limite de terrain
	float d = 50;
	
	////////////variables genetiques
	float water;
	float maxWater;
	float food;
	float maxFood;
	public float health;
	float r; ///la taille
	public float maxspeed;
	float lifeTime;
	float reproduceTime;
	float maxLifeTime;
	float speed;
	float fieldOfView;
	
	float eating =20;
	
	float weight;
	float foodPerDay;
	float maxFoodPerDay;

	

	//////test anim/////
	int animLnumFrame = 3;
	int animRnumFrame = 3;
	int animUnumFrame = 3;
	int animDnumFrame = 3;
	
	int currentFrame = 0;
	
	PImage[] animLeft = new PImage[animLnumFrame];
	PImage[] animRight = new PImage[animRnumFrame];
	PImage[] animUp = new PImage[animUnumFrame];
	PImage[] animDown = new PImage[animDnumFrame];
	
	PImage[] pupanimLeft = new PImage[animLnumFrame];
	PImage[] pupanimRight = new PImage[animRnumFrame];
	PImage[] pupanimUp = new PImage[animUnumFrame];
	PImage[] pupanimDown = new PImage[animDnumFrame];
	
	PImage[] younganimLeft = new PImage[animLnumFrame];
	PImage[] younganimRight = new PImage[animRnumFrame];
	PImage[] younganimUp = new PImage[animUnumFrame];
	PImage[] younganimDown = new PImage[animDnumFrame];

	
	int previousDisplayTime;
	int deltaTime;
	////////////////////

	@SuppressWarnings("deprecation")
	public BoarM(PVector l, DNAboar dnaboar_, PApplet p){
		parent = p;
		position = l.get();
		xoff = p.random(3200);
		yoff = p.random(3200);
		
		dnaboar = dnaboar_;
		
		///////A MODIF////////////
		
		health = PApplet.map(dnaboar.genes[0], 0, 1, 50, 250);
		r = PApplet.map(dnaboar.genes[0], 0, 1, 4, 8);
		//maxspeed = PApplet.map(dnaboar.genes[0], 0, 1, 1, 2);
		lifeTime = PApplet.map(dnaboar.genes[0], 0, 1, 500, 600);
		maxLifeTime = PApplet.map(dnaboar.genes[0], 0, 1, 500, 600);
		water = PApplet.map(dnaboar.genes[1], 0, 1, 200, 400);
		maxWater = PApplet.map(dnaboar.genes[1], 0, 1, 200, 400);
		food = PApplet.map(dnaboar.genes[1], 0, 1, 200, 600);
		maxFood = PApplet.map(dnaboar.genes[1], 0, 1, 200, 600);
		fieldOfView = PApplet.map(dnaboar.genes[2], 0, 1, 400, 800);
		
		speed = PApplet.map(dnaboar.genes[0], 0, 1, 2, 3);
		
		///for food 1000 = 1kg
		weight = PApplet.map(dnaboar.genes[1], 0, 1, 75000, 100000);
		foodPerDay = 0;
		maxFoodPerDay = PApplet.map(dnaboar.genes[1], 0, 1, 1000, 2000);

		
		acceleration = new PVector(0,0);
		velocity = new PVector(0,0);
		maxforce = (float) 0.1;

		//////test anim adulte/////
		animLeft[0] = p.loadImage("res/animal/boar/boarL0.png");
		animLeft[1] = p.loadImage("res/animal/boar/boarL1.png");
		animLeft[2] = p.loadImage("res/animal/boar/boarL2.png");
		
		animRight[0] = p.loadImage("res/animal/boar/boarR0.png");
		animRight[1] = p.loadImage("res/animal/boar/boarR1.png");
		animRight[2] = p.loadImage("res/animal/boar/boarR2.png");
			
		animUp[0] = p.loadImage("res/animal/boar/boarU0.png");
		animUp[1] = p.loadImage("res/animal/boar/boarU1.png");
		animUp[2] = p.loadImage("res/animal/boar/boarU2.png");
			
		animDown[0] = p.loadImage("res/animal/boar/boarD0.png");
		animDown[1] = p.loadImage("res/animal/boar/boarD1.png");
		animDown[2] = p.loadImage("res/animal/boar/boarD2.png");
		
		//////test anim pup/////
		pupanimLeft[0] = p.loadImage("res/animal/boar/pupboarL0.png");
		pupanimLeft[1] = p.loadImage("res/animal/boar/pupboarL1.png");
		pupanimLeft[2] = p.loadImage("res/animal/boar/pupboarL2.png");
		
		pupanimRight[0] = p.loadImage("res/animal/boar/pupboarR0.png");
		pupanimRight[1] = p.loadImage("res/animal/boar/pupboarR1.png");
		pupanimRight[2] = p.loadImage("res/animal/boar/pupboarR2.png");
			
		pupanimUp[0] = p.loadImage("res/animal/boar/pupboarU0.png");
		pupanimUp[1] = p.loadImage("res/animal/boar/pupboarU1.png");
		pupanimUp[2] = p.loadImage("res/animal/boar/pupboarU2.png");
			
		pupanimDown[0] = p.loadImage("res/animal/boar/pupboarD0.png");
		pupanimDown[1] = p.loadImage("res/animal/boar/pupboarD1.png");
		pupanimDown[2] = p.loadImage("res/animal/boar/pupboarD2.png");

		//////test anim young/////
		younganimLeft[0] = p.loadImage("res/animal/boar/youngboarL0.png");
		younganimLeft[1] = p.loadImage("res/animal/boar/youngboarL1.png");
		younganimLeft[2] = p.loadImage("res/animal/boar/youngboarL2.png");
		
		younganimRight[0] = p.loadImage("res/animal/boar/youngboarR0.png");
		younganimRight[1] = p.loadImage("res/animal/boar/youngboarR1.png");
		younganimRight[2] = p.loadImage("res/animal/boar/youngboarR2.png");
			
		younganimUp[0] = p.loadImage("res/animal/boar/youngboarU0.png");
		younganimUp[1] = p.loadImage("res/animal/boar/youngboarU1.png");
		younganimUp[2] = p.loadImage("res/animal/boar/youngboarU2.png");
			
		younganimDown[0] = p.loadImage("res/animal/boar/youngboarD0.png");
		younganimDown[1] = p.loadImage("res/animal/boar/youngboarD1.png");
		younganimDown[2] = p.loadImage("res/animal/boar/youngboarD2.png");

			
		deltaTime = 100; // animation speed +=fast -=slow
		previousDisplayTime = 0;
			
		////////////////////

	}
	
	public void mvtUpdate(){
		position.add(velocity);
		velocity.add(acceleration);
		velocity.limit(maxspeed);
		acceleration.mult(0);
		acceleration.limit(maxspeed);
	}

	void update(){
		water -= 0.005;
		if(water < 0){
			water = 0;
		}
		if(water > maxWater){
			water = maxWater;
		}
		food -= 0.1;
		if(food < 0){
			food = 0;
		}
		if(food > maxFood){
			food = maxFood;
		}
		if(health < 0) health = 0;
		if(health > 250) health = 250;
		health -= 0.01;
		reproduceTime += 0.1;
		eating += 0.1;
		if(eating > 20) eating = 20;
		
		lifeTime -= 0.01;
		if(lifeTime < 0) lifeTime = 0; 
		////plus ils veillissent plus il ralentissent
		maxspeed = speed * lifeTime/maxLifeTime;
		
		if(foodPerDay > maxFoodPerDay) foodPerDay = maxFoodPerDay;
	}

	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void wander(PApplet p){
		
		float wanderR = 50;  // Radius for our "wander circle"
		float wanderD = 80;  // Distance for our "wander circle"
		float change = (float) 1.03;
		wandertheta += p.random(-change, change);  // Randomly change wander theta
		
		// Now we have to calculate the new position to steer towards on the wander circle
		PVector circlepos = velocity.get();
		circlepos.normalize();
		circlepos.mult(wanderD);
		circlepos.add(position);  // Make it relative to boid's position
		
		float h = velocity.heading2D();
		
		PVector circleOffSet = new PVector(wanderR*p.cos(wandertheta+h), wanderR*p.sin(wandertheta+h));
		PVector target = PVector.add(circlepos, circleOffSet);
		seek(target);
		
	}

	void applyForce(PVector force){
		acceleration.add(force);
	}
	
	void seek(PVector target){
		PVector desired = PVector.sub(target, position);
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}

	public void boundaries(){
		PVector desired = null;
		
		if(position.x < d){
			desired = new PVector(maxspeed,velocity.y);
		}
		else if(position.x > 3200 - d){
			desired = new PVector(-maxspeed, velocity.y);
		}
		
		if(position.y < d){
			desired = new PVector(velocity.x, maxspeed);
		}
		else if(position.y > 3200 -d){
			desired = new PVector(velocity.x, -maxspeed);
		}
		
		if(desired != null){
			desired.normalize();
			desired.mult(maxspeed);
			PVector steer = PVector.sub(desired, velocity);
			steer.limit(maxforce);
			applyForce(steer);
		}
	}

	public boolean isTooClose(PVector target){
		return PApplet.dist(position.x, position.y, target.x, target.y) < 10;
	}
	
	public void goAway(PVector target){
		PVector desired = new PVector(position.x - target.x, position.y - target.y);
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}

	public boolean isInDanger(PVector target){
		return PApplet.dist(position.x, position.y, target.x, target.y) < 150;
	}
	
	public void runaway(PVector target){
		PVector desired = new PVector(position.x - target.x, position.y - target.y);
		desired.normalize();
		desired.mult(maxspeed);
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}

	public void run(PApplet p){
		update();
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public void display(PApplet p){
		
		/*
		float theta = velocity.heading2D() + p.radians(90);
		p.fill(91,175,8);
		p.stroke(255,0,0, lifeTime);
		p.strokeWeight(1);
		p.pushMatrix();
		p.translate(position.x, position.y);
		p.rotate(theta);
		p.beginShape(p.TRIANGLES);
		p.vertex(0, -r*2);
		p.vertex(-r, r*2);
		p.vertex(r, r*2);
		p.endShape(PConstants.CLOSE);
		p.popMatrix();
		p.ellipse(position.x,position.y,r*2,r*2);
		*/
		
		//p.fill(0);
		//p.text("reproduceTime " + reproduceTime, position.x+10, position.y+10);
		//p.text("food " + food, position.x+10,position.y+10);
		//p.text("eating " + eating, position.x+10,position.y+20);
		//p.text("lifetime " + lifeTime, position.x+10,position.y+30);
		//p.text("Maxlifetime " + maxLifeTime, position.x+10,position.y+40);
		//p.text("reproduct time " + reproduceTime, position.x+10,position.y+40);
		//p.text("Male", position.x+10,position.y+50);
		//p.text("water " + water, position.x+10,position.y+60);
		//p.text("health " + health , position.x +10, position.y +20);
		//p.noFill();
		
		
		/////test sprite fct parfait////
		
		int dir = p.round(4 * (velocity.heading2D() + PApplet.radians(90)) /  PApplet.TWO_PI + 4) % 4;
	    switch(dir) {
	    case 0:
	    	//p.text("up",10,10);
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	if(pup() == true){
	    		p.image(pupanimUp[currentFrame],position.x-11,position.y-11);
	    	}
	    	if(young() == true){
	    		p.image(younganimUp[currentFrame],position.x-18,position.y-18);
	    	}
	    	if(young() == false && pup() == false){
	    		p.image(animUp[currentFrame],position.x-23,position.y-23);
	    	}
	    	break;
	    case 1:
	    	//p.text("right",10,10);
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	if(pup() == true){
	    		p.image(pupanimRight[currentFrame],position.x-11,position.y-11);
	    	}
	    	if(young() == true){
	    		p.image(younganimRight[currentFrame],position.x-18,position.y-18);
	    	}
	    	if(young() == false && pup() == false){
	    		p.image(animRight[currentFrame],position.x-23,position.y-23);
	    	}
	    	break;
	    case 2:
	    	//p.text("down",10,10);
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	if(pup() == true){
	    		p.image(pupanimDown[currentFrame],position.x-11,position.y-11);
	    	}
	    	if(young() == true){
	    		p.image(younganimDown[currentFrame],position.x-18,position.y-18);
	    	}
	    	if(young() == false && pup() == false){
	    		p.image(animDown[currentFrame],position.x-23,position.y-23);
	    	}
	    	break;
	    case 3:
	    	//p.text("left",10,10);
	    	if (p.millis() > previousDisplayTime + deltaTime) {
	    	    currentFrame++;
	    	    if (currentFrame > 2) { 
	    	      currentFrame = 0;
	    	    }
	    	    previousDisplayTime = p.millis();
	    	}
	    	if(pup() == true){
	    		p.image(pupanimLeft[currentFrame],position.x-11,position.y-11);
	    	}
	    	if(young() == true){
	    		p.image(younganimLeft[currentFrame],position.x-18,position.y-18);
	    	}
	    	if(young() == false && pup() == false){
	    		p.image(animLeft[currentFrame],position.x-23,position.y-23);
	    	}
	    	break;
	    }
		////////////////////
		
	}

	public void findFood(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 10){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public void findWater(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 10){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}

	
	public void findPray(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 10){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}


	public void findFemale(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}

	public void goToNest(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}
	
	public void stayInPack(PVector target){
		PVector desired = PVector.sub(target, position);
		float d = desired.mag();
		if(d < 100){
			float m = PApplet.map(d, 0, 10, 0, maxspeed);
			desired.setMag(m);
		}
		else{
			desired.setMag(maxspeed);
		}
		PVector steer = PVector.sub(desired, velocity);
		steer.limit(maxforce);
		applyForce(steer);
	}


	public DNAboar getDNAboar(){
		return dnaboar;
	}
	
	public float getR(){
		return r;
	}

	public ArrayList<PVector> getBoarM(){
		return boarm;
	}

	public boolean dead(){
		if(health <= 0.0 || lifeTime <= 0.0)return true;
		else 	return false;
		
	}
	
	
	public boolean soif(){
		if(water < 50){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void drinkWater(){
		water += 200;
	}
	
	/////new food model/////
	public float getWeight(){
		return weight;
	}

	public boolean wellFed(){
		if(foodPerDay >= maxFoodPerDay) return true;
		else return false;
	}
	
	public void resetWellFed(){
		foodPerDay = 0;
	}
	
	public void eat(float food){
		foodPerDay += food;
	}
	
	public boolean NstarvingLvl1(){
		if(food < maxFood){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean NstarvingLvl2(){
		if(food < maxFood/2){
			return true;
		}
		else{
			return false;
		}
	}
	////////////////////////
	
	public boolean reproduceTime(){
		if(reproduceTime > 20){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void resetReproduce(){
		reproduceTime = 0;
	}
	
	public boolean eatingTime(){
		if(eating < 10) return true;
		else return false;
	}
	
	public void resetEating(){
		eating = 0;
	}
	
	public void gettingOld(){
		float randomAge = parent.random(20, 50);
		lifeTime -= randomAge;
	}

	public boolean pup(){
		float x = maxLifeTime/5;
		if(lifeTime > maxLifeTime-x){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean young(){
		float x = maxLifeTime/5;
		float y = maxLifeTime/6;
		if(lifeTime > maxLifeTime-x && lifeTime < maxLifeTime-y){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean old(){
		float x = maxLifeTime/5;
		if(lifeTime < x){
			return true;
		}
		else{
			return false;
		}
	}

	
	
}
