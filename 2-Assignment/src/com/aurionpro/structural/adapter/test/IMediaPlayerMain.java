package com.aurionpro.structural.adapter.test;

import com.aurionpro.structural.adapter.model.IMediaPlayer;
import com.aurionpro.structural.adapter.model.MediaAdaptor;

import java.util.Scanner;

public class IMediaPlayerMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileType = "";
        String fileName = "";

        while (true) {
            System.out.print("Enter the file type (MP4/AVI): ");
            fileType = scanner.nextLine().trim().toUpperCase();
            
            if (fileType.equals("MP4") || fileType.equals("AVI")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter (MP4/AVI)");
            }
        }

        while (true) {
            System.out.print("Enter the file name (with extension ( .mp4 or .avi): ");
            fileName = scanner.nextLine().trim();

            if (fileName.isEmpty()) {
                System.out.println("File name cannot be empty.");
            } else if (!fileName.matches("^[a-zA-Z0-9_\\-]+\\.(mp4|avi)$")) {
                System.out.println("Invalid file name.");
            } else if (!fileName.toLowerCase().endsWith("." + fileType.toLowerCase())) {
                System.out.println("File extension does not match the selected file type (" + fileType + "). Please enter a valid file name.");
            } else {
                break;
            }
        }

        IMediaPlayer player = new MediaAdaptor(fileType);
        player.play(fileType, fileName);

        scanner.close();
    }
}