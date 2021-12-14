package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import components.Ground;
import javafx.scene.paint.Color;
import utility.Resource;

public class Dino {
  private static int dinoY,dinoStartX, dinoTopY, dinoEndX;
  private static int dinoTop,topPoint, dinoBottom;
  private static int jumpFactor = 20;
  private static boolean topReach;
  
  public static final int STAND = 1,
                    RUN = 2,
                    JUMP = 3,
                    DEAD = 4;
  private final int LEFT_FOOT = 1,
                    RIGHT_FOOT = 2,
                    NO_FOOT = 3;
  
  private static int state;

  private int foot;

  static BufferedImage image;
  BufferedImage leftFoot;
  BufferedImage rightFoot;
  BufferedImage dead;

  

  public void create(Graphics g) {
    dinoBottom = dinoTop + image.getHeight();
    
    switch(state) {

      case STAND:
        g.drawImage(image, dinoStartX, dinoTopY, null);
        break;

      case RUN:
        if(foot == NO_FOOT) {
          foot = LEFT_FOOT;
          g.drawImage(leftFoot, dinoStartX, dinoTopY, null);
        } else if(foot == LEFT_FOOT) {
          foot = RIGHT_FOOT;
          g.drawImage(rightFoot, dinoStartX, dinoTopY, null);
        } else {
          foot = LEFT_FOOT;
          g.drawImage(leftFoot, dinoStartX, dinoTopY, null);
        }
        break;

      case JUMP:
        if(dinoTop > topPoint && !topReach) {
          g.drawImage(image, dinoStartX, dinoTop -= jumpFactor, null);
          break;
        } 
        if(dinoTop >= topPoint && !topReach) {
          topReach = true;
          g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
          break;
        }         
        if(dinoTop > topPoint && topReach) {      
          if(dinoTopY == dinoTop && topReach) {
            state = RUN;
            topReach = false;
            break;
          }    
          g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);          
          break;
        }
      case DEAD: 
        g.drawImage(dead, dinoStartX, dinoTop, null);    
        break;     
    } 
  }
  
  public Dino() {
	    image = new Resource().getResourceImage("../images/D-stand.png");
	    leftFoot = new Resource().getResourceImage("../images/D-left.png");
	    rightFoot = new Resource().getResourceImage("../images/D-right.png");
	    dead = new Resource().getResourceImage("../images/D-eyes.png");

	    dinoY = Ground.GROUND_Y + 5;
	    dinoTopY = Ground.GROUND_Y - image.getHeight() + 5;
	    dinoStartX = 100;
	    dinoEndX = dinoStartX + image.getWidth();
	    topPoint = dinoTopY - 120;

	    state = 1;
	    foot = NO_FOOT;
	  }
  
  public void die() {
    state = DEAD;
  }

  public static Rectangle getDino() {
    Rectangle dino = new Rectangle();
    dino.x = dinoStartX;

    if(state == JUMP && !topReach) dino.y = dinoTop - jumpFactor;
    else if(state == JUMP && topReach) dino.y = dinoTop + jumpFactor;
    else if(state != JUMP) dino.y = dinoTop;

    dino.width = image.getWidth();
    dino.height = image.getHeight();

    return dino;
  }
  
  public void jump() {
	    dinoTop = dinoTopY;
	    topReach = false;
	    state = JUMP;
	  }
  
  public void startRunning() {
    dinoTop = dinoTopY;
    state = RUN;
  }

  private class DinoImages {

  }

}