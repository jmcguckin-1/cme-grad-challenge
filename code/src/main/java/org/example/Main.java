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
        if (username.isEmpty()){
            throw new Exception("Username cannot be blank");
        }
        System.out.println("Enter a sequence of numbers");
        String sequence = sc.nextLine();
        System.out.println("Username:" + username);
        System.out.println(findPrimeNumbers(sequence));
    }

    /**
     * Finds prime numbers in a string sequence
     * @param sequence - the sequence in question
     * @return a string with the prime numbers present in the sequence.
     * @throws Exception - in case of invalid input.
     */
    public static String findPrimeNumbers(String sequence) throws Exception {
        File f = new File("primenumbers.txt");
        boolean numbersPresent = true;
        ArrayList<String> finalNumbers = new ArrayList<>();
        if (f.exists()){
            // loads previous files results
            FileManipulation fm = new FileManipulation("primenumbers.txt");
            fm.loadResults(finalNumbers);
        }
        // updated cache array in case of new sequence.
        ArrayList<String> updatedCache = new ArrayList<>();

        Pattern myPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher myMatch = myPattern.matcher(sequence);
        boolean check = myMatch.find();
        // validation for letters, special characters and blank spaces
        if (check){
            Logs.log(Level.WARNING, "Incorrect sequence - special characters included");
            throw new Exception("Sequence should not contain special characters");
        }
        else if (sequence.matches("[a-zA-Z]+")){
            Logs.log(Level.WARNING, "Incorrect sequence - letters included");
            throw new Exception("Sequence should not contain letters");
        }
        else if (sequence.contains(" ")){
            Logs.log(Level.WARNING, "Incorrect sequence - blank space included");
            throw new Exception("Sequence should not contain blank spaces");
        }
        else{
            // creates the prime numbers file.
            FileWriter fw = new FileWriter("primenumbers.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < sequence.length(); i++) {
                for (int j = 0; j < sequence.length() + 1; j++) {
                    if (i < j){
                        // grabs the strings in sequence.
                        String substr = sequence.substring(i, j);
                        int currentPrime = Integer.parseInt(substr);
                        if (!finalNumbers.contains(substr)){
                            numbersPresent = false;
                            if (isPrime(currentPrime)){
                                finalNumbers.add(substr); // adds to cache
                                updatedCache.add(substr);
                                Logs.log(Level.INFO, "Prime Number Found and Added to Cache");
                                FileManipulation fm = new FileManipulation("primenumbers.txt");
                                // writes unique values to file
                                if (fm.insertUnique(substr) && f.exists()){
                                    bw.write(substr);
                                    bw.newLine();
                                }
                                Logs.log(Level.INFO, "Appending numbers to file");
                                }
                        }
                        else if (sequence.contains(substr)){
                            updatedCache.add(substr);
                        }
                    }
                }
            }
            bw.close();
        }
        String message = "Prime Numbers in sequence: ";
        // deduces which arraylist to use, the pre-populated one or the new one
        ArrayList<String> finalList =  (numbersPresent) ? finalNumbers : updatedCache;
        for (int i=0; i<finalList.size()-1; i++){
            message += finalList.get(i) + ",";
        }
        message += finalList.getLast();

        return message;
    }

    /**
     * deduces whether or not a number is prime
     * @param a - the number in question
     * @return true or false based on a number's prime status
     */
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