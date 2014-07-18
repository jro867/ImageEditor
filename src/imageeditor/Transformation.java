/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageeditor;

/**
 *
 * @author nuxer
 */

import java.util.*;

public class Transformation {
    String transformation;
    int MAX_COLOR_VALUE = 250;
    
    public Transformation(String transformation, ProcessFile PPMList){
        
        this.transformation = transformation;
        
        switch(this.transformation){
            case "INVERT":
                System.out.println("it got into invert");
                invert(PPMList);
                break;
            case "GRAYSCALE":
                System.out.println("grayscale");
                grayScale(PPMList);
                break;
            case "EMBOSS":
                emboss(PPMList);
                break;
            case "BLUR":
                break;
        
        }
    }
    
    public ProcessFile invert(ProcessFile PPMList){
        for(int i=0; i < PPMList.size(); i++){
//            System.out.println(PPMList.size() );
            if(PPMList.get(i) < MAX_COLOR_VALUE){
               int oppositeVal = MAX_COLOR_VALUE - PPMList.get(i);
               PPMList.set(i, oppositeVal);
            }
        }
        
        return PPMList;
            
    }
    
    public ProcessFile emboss(ProcessFile PPMList){
        
            int pixelRange = 2;
            int pixelRangeCounter = 0;
            
            int[] pixelHolder = new int[3];
            Pixel pixel;
            List<Pixel> pixelArray = new ArrayList<>();
            List<Integer>changedPPMList = new ArrayList<>();
            
            for(int i=3; i < PPMList.size(); i++){
                
                    pixelHolder[pixelRangeCounter] = PPMList.get(i);
                    
                     if(pixelRangeCounter == pixelRange){
                         pixelRangeCounter = 0;
                         pixel = new Pixel(pixelHolder);
                         pixelArray.add(pixel);
                    }else{
                        pixelRangeCounter++; 
                     }
                     
                
            } 
        
        
        
        Pixel[][] image = new Pixel[PPMList.get(1)][PPMList.get(0)];
        System.out.println("Width: " + PPMList.get(0) + " -- Height: " + PPMList.get(1));
        int grabedPixel = 0;
        int magicEmbossNumber = 128;
        
        for(int i = 0; i < PPMList.get(1); i++){
            for(int z = 0; z < PPMList.get(0); z++){
                
//                System.out.println("z=" + z + "  i="+i+ "   " +PPMList.get(1) + ":"+PPMList.get(0));
                
                if(grabedPixel < pixelArray.size()-1){                    
                    image[i][z] = pixelArray.get(grabedPixel);
                    grabedPixel++;
                }
                
                
                if(i != 0 && z != 0){
//                        System.out.println("this is i: " + i + " and this is z:  " + z);
//                        System.out.println("pixel value " + pixelArray.get(grabedPixel).toString());
//                        System.out.println("pixel to substract from:: " + image[i-1][z-1].toString());

                
//                        redDiff  = image[i-1][z-1].redValue() - pixelArray.get(grabedPixel).redValue(); 
//                        greenDiff = image[i-1][z-1].greenValue() - pixelArray.get(grabedPixel).greenValue();
//                        blueDiff  = image[i-1][z-1].blueValue() - pixelArray.get(grabedPixel).blueValue();
                        
                        
                        int redDiff  = pixelArray.get(grabedPixel).redValue() - image[i-1][z-1].redValue();
                        int greenDiff = pixelArray.get(grabedPixel).greenValue() - image[i-1][z-1].greenValue();
                        int blueDiff  = pixelArray.get(grabedPixel).blueValue() - image[i-1][z-1].blueValue();
                       
//                        System.out.println("redDiff " + redDiff);
//                        System.out.println("greeDiff " + greenDiff);
//                        System.out.println("blueDiff " + blueDiff);
                        
                        int maxDiff;
                        if(Math.abs(redDiff) >= Math.abs(greenDiff)){
                            maxDiff = redDiff;
                        }else{
                            if(Math.abs(blueDiff) > Math.abs(greenDiff)){
                                maxDiff = blueDiff;
                            }else{
                                maxDiff = greenDiff;
                            }
                        }
                        int v = magicEmbossNumber + maxDiff;
                        if(v < 0){
                            v = 0;
                        }else{
                                if(v > 255){
                                    v =255;
                                }
                        }
                        
//                        try{
//                        System.out.println("i::: " + i );
//                        System.out.println("z::: " + z );
                        try {
                             image[i][z].setValueForPixel(v);
                        } catch(NullPointerException e) {
//                            System.out.println(i + ": " + z);
                        }                  
//                        
//                        System.out.println("maxDiff:: " + maxDiff);
//                        System.out.println("v:: " + v);
//                        
//                        System.out.println("\\");
                    
                }else{
                        image[i][z].setValueForPixel(128);
                }
               
                
            }
           
        }
        
        int getter; 
        
        for(int i=0; i < image.length; i++){
            for(int x=0; x < image[i].length; x++ ){
                if(image[i][x] != null){
                  getter = image[i][x].redValue();
                    changedPPMList.add(getter);
                    getter = image[i][x].greenValue();
                    changedPPMList.add(getter);
                    getter = image[i][x].blueValue();
                    changedPPMList.add(getter);  
                }
                
                
            }
        }
        
        for(int i=0; i < changedPPMList.size(); i++){
            PPMList.set(i, changedPPMList.get(i));
        }
        
        
        
        
//        for(int w = 0; pixelArray.size(); w++){
//            pixelHolder[pixelRangeCounter] = pixelArray.get(w).pixel[pixelRangeCounter];
//            changedPPMList.add(pixelHolder[pixelRangeCounter]);
//            pixelRangeCounter++;
//            pixelHolder[pixelRangeCounter] = pixelArray.get(w).pixel[pixelRangeCounter];
//            changedPPMList.add(pixelHolder[pixelRangeCounter]);
//            pixelRangeCounter++;
//            pixelHolder[pixelRangeCounter] = pixelArray.get(w).pixel[pixelRangeCounter];
//            changedPPMList.add(pixelHolder[pixelRangeCounter]);
//            pixelRangeCounter = 0;
//            
//            if(w)
//            
//            
//        }


        return PPMList;
    }
    
    public ProcessFile grayScale(ProcessFile PPMList){
            int pixelRange = 2;
            int pixelRangeCounter = 0;
            int totalValueFromColors = 0;
            int averageVal = 0;
            int average = 3;
            int downCounter = 4;
            
//            List<Integer> pixelHolder = new ArrayList<Integer>();
            int[] pixelHolder = new int[3];
            int[] spotToSaveTo = new int[3];
            
            for(int i=0; i < PPMList.size(); i++){
                
                if(PPMList.get(i) <= 255){
                    pixelHolder[pixelRangeCounter] = PPMList.get(i);
                    spotToSaveTo[pixelRangeCounter] = i;
                    
                     if(pixelRangeCounter == pixelRange){
                         pixelRangeCounter = 0;
                        for(int x=0; x < pixelHolder.length; x++){
                            totalValueFromColors += pixelHolder[x];                        
                        }                    
                    averageVal = totalValueFromColors/average;
                        for(int z=0; z < spotToSaveTo.length; z++){
                            PPMList.set(spotToSaveTo[z], averageVal);
                        }
                        totalValueFromColors = 0;
                    }else{
                        pixelRangeCounter++; 
                     }
                     
                }
                
            }            
        return PPMList;
    }
    
    public void blur(ArrayList<Integer> PPMList){
            
    }
    
    
}
