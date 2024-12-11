import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.BufferUnderflowException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bird {
    // int xAxis;
    // int yAxis;
    // int carWidht;
    // int carHeight;
    BufferedImage spriteSheet;
    BufferedImage singleImages[] = new BufferedImage[8];
    // ImageIcon spriteSheet;

    Bird() {
        loadSpriteSheet();
    }

    void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read(AppPanel.class.getResource("bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage[] cutSpriteSheet() {
        singleImages[1] = spriteSheet.getSubimage(0, 0, 598, 402);
        singleImages[0] = spriteSheet.getSubimage(598, 0, 598, 402);
        singleImages[2] = spriteSheet.getSubimage(1196, 0, 598, 402);
        singleImages[3] = spriteSheet.getSubimage(1794, 0, 580, 402);
        singleImages[4] = spriteSheet.getSubimage(0, 402, 598, 402);
        singleImages[5] = spriteSheet.getSubimage(598, 402, 598, 402);
        singleImages[6] = spriteSheet.getSubimage(1196, 402, 598, 402);
        singleImages[7] = spriteSheet.getSubimage(1794, 402, 598, 402);

        return singleImages;
    }

    void showBird(Graphics pen, BufferedImage img, int x , int y ) {
        pen.drawImage(img, x, y, 50, 50, null);
    }

  
}