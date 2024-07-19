package org.example;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a username");
        String username = sc.nextLine();
        System.out.println("Enter a sequence of numbers");
        String sequence = sc.nextLine();
        System.out.println("Username:" + username);
        System.out.println(findPrimeNumbers(sequence));
    }

    public static String findPrimeNumbers(String sequence) throws Exception {
        ArrayList<String> finalNumbers = new ArrayList<>();
        if (sequence.matches(".*[a-z].*")){
            throw new Exception("Sequence should not contain letters");
        }
        else if (sequence.contains(" ")){
            throw new Exception("Sequence should not contain blank spaces");
        }
        else{
            File f = new File("primenumbers.txt");
            FileWriter fw = new FileWriter("primenumbers.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < sequence.length(); i++) {
                for (int j = 0; j < sequence.length() + 1; j++) {
                    if (i < j){
                        String substr = sequence.substring(i, j);
                        int currentPrime = Integer.parseInt(substr);
                        if (isPrime(currentPrime)){
                            finalNumbers.add(substr);
                            if (f.createNewFile()){
                                bw.write(substr);
                                bw.newLine();
                            }
                            else{
                                bw.write(substr);
                                bw.newLine();
                            }
                        }
                    }

                }
            }
            bw.close();
        }
        String message = "Prime Numbers in sequence: ";
        for (int i=0; i<finalNumbers.size()-1; i++){
            message += finalNumbers.get(i) + ",";
        }
        message += finalNumbers.getLast();
        return message;
    }

    public static boolean isPrime(int a){
        if (a == 1 || a == 0){
            return false;
        }
        if (a == 2){
            return true;
        }
        for (int i = 2; i<a; i++){
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }
}