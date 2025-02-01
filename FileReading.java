package com.aurionpro;

import java.io.*;
import java.util.Scanner;

public class FileReading {
    public static void main(String[] args) {
        Scanner userinput = new Scanner(System.in);

        // user input for directory and name of the file
        System.out.print("Enter the name of the file: ");
        String filename = userinput.nextLine();
        
        File file = new File(filename);
        
        // Check if file exists
        if (!file.exists()) {
            System.out.println("file doesn't exist.");
            return;
        }
        
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++; 
                charCount += line.length(); 
                wordCount += line.split("\\s+").length; 
            }
            
            System.out.println("File stats are :");
            System.out.println("Lines within the file are :" + lineCount);
            System.out.println("Words within the file are :" + wordCount);
            System.out.println("Characters within the file are : " + charCount);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
