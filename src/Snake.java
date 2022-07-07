import Vectors.Vec2d;

import java.util.ArrayList;

public class Snake {
    ArrayList<Vec2d> segments;
    byte dir = 1; // 0 - right; 1 - up; 2 - left; 3 - down
    int snakeLength = 1;
    public Snake(Vec2d coords){
        segments = new ArrayList<>();
        Main.tiles[(int)coords.getX()][(int)coords.getY()].type = 2;
        segments.add(coords);
    }

    public Snake(int x, int y){
        segments = new ArrayList<>();
        Main.tiles[x][y].type = 2;
        segments.add(new Vec2d(x, y));
    }


    public boolean move(){
        int headX = (int)(segments.get(0).getX());
        int headY = (int)(segments.get(0).getY());
        int tailX = (int)(segments.get(segments.size() - 1).getX());
        int tailY = (int)(segments.get(segments.size() - 1).getY());
        switch(dir){
            case 0:
                if(Main.inBounds(headX + 1, headY)){
                    if(!Main.tiles[headX + 1][headY].isEmpty()  &&  !Main.tiles[headX + 1][headY].hasFruit()){
                        return false;
                    }
                    if(snakeLength > 1){
                        Main.tiles[headX][headY].type = 1;
                    } else{Main.tiles[headX][headY].type = 0;}
                    segments.add(0, new Vec2d(headX + 1, headY));

                    if(Main.tiles[headX + 1][headY].hasFruit()){
                        Main.tiles[tailX][tailY].type = 1;
                        Main.fruitCounter--;
                        snakeLength++;
                    }else{
                        segments.remove(segments.size() - 1);
                        Main.tiles[tailX][tailY].type = 0;
                    }

                    Main.tiles[headX + 1][headY].type = 2;

                    return true;
                }
                return false;
            case 1:
                if(Main.inBounds(headX, headY - 1)){
                    if(!Main.tiles[headX][headY - 1].isEmpty()  &&  !Main.tiles[headX][headY - 1].hasFruit()){
                        return false;
                    }
                    if(snakeLength > 1){
                        Main.tiles[headX][headY].type = 1;
                    } else{Main.tiles[headX][headY].type = 0;}
                    segments.add(0, new Vec2d(headX, headY - 1));

                    if(Main.tiles[headX][headY - 1].hasFruit()){
                        Main.tiles[tailX][tailY].type = 1;
                        Main.fruitCounter--;
                        snakeLength++;
                    }else{
                        segments.remove(segments.size() - 1);
                        Main.tiles[tailX][tailY].type = 0;
                    }

                    Main.tiles[headX][headY - 1].type = 2;

                    return true;
                }
                return false;
            case 2:
                if(Main.inBounds(headX - 1, headY)){
                    if(!Main.tiles[headX - 1][headY].isEmpty()  &&  !Main.tiles[headX - 1][headY].hasFruit()){
                        return false;
                    }
                    if(snakeLength > 1){
                        Main.tiles[headX][headY].type = 1;
                    } else{Main.tiles[headX][headY].type = 0;}
                    segments.add(0, new Vec2d(headX - 1, headY));

                    if(Main.tiles[headX - 1][headY].hasFruit()){
                        Main.tiles[tailX][tailY].type = 1;
                        Main.fruitCounter--;
                        snakeLength++;
                    }else{
                        segments.remove(segments.size() - 1);
                        Main.tiles[tailX][tailY].type = 0;
                    }

                    Main.tiles[headX - 1][headY].type = 2;

                    return true;
                }
                return false;
            case 3:
                if(Main.inBounds(headX, headY + 1)){
                    if(!Main.tiles[headX][headY + 1].isEmpty()  &&  !Main.tiles[headX][headY + 1].hasFruit()){
                        return false;
                    }
                    if(snakeLength > 1){
                        Main.tiles[headX][headY].type = 1;
                    } else{Main.tiles[headX][headY].type = 0;}
                    segments.add(0, new Vec2d(headX, headY + 1));

                    if(Main.tiles[headX][headY + 1].hasFruit()){
                        Main.tiles[tailX][tailY].type = 1;
                        Main.fruitCounter--;
                        snakeLength++;
                    }else{
                        segments.remove(segments.size() - 1);
                        Main.tiles[tailX][tailY].type = 0;
                    }
                    Main.tiles[headX][headY + 1].type = 2;




                    return true;
                }
                return false;
        }
        return false;
    }

    public void addSegment(){
        int x = (int)segments.get(snakeLength - 1).getX();
        int y = (int)segments.get(snakeLength - 1).getY();
        
        if(Main.inBounds(x + 1, y)){
            if(Main.tiles[x + 1][y].isEmpty()){
                segments.add(new Vec2d(x + 1, y));
                Main.tiles[x + 1][y].type = 1;
                snakeLength++;
                return;
            }
        }

        if(Main.inBounds(x - 1, y)){
            if(Main.tiles[x - 1][y].isEmpty()){
                segments.add(new Vec2d(x - 1, y));
                Main.tiles[x - 1][y].type = 1;
                snakeLength++;
                return;
            }
        }

        if(Main.inBounds(x, y + 1)){
            if(Main.tiles[x][y + 1].isEmpty()){
                segments.add(new Vec2d(x, y + 1));
                Main.tiles[x][y + 1].type = 1;
                snakeLength++;
                return;
            }
        }

        if(Main.inBounds(x, y - 1)){
            if(Main.tiles[x][y - 1].isEmpty()){
                segments.add(new Vec2d(x, y - 1));
                Main.tiles[x][y - 1].type = 1;
                snakeLength++;
                return;
            }
        }
    }
}
