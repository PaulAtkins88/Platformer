package com.paulsgames.platformer.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import com.paulsgames.platformer.framework.KeyInput;
import com.paulsgames.platformer.framework.ObjectId;
import com.paulsgames.platformer.framework.Texture;
import com.paulsgames.platformer.objects.Block;
import com.paulsgames.platformer.objects.Player;

/*
 * 
 *  You are up to video 17 at start. You need to fix the bullets if you spawn too many there is an out of bounds exception. handle it.
 * 
 */

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -6801280139600092644L;
    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    private BufferedImage level = null, clouds = null;

    Handler handler;
    Camera cam;
    static Texture tex;
    Random rand = new Random();

    private void init() {
	WIDTH = getWidth();
	HEIGHT = getHeight();
	tex = new Texture();
	BufferedImageLoader loader = new BufferedImageLoader();
	level = loader.loadImage(new File("res/level.png")); // loads level sprite sheet
	clouds = loader.loadImage(new File("res/cloud.png"));
	System.out.println("cloud width = " + clouds.getWidth());
	handler = new Handler();
	cam = new Camera(0, 0);
	loadImageLevel(level);
	// handler.addObject(new Player(100, 100, handler, ObjectId.Player));
	// handler.createLevel();
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
		// System.out.println("FPS: " + frames + " TICKS: " + updates);
		frames = 0;
		updates = 0;
	    }
	}
	stop();
    }

    private void tick() {
	handler.tick();
	for (int i = 0; i < handler.object.size(); i++) {
	    if (handler.object.get(i).getId() == ObjectId.Player)
		cam.tick(handler.object.get(i));
	}

    }

    private void render() {
	BufferStrategy bs = this.getBufferStrategy();
	if (bs == null) {
	    this.createBufferStrategy(3);
	    return;
	}

	Graphics g = bs.getDrawGraphics();
	Graphics2D g2d = (Graphics2D) g;

	/////////////////////////////////////////////////
	// everything between the slashes is what is being drawn.
	g.setColor(new Color(25,190,225));
	g.fillRect(0, 0, getWidth(), getHeight());
	
	g2d.translate(cam.getX(), cam.getY()); // beginning of camera
	// anything inside here gets affected by the camera
	
	for (int xx = 0; xx < clouds.getWidth() * 10; xx += clouds.getWidth()+(clouds.getWidth()*2))
	    g.drawImage(clouds, xx, 50, this);
	handler.render(g);

	g2d.translate(-cam.getX(), -cam.getY()); // end of camera
	/////////////////////////////////////////////////

	g.dispose();
	bs.show();

    }

    private void loadImageLevel(BufferedImage image) {
	int w = image.getWidth();
	int h = image.getHeight();

	for (int xx = 0; xx < h; xx++) {
	    for (int yy = 0; yy < w; yy++) {
		int pixel = image.getRGB(xx, yy);
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;

		if (red == 255 && green == 255 && blue == 255)
		    handler.addObject(new Block(xx * 32, yy * 32,0, ObjectId.Block));
		if (red == 0 && green == 255 && blue == 0)
		    handler.addObject(new Block(xx * 32, yy * 32,1, ObjectId.Block));
		
		if (red == 0 && green == 0 && blue == 255)
		    handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
	    }
	}
    }
    
    public static Texture getInstance() {
	return tex;
    }

    public static void main(String[] args) {
	new Window(1366, 768, "Platformer - tutorial by RealTutsGML - Coded/tweaked by paulatkins88", new Game());
    }

}
