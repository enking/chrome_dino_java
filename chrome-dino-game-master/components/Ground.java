package components;

import utility.Resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Ground {
  
  private class GroundImage {
    BufferedImage img;
    int x;
  }
  
  public static int GROUND_Y;
  
  private BufferedImage img;
  
  private ArrayList<GroundImage> groundImgSet;
  
  public Ground(int panelHeight) {
    GROUND_Y = (int)(panelHeight - 0.25 * panelHeight);
    
    try{
      img = new Resource().getResourceImage("../images/Ground.png");
    } catch(Exception e) {e.printStackTrace();}
    
    groundImgSet = new ArrayList<GroundImage>();
    
    
    for(int i=0; i<3; i++) {
      GroundImage obj = new GroundImage();
      obj.img = img;
      obj.x = 0;
      groundImgSet.add(obj);
    }
  }
  
  public void update() {
    Iterator<GroundImage> looper = groundImgSet.iterator();
    GroundImage fst = looper.next();
    
    fst.x -= 10;
    int preX = fst.x;
    while(looper.hasNext()) {
      GroundImage next = looper.next();
      next.x = preX + img.getWidth();
      preX = next.x;
    }
    
    if(fst.x < -img.getWidth()) {
      groundImgSet.remove(fst);
      fst.x = preX + img.getWidth();
      groundImgSet.add(fst);
    }
    
  }
  
  public void create(Graphics g) {
		for(GroundImage img : groundImgSet) {
			g.drawImage(img.img, (int) img.x, GROUND_Y, null);
		}
	}
}