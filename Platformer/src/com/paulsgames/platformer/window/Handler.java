package com.paulsgames.platformer.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.paulsgames.platformer.framework.GameObject;
import com.paulsgames.platformer.framework.ObjectId;
import com.paulsgames.platformer.objects.Block;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    public void tick() {
	for (int i = 0; i < object.size(); i++) {
	    tempObject = object.get(i);
	    tempObject.tick(object);
	}
    }

    public void render(Graphics g) {
	for (int i = 0; i < object.size(); i++) {
	    tempObject = object.get(i);
	    tempObject.render(g);
	}
    }

    public void addObject(GameObject object) {
	this.object.add(object);
    }

    public void removeObject(GameObject object) {
	this.object.remove(object);
    }
    
    public void createLevel() {
	for (int yy = 0; yy < Game.HEIGHT+32; yy += 32) {
	    addObject(new Block(0,yy,ObjectId.Block));
	}
	for (int xx = 0; xx < Game.WIDTH*2; xx += 32) {
	    addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
	}
	for (int xx = 198; xx < Game.WIDTH-256; xx += 32) {
	    addObject(new Block(xx, Game.HEIGHT-230, ObjectId.Block));
	}
	
    }    
}
