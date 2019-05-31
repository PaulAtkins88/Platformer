package com.paulsgames.platformer.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.paulsgames.platformer.framework.KeyInput;
import com.paulsgames.platformer.framework.ObjectId;
import com.paulsgames.platformer.objects.Player;

/*
 * 
 *  You are up to video 8 - you just implemented the code for collision around the player.
 * 
 */

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -6801280139600092644L;
    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;
    
    Handler handler;
    Camera cam;
    Random rand = new Random();

    private void init() {
	WIDTH = getWidth();
	HEIGHT = getHeight();
	handler = new Handler();
	handler.addObject(new Player(100, 100, handler, ObjectId.Player));
	handler.createLevel();
	this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
	if (running)
	    return;

	running = true;
	thread = new Thread(this);
	thread.start();
    }

    private void stop() {
	// TODO Auto-generated method stub

    }

    public void run() {
	System.out.println("Game has started.");
	init();
	this.requestFocus();
	long lastTime = System.nanoTime();
	float amountOfTicks = 60.0f; // set updates per second to 60
	float ns = 1000000000 / amountOfTicks;
	float delta = 0f;
	long timer = System.currentTimeMillis();
	int updates = 0;
	int frames = 0;
	while (running) {
	    long now = System.nanoTime();
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    while (delta >= 1) {
		tick();
		updates++;
		delta--;
	    }
	    if (running)
		render();
	    frames++;

	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		//System.out.println("FPS: " + frames + " TICKS: " + updates);
		frames = 0;
		updates = 0;
	    }
	}
	stop();
    }

    private void tick() {
	handler.tick();
    }

    private void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if (bs == null) {
	    this.createBufferStrategy(3);
	    return;
	}

	Graphics g = bs.getDrawGraphics();

	/////////////////////////////////////////////////
	// everything between the slashes is what is being drawn.
	g.setColor(Color.black);
	g.fillRect(0, 0, getWidth(), getHeight());

	handler.render(g);
	/////////////////////////////////////////////////

	g.dispose();
	bs.show();

    }

    public static void main(String[] args) {
	new Window(1366, 768, "Platformer - tutorial by RealTutsGML - Coded/tweaked by paulatkins88", new Game());
    }

}
