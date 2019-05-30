package com.paulsgames.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.paulsgames.platformer.framework.GameObject;
import com.paulsgames.platformer.framework.ObjectId;

public class Player extends GameObject {

    private float width = 48, height = 96;
    private final float MAX_SPEED = 10;

    private float gravity = 0.5f;

    public Player(float x, float y, ObjectId id) {
	super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
//	x += velX;
//	y += velY;
	if (falling || jumping) {
	    velY += gravity;

	    if (velY > MAX_SPEED) {
		velY = MAX_SPEED;
	    }
	}
    }

    public void render(Graphics g) {	
	g.setColor(Color.blue);
	g.fillRect((int) x, (int) y, (int) width, (int) height);
	
	Graphics2D g2d = (Graphics2D) g;
	
	// Draw the collion boxes 
	g.setColor(Color.green);
	g2d.draw(getBounds());
	g2d.draw(getBoundsRight());
	g2d.draw(getBoundsLeft());
	g2d.draw(getBoundsTop());
    }

    /*
     * Below is the code for setting the collision boxes. Feel free to change these values around if needed.
     * it is literally a simple rectangle, and if the rectangle intersects with something, do something. 
     */
    
    
    public Rectangle getBounds() {
	return new Rectangle((int) ((int) x+(width/2)-((width/2)/2)), (int) ((int) y+(height/2)), (int) width/2, (int) height/2);
    }

    public Rectangle getBoundsTop() {
	return new Rectangle((int) ((int) x+(width/2)-((width/2)/2)), (int) y, (int) width/2, (int) height/2);
    }

    public Rectangle getBoundsRight() {
	return new Rectangle((int) ((int) x+width-5), (int) y+5, (int) 5, (int) height-10);
    }

    public Rectangle getBoundsLeft() {
	return new Rectangle((int) x, (int) y+5, (int) 5, (int) height-10);
    }


}