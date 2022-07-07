import Vectors.Vec2d;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Tile {
    public Vec2d cords;
    private Vec2d pos;
    byte type;
    private Shape border;
    float size;

    public Tile(Vec2d cords, byte type, float size){
        this.cords = cords;
        this.type = type;
        this.size = size;
        this.pos = getPos(Main.unit * 50);
        this.border = getTileShape(pos, size);
    }

    public Tile(Vec2d cords, float size){
        this.cords = cords;
        this.size = size;
        this.type = 0;
        this.pos = getPos(Main.unit * 50);
        this.border = getTileShape(pos, size);
    }

    public Tile(Vec2d cords){
        this.cords = cords;
        this.size = Main.unit * 15;
        this.type = 0;
        this.pos = getPos(Main.unit * 50);
        this.border = getTileShape(pos, size);
    }



    public void draw(Graphics2D g2d){
        g2d.setColor(new Color(114, 112, 112, 100));
        g2d.draw(border);
        switch(type){
            case 0:    //    empty tile
                g2d.setColor(new Color(114, 112, 112, 100));
                g2d.fill(border);
                //g2d.setColor(Color.BLACK);     ???
                break;
            case 1:    //    snake body
                g2d.setColor(new Color(114, 112, 112, 100));
                g2d.fill(border);
                Shape body = Main.getCircle(pos, size * 0.4f);
                g2d.setColor(new Color(87, 217, 132));
                g2d.fill(body);
                g2d.setColor(Color.BLACK);
                g2d.draw(body);
                break;
            case 2:    //    snake head
                g2d.setColor(new Color(114, 112, 112, 100));
                g2d.fill(border);
                Shape head = Main.getCircle(pos, size * 0.4f);
                g2d.setColor(new Color(87, 217, 132));
                g2d.fill(head);
                g2d.setColor(Color.BLACK);
                g2d.draw(head);
                g2d.draw(new Arc2D.Double(new Rectangle2D.Double(pos.getX() - size * 0.25f, pos.getY() - size * 0.25f, size * 0.5f, size * 0.5f), 200, 140, Arc2D.CHORD));
                g2d.draw(Main.getCircle(pos.getX() - size * 0.15f, pos.getY() - size * 0.1f, size * 0.04f));
                g2d.draw(Main.getCircle(pos.getX() + size * 0.15f, pos.getY() - size * 0.1f, size * 0.04f));
                g2d.drawLine((int)(pos.getX() - size * 0.05f), (int)(pos.getY() + size * 0.05f), (int)(pos.getX() - size * 0.02f), (int)(pos.getY() - size * 0.03f));
                g2d.drawLine((int)(pos.getX() + size * 0.05f), (int)(pos.getY() + size * 0.05f), (int)(pos.getX() + size * 0.02f), (int)(pos.getY() - size * 0.03f));
                break;
            case 3:    //    fruit
                g2d.setColor(new Color(114, 112, 112, 100));
                g2d.fill(border);
                g2d.setColor(Color.BLACK);
                g2d.draw(Main.getCircle(pos.getX(), pos.getY() + size * 0.1f, size * 0.3f));
                g2d.draw(Main.getCircle(pos.getX(), pos.getY() - size * 0.2f, size * 0.2f));
                g2d.setColor(new Color(190, 232, 64));
                g2d.fill(Main.getCircle(pos.getX(), pos.getY() + size * 0.1f, size * 0.3f));
                g2d.fill(Main.getCircle(pos.getX(), pos.getY() - size * 0.2f, size * 0.2f));
                g2d.setColor(new Color(89, 48, 10));
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(new Arc2D.Double(new Rectangle2D.Double(pos.getX(), pos.getY() - size * 0.52f, size * 0.4f, size * 0.6f), 110, 60, Arc2D.OPEN));
                g2d.setStroke(new BasicStroke(1));
        }
    }

    private static Polygon getTileShape(Vec2d pos, float size){
        int[] xs = new int[]{
                (int)(pos.getX() - size * 0.5f),
                (int)(pos.getX() - size * 0.4f),
                (int)(pos.getX() + size * 0.4f),
                (int)(pos.getX() + size * 0.5f),
                (int)(pos.getX() + size * 0.5f),
                (int)(pos.getX() + size * 0.4f),
                (int)(pos.getX() - size * 0.4f),
                (int)(pos.getX() - size * 0.5f)
        };
        int[] ys = new int[]{
                (int)(pos.getY() - size * 0.4f),
                (int)(pos.getY() - size * 0.5f),
                (int)(pos.getY() - size * 0.5f),
                (int)(pos.getY() - size * 0.4f),
                (int)(pos.getY() + size * 0.4f),
                (int)(pos.getY() + size * 0.5f),
                (int)(pos.getY() + size * 0.5f),
                (int)(pos.getY() + size * 0.4f)
        };
        return new Polygon(xs, ys, 8);
    }

    private Vec2d getPos(double k){
        //return new Vec2d((cords.getX() + 1)* k,(cords.getY() + 1) * k);
        return new Vec2d((cords.getX() + 0.55)* k,(cords.getY() + 0.55) * k);

    }

    public boolean isEmpty(){return (type == 0);}
    public boolean hasFruit(){return (type == 3);}
}
