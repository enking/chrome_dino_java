import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class UserInterface {
  JFrame Window = new JFrame("공룡 게임 실행화면");
  
  public static int WIDTH = 800;
  public static int HEIGHT = 500;
  
  public void createAndShowGUI() {
    Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Container container = Window.getContentPane();
    
    GamePanel gamePanel = new GamePanel();
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);
    
    container.setLayout(new BorderLayout());
    
    container.add(gamePanel, BorderLayout.CENTER);
    
    Window.setSize(WIDTH, HEIGHT);
    Window.setResizable(false);
    Window.setVisible(true);
  }
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}