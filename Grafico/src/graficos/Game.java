
package graficos;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

    public static JFrame frame;
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
    
    
    public static void main(String[] args) {
        Game game = new Game();
        
    }
    
    public void run(){
        
    }
    
    
    
}
