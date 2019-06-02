package com.paulsgames.platformer.framework;

import java.awt.image.BufferedImage;
import java.io.File;

import com.paulsgames.platformer.window.BufferedImageLoader;

public class Texture {
    SpriteSheet bs,ps;
    private BufferedImage blockSheet = null; // made my own block sheet, thats why it sucks.
    private BufferedImage playerSheet = null; // stolen player_sheet from tutorial
    
    public BufferedImage[] block = new BufferedImage[2];   
    public BufferedImage[] player = new BufferedImage[14];
    
    public Texture() {
	BufferedImageLoader loader = new BufferedImageLoader();
	try {
	    blockSheet = loader.loadImage(new File("res/block_sheet.png"));
	    playerSheet = loader.loadImage(new File("res/player_sheet.png"));
	}catch (Exception e) {
	    e.printStackTrace();
	}
	bs = new SpriteSheet(blockSheet);
	ps = new SpriteSheet(playerSheet);
	
	getTextures();
    }
    private void getTextures() {
	block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
	block[1] = bs.grabImage(2, 1, 32, 32); // grass block
	
	// grab right facing for player
	for (int i = 0 ; i < 7;i++) {
	    player[i] = ps.grabImage(i+1, 1, 32, 64);
	}
	// grab left facing for player
	for (int i = 0 ; i < 7;i++) {
	    player[i + 7] = ps.grabImage(i+14, 1, 32, 64);
	}
	
    }
}
