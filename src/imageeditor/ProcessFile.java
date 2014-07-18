/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imageeditor;

import java.util.*;
import java.io.*;

/**
 *
 * @author nuxer
 */
public class ProcessFile {
    
    Scanner scan = null;
    List<Integer> PPMdata = new ArrayList<Integer>();
    List<String> PPMHeaders = new ArrayList<String>();
    
    public ProcessFile(File file){
        try{
            scan = new Scanner(file);
            while(scan.hasNext()){
                if(!scan.hasNextInt()){
//                    System.out.println("#");
                   PPMHeaders.add(scan.next());

                }else{
                    PPMdata.add(scan.nextInt()); 
                }                
            }            
//            for(int i=0; i< PPMdata.size();i++){
//                System.out.println(PPMdata.get(i));
//            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if (scan != null) {
		scan.close();
            }
	}
        
    }
    
    public ProcessFile(Pixel [][]image){
        
    }
    
    public List<Integer> getPPMList(){
        return PPMdata;
    }
    
    public File returnNewImage(List<Integer> chengedArray, File outputFile){
        
        Writer writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile))); 
            for(int i = 0; i < chengedArray.size(); i++){
                writer.write(chengedArray.get(i)+"\n");
            }   
            
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                writer.close();
            }catch(Exception ex){}
        }
        
        return outputFile;
        
    }
    
    public int size(){
        return PPMdata.size();
    }
    
    public Integer get(int index){
//        System.out.println("PPMdata.get(index):: "+PPMdata.get(index));
        int value = 0;
        try{
            value = PPMdata.get(index);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return value;
    }
    
    public void set(int index, int value){
        
        PPMdata.set(index,value);
    }
    
    
}
