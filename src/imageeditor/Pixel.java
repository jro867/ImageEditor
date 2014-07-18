
package imageeditor;

/**
 *
 * @author nuxer
 */

public class Pixel {
    
    int[] pixel = new int[3];
    
    public Pixel(int[] colors){
        pixel[0] = colors[0];
        pixel[1] = colors[1];
        pixel[2] = colors[2];
    }
    
    public int redValue(){
        return pixel[0];
    }
    
    public int greenValue(){
        return pixel[1];
    }
    
    public int blueValue(){
        return pixel[2];
    }
    
    public void setValueForPixel(int val){
        pixel[0] = val;
        pixel[1] = val;
        pixel[2] = val;
    }
    public String toString(){
        return "pixel value: " + pixel[0] +" " + pixel[1] +" "+ pixel[2];
    }
    
}
