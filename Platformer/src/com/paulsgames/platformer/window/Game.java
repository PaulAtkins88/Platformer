package com.paulsgames.platformer.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.paulsgames.platformer.framework.ObjectId;
import com.paulsgames.platformer.objects.Test;

/*
 * 
 *  You are up to episode 5... same game engine every time with this guy but you are doing this for the physics aspect.
 * 
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -6801280139600092644L;
	private boolean running = false;
	private Thread thread;

	Handler handler;
	Random rand = new Random();

	private void init() {
		handler = new Handler();
		for (int i = 0; i < 50; i++) {
			handler.addObject(new Test(rand.nextInt(800), rand.nextInt(600), ObjectId.Test));
		}

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
				System.out.println("FPS: " + frames + " TICKS: " + updates);
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
		new Window(800, 600, "Platformer - tutorial by RealTutsGML - Coded/tweaked by paulatkins88", new Game());
	}

}