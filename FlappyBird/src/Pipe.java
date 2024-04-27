import java.awt.*;

public class Pipe{
    private int posX;
    private int posY;
    private int width;
    private int heigth;
    private Image image;
    private int velocityX;
    private boolean passed;


    public Pipe(int posX, int posY, int width, int heigth, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.heigth = heigth;
        this.image = image;
        this.velocityX = -3;
        this.passed = false;
    }
    public void setPosX(int posX){
        this.posX = posX;
    }
    public void setPosY(int posY){
        this.posY = posY;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeigth(int heigth){
        this.heigth = heigth;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public void setVelocityY(int velocityY){
        this.velocityX = velocityY;
    }
    public void setPassed(boolean passed) { this.passed = passed; }
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeigth(){
        return this.heigth;
    }
    public Image getImage(){
        return this.image;
    }
    public int getVelocityY(){
        return this.velocityX;
    }
    public boolean isPassed() { return passed; }
}
