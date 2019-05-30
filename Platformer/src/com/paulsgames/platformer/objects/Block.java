package com.paulsgames.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.paulsgames.platformer.framework.GameObject;
import com.paulsgames.platformer.framework.ObjectId;

public class Block extends GameObject {

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int) x, (int) y, 32, 32);
	}

	public Rectangle getBounds() {
	    // return the outline of the image that has just been drawn.
	    return new Rectangle((int)x, (int) y, 32,32); 
	}


}
