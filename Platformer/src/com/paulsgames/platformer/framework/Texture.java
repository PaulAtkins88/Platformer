package com.paulsgames.platformer.framework;

import java.awt.image.BufferedImage;
import java.io.File;

import com.paulsgames.platformer.window.BufferedImageLoader;

public class Texture {
    SpriteSheet bs,ps;
    private BufferedImage blockSheet = null;
    //private BufferedImage playerSheet = null
    
    public BufferedImage[] block = new BufferedImage[2];    
    
    public Texture() {
	BufferedImageLoader loader = new BufferedImageLoader();
	try {
	    blockSheet = loader.loadImage(new File("res/block_sheet.png"));
	    //playerSheet = loader.loadImage(new File("/res/player_sheet.png"));
	}catch (Exception e) {
	    e.printStackTrace();
	}
	bs = new SpriteSheet(blockSheet);
	//ps = new SpriteSheet(new File("/res/player_sheet.png"));
	
	getTextures();
    }
    private void getTextures() {
	block[0] = bs.grabImage(1, 1, 32, 32); // dirt block
	block[1] = bs.grabImage(2, 1, 32, 32); // grass block
    }
}
