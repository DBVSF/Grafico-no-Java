
package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 160;
    private final int HEIGHT = 120;
    private final int SCALE = 3;
    
    
    
    public Game (){
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
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
        
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        
    }
    
    public void tick(){
        
    }
    public void render(){
        
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
        
    }
    
    
    
}
