package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import utility.Resource;

public class Obstacles {
  private class Obstacle {
    BufferedImage img; 
    int x;
    int y;

    Rectangle getObstacle() {
      Rectangle obstacle = new Rectangle();
      obstacle.x = x;
      obstacle.y = y;
      obstacle.width = img.getWidth();
      obstacle.height = img.getHeight();

      return obstacle;
    }
  }
  
  private int firstX;
  private int moveSpeed;
  private int obstacleSpace;
  
  private ArrayList<BufferedImage> imageList;
  private ArrayList<Obstacle> obList;

  private Obstacle blockedAt;
  
  public Obstacles(int firstPos) {
    obList = new ArrayList<Obstacle>();
    imageList = new ArrayList<BufferedImage>();
    
    firstX = firstPos;
    obstacleSpace = 200;
    moveSpeed = 11;
    
    imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
    imageList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
    imageList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
    imageList.add(new Resource().getResourceImage("../images/Cactus-3.png"));
    imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
    imageList.add(new Resource().getResourceImage("../images/Cactus-3.png"));
    
    int x = firstX;
    
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
      
      ob.img = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleSpace;
      
      obList.add(ob);
    }
  }
  
  public void update() {
    Iterator<Obstacle> looper = obList.iterator();
    
    Obstacle firstOb = looper.next();
    firstOb.x -= moveSpeed;
    
    while(looper.hasNext()) {
      Obstacle ob = looper.next();
      ob.x -= moveSpeed;
    }
    
    Obstacle lastOb = obList.get(obList.size() - 1);
    
    if(firstOb.x < -firstOb.img.getWidth()) {
      obList.remove(firstOb);
      firstOb.x = obList.get(obList.size() - 1).x + obstacleSpace;
      obList.add(firstOb);
    }
  }
  
  public void resume() {
    int x = firstX/2;   
    obList = new ArrayList<Obstacle>();
    
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
      
      ob.img = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleSpace;
      
      obList.add(ob);
    }
  }
  public boolean hasCollided() {
	    for(Obstacle ob : obList) {
	      if(Dino.getDino().intersects(ob.getObstacle())) {
	        blockedAt = ob;
	        return true;
	      }   
	    }
	    return false;
	  }
  
  public void create(Graphics g) {
	    for(Obstacle ob : obList) {
	      g.setColor(Color.black);
	      g.drawImage(ob.img, ob.x, ob.y, null);
	    }
	  }
}