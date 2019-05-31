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
    
  
}
