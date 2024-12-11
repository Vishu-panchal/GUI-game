// import java.awt.Color;
// import java.awt.event.KeyListener;
// import java.awt.image.BufferedImage;
// import java.io.IOException;

// import javax.imageio.ImageIO;
// import javax.swing.JPanel;
// import javax.swing.Timer;

// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class AppPanel extends JPanel implements KeyListener {

    Timer timer;
    Bird b = new Bird();
    Pipe p = new Pipe();
    int iniIndex = 0;
    int birdX = 150;
    int birdY = 150;

    double score = 0;

    Image icon;
    Image cloud;

    boolean marGyi =  false;

    int pipeX = 500;
    int pipeX2 = 800;
    int pipeX3 = 1100;

    BufferedImage imgPipe;
    BufferedImage birdArr[] = b.cutSpriteSheet();

    int[] pipeArr = p.array();

    int pipeIndex = 0;

    void restart(){
        repaint();
        setBackground(Color.RED);
        return;
    }

    AppPanel() {
        Color color = new Color(135, 206, 235);
        setBackground(color);
        setSize(500, 500);

        icon = new ImageIcon("pipe.png").getImage();
        cloud = new ImageIcon("cloud.png").getImage();

        callPaintAgain();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
        addKeyListener(this);

    }

    void paintBird(Graphics g) {

        b.showBird(g, birdArr[iniIndex], birdX, birdY);
        // g.drawImage(icon, 250, 0, 20,100);
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        if (marGyi) {
            
            pen.setColor(Color.WHITE);
           
            setBackground(Color.black);
            pen.setFont(new Font("Arial", Font.BOLD, 40));
            
            pen.drawString(" Game Over " + (int)score, 200, 280);

            // if (!isAncestorOf(restartButton)) {
            //     add(restartButton);
            //     revalidate(); // Revalidate to ensure the button is displayed
            //     repaint();
            // }
                return; // Stop drawing other components
        }
        paintPipe(pen);
        paintBird(pen);
        // paintPipe(pen);

    }

    void loadSpriteSheet() {
        try {
            imgPipe = ImageIO.read(AppPanel.class.getResource("bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int pipe1UH = 160;
    int pipe1SH = 320;

    int cloudX1 = 700;
    int cloudX2 = 1200;

    void paintPipe(Graphics g) {

        g.drawImage(cloud , cloudX1 , 100, 100, 100 , this);
        g.drawImage(cloud , cloudX2 , 50, 100, 100 , this);
        g.drawImage(icon, pipeX, 0, 50, pipe1UH, this);
        g.drawImage(icon, pipeX, pipe1SH, 50, 160, this);

        g.drawImage(icon, pipeX2, 0, 50, 70, this);
        g.drawImage(icon, pipeX2, 200, 50, 300, this);

        g.drawImage(icon, pipeX3, 0, 50, 260, this);
        g.drawImage(icon, pipeX3, 400, 50, 160, this);

        // g.drawImage(cloud , cloudX , 100, 100, 100 , this);

    }

    void callPaintAgain() 
    {
        timer = new Timer(50, (a) -> {

            birdY += 5;
            score = score + 0.1;
            

            if(birdY > 500 || birdY <=0){
                marGyi = true;
                timer.stop();
            }

          
            cloudX1 = cloudX1 - 20;

            if(cloudX1 < -40){
                cloudX1 = 700;
            }

            if(cloudX2 < -40){
                cloudX2 = 700;
            }

            cloudX2 = cloudX2 - 20;
            iniIndex++;
            if (iniIndex > 7) {
                iniIndex = 0;
            }

            pipeIndex++;
            if (pipeIndex > 9) {
                pipeIndex = 0;
            }

            pipeX -= 10;

            pipeX2 -= 10;

            pipeX3 -= 10;

            if (pipeX < -30) {
                pipeX = 900;
            }

            if (pipeX2 < -30) {
                pipeX2 = 900;
            }

            if (pipeX3 < -30) {
                pipeX3 = 900;
            }

            if (checkCollision(pipeX, pipe1UH, pipe1SH) ||
            checkCollision(pipeX2, 70, 200) ||
            checkCollision(pipeX3, 260, 400)) {
            marGyi = true;
            timer.stop();
        }
            

            repaint();
        });

        timer.start();
        
    }


    boolean checkCollision(int pipeX, int topHeight, int bottomY) {
        int birdWidth = 30; 
        int birdHeight = 30; 
        int pipeWidth = 30; 

       
        if (birdX + birdWidth > pipeX && birdX < pipeX + pipeWidth) {
            if (birdY < topHeight) {
                return true; 
            }
        }

     
        if (birdX + birdWidth > pipeX && birdX < pipeX + pipeWidth) {
            if (birdY + birdHeight > bottomY) {
                return true; 
            }
        }

        return false; 
    }


    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            birdY = birdY - 40;
          
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            birdY = birdY + 20;
          
        }
        
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
