package org.example;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a username");
        String username = sc.nextLine();
        System.out.println("Enter a sequence of numbers");
        String sequence = sc.nextLine();
        if (!sequence.isEmpty() || !sequence.matches("[a-zA-Z]+")) {
            ArrayList<String> numbers = findPrimeNumbers(sequence);
            System.out.println("Username:" + username);
            System.out.print("Prime Numbers in sequence: ");
            for (int i=0; i<numbers.size()-1; i++){
                System.out.print(numbers.get(i) + ",");
            }
            System.out.print(numbers.getLast());

        }
        else{
            System.out.println("Sequence should not be empty and should only have numbers present");
        }
    }

    public static ArrayList<String> findPrimeNumbers(String sequence) throws IOException {
        ArrayList<String> finalNumbers = new ArrayList<>();
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
                            System.out.println("file created!");
                            bw.write(substr);
                            bw.newLine();

                        }
                        else{
                            System.out.println("file appended to!");
                            bw.write(substr);
                            bw.newLine();
                        }
                    }
                }

            }
        }
        bw.close();
        System.out.println(finalNumbers.size());
        return finalNumbers;
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