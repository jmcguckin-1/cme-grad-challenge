package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManipulation {
    private String filename;
    private BufferedReader br;
    private BufferedReader br1;
    public FileManipulation(String filename) throws FileNotFoundException {
        this.filename = filename;
        br = new BufferedReader(new FileReader(filename));
        br1 = new BufferedReader(new FileReader(filename));
    }

    public boolean insertUnique(String s){
        try {
            String line = br.readLine();
            while (line != null) {
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

    public void loadResults(ArrayList<String> a){
        try {
            String line = br1.readLine();
            while (line != null) {
                a.add(line);
                line = br1.readLine();
            }
            br1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
