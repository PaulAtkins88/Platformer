package com.paulsgames.platformer.window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
    private BufferedImage image;
    
    public BufferedImage loadImage(File path) {
	try {
	    image = ImageIO.read(path);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return image;	
    }
    
}
