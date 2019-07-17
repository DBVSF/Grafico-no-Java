
package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;
    
    private BufferedImage image;
    
    
  
   
    
    
    
    public Game (){
        
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        
    }
    
     public void initFrame(){
        
        frame = new JFrame("Game#1");
        frame.add(this);
        //nao poder mudar as dimensões 
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        //para a execução clicando no X do JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //executou apareceu
        frame.setVisible(true);
        
    }
    
    public synchronized void start (){
        
        thread = new Thread(this);
        thread.start();
        isRunning = true;
        
    }
        
    public synchronized void stop (){
        
        //parar caso dê erro
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
       
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        
    }
    
    public void tick(){
        
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        //cor do backgroud
        g.setColor(new Color(30,30,30));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.setColor(Color.white);
        g.drawString("OOOOOOO",19, 19);
        g.setColor(Color.MAGENTA);
        g.fillRect(20, 20, 80, 80);
        
        g = bs.getDrawGraphics();
        //preenchendo o background
        g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        bs.show();
    }
    
    
    public void run(){
        //loop avançado para 60 frames por segundo
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        
        while(isRunning){
            
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            
            if (delta >= 1) {
                tick();
                render();
            }
            
        }
        stop();
    }
    
    
    
}
