package com.paulsgames.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.paulsgames.platformer.framework.GameObject;
import com.paulsgames.platformer.framework.ObjectId;

public class Bullet extends GameObject {

    public Bullet(float x, float y, ObjectId id, int velX) {
	super(x, y, id);
	this.velX = velX;
    }

    public void tick(LinkedList<GameObject> object) {
	x += velX;
    }

    public void render(Graphics g) {
	g.setColor(Color.red);
	g.fillRect((int) x, (int) y, 16, 16);
    }

    public Rectangle getBounds() {
	return new Rectangle((int)x,(int)y, 16,16);
    }
}
