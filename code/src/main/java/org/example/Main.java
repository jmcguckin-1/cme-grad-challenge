package org.example;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    private static Logger Logs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
        Pattern my_pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher my_match = my_pattern.matcher(sequence);
        boolean check = my_match.find();
        if (check){
            Logs.log(Level.WARNING, "Incorrect sequence - special characters included");
            throw new Exception("Sequence should not contain special characters");
        }
        else if (sequence.matches(".*[a-z].*")){
            Logs.log(Level.WARNING, "Incorrect sequence - letters included");
            throw new Exception("Sequence should not contain letters");
        }
        else if (sequence.contains(" ")){
            Logs.log(Level.WARNING, "Incorrect sequence - blank space included");
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
                            Logs.log(Level.INFO, "Prime Number Found and Added to Cache");
                            if (f.createNewFile()){
                                bw.write(substr);
                                bw.newLine();
                                Logs.log(Level.INFO, "File Created for First Time");
                            }
                            else{
                                bw.write(substr);
                                bw.newLine();
                                Logs.log(Level.INFO, "Appending numbers to file");
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
            Logs.log(Level.INFO, "Number between 0 and 1 - not prime");
            return false;
        }
        if (a == 2){
            Logs.log(Level.INFO, "Number = 2 - not prime");
            return true;
        }
        for (int i = 2; i<a; i++){
            if (a % i == 0) {
                Logs.log(Level.INFO, "Number divisible by value other than 1 or itself - not prime");
                return false;
            }
        }
        Logs.log(Level.INFO, "Number is prime");
        return true;
    }
}