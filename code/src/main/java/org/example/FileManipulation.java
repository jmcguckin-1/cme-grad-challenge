package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManipulation {
    private String filename;
    private BufferedReader br;
    public FileManipulation(String filename) throws FileNotFoundException {
        this.filename = filename;
        br = new BufferedReader(new FileReader(filename));
    }

    public boolean insertUnique(String s){
        try {
            String line = br.readLine();
            while (line != null) {
                System.out.println("Line:" + line + ", " + "S:" + s);
                if (line.equals(s)){
                    return false;
                }
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void loadResults(){

    }
}
