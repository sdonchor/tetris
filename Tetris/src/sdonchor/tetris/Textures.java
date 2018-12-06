package sdonchor.tetris;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {
	public static Image[] textureLoad(String file, int width) throws IOException {
		BufferedImage buffer = ImageIO.read(Textures.class.getResource(file));
		Image[] textures = new Image[buffer.getWidth()/width];
		for(int i=0; i<textures.length;i++) {
			 	textures[i]=buffer.getSubimage(i*width, 0, width, width);
		}
		return textures;
		
	}
}

