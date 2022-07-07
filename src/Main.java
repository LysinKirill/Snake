import Vectors.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Main extends JComponent implements KeyListener, ActionListener {
    static int fruitCounter = 0;
    static long gameTicks = 0;
    static Tile[][] tiles = new Tile[19][11];
    static boolean[] pressedKeys = new boolean[1000];
    public static JFrame frame = new JFrame("Test");
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension dimension = toolkit.getScreenSize();
    static int width = (int)(dimension.width);
    static int height = (int)(dimension.height*0.98);
    Timer timer = new Timer(0, this);
    public static float unit = width/1000f;
    static Snake snake;

    public static void main(String[] args){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                tiles[i][j] = new Tile(new Vec2d(i, j), (byte)0, 50*unit);
            }
        }
        snake =  new Snake(tiles.length/2, tiles[0].length/2);

        //snake.move();
        //snake.move();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main simulation = new Main();
                frame.setSize(width, height);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                frame.add(simulation);
                frame.addKeyListener(simulation);
            }
        });

        while(true){
            Main.controls();

            if(gameTicks % (128 * 0.5) == 0){
                if(!snake.move()){
                    System.out.println("Game Over!   Your score: " + snake.snakeLength);
                    new Scanner(System.in).next();
                }
            }

            if(gameTicks % (128 * 4) == 0){
                if(fruitCounter < 3){
                    spawnFruit();
                }
            }


            try{
                TimeUnit.MICROSECONDS.sleep((int)(7812));}catch(InterruptedException e){//128 исполнений цикла за секнду
                e.getStackTrace();
            }
            gameTicks++;
        }
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Tile tile = new Tile(new Vec2d(0, 0), (byte)2, 60);
        //tile.draw(g2d);
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                tiles[i][j].draw(g2d);
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.drawString("Your score:  " + snake.snakeLength, (int)(0.9 * width), (int)(0.05 * height));
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
        //System.out.println(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void controls(){
        if(pressedKeys[87]){  // w pressed
            snake.dir = 1;
        }
        if(pressedKeys[83]){  // s pressed
            snake.dir = 3;
        }
        if(pressedKeys[65]){  // a pressed
            snake.dir = 2;
        }
        if(pressedKeys[68]){  // d pressed
            snake.dir = 0;
        }
    }
    static Ellipse2D.Double getCircle(double posX, double posY, float r){
        return new Ellipse2D.Double(posX - r, posY - r, 2 * r, 2 * r);
    }
    static Ellipse2D.Double getCircle(Vec2d pos, float r){
        return getCircle(pos.getX(), pos.getY(), r);
    }

    public static boolean inBounds(Vec2d coords){
        int x = (int)coords.getX();
        int y = (int)coords.getY();
        return ((x >= 0) && (x < tiles.length) && (y >= 0) && (y < tiles[0].length));
    }

    public static boolean inBounds(int x, int y){
        return ((x >= 0) && (x < tiles.length) && (y >= 0) && (y < tiles[0].length));
    }

    public static void spawnFruit(){
        while(true){
            int x = (int)(Math.random() * tiles.length);
            int y = (int)(Math.random() * tiles[0].length);
            if(Main.tiles[x][y].isEmpty()){
                fruitCounter++;
                Main.tiles[x][y].type = 3;
                return;
            }
        }
    }
}