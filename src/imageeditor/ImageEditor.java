/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageeditor;
import java.io.*;

/**
 *
 * @author nuxer
 */
public class ImageEditor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File input = null;
        File output = null;
        String transformation = null;
        Transformation trans = null;
        ProcessFile process = null;
        int paramtNum = args.length;
        
        if (paramtNum == 0){
            System.out.println("There were not files being passed");
        }else{
            switch(paramtNum){
                case 3:
                    input = new File(args[0]);
                    output = new File(args[1]);
                    transformation = args[2];
                    
                    process = new ProcessFile(input);
                    trans = new Transformation(transformation,process);
                    process.returnNewImage(process.getPPMList(), output);
                    
                    break;
                default:
                      System.out.println("You have to pass the following for the program to actually run: "
                            + "\n"+ "inputFile1, outputFile2, TransformationType(INVERT,GRAYSCALE,EMBOSS, BLUR)");
                    break;
            }
            
        }

  
           
    }
    
}
