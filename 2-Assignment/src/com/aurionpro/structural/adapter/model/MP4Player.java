package com.aurionpro.structural.adapter.model;

public class MP4Player implements IAdvancedMediaPlayer {

    @Override
    public void playMP4(String fileName) {
        System.out.println("Playing MP4: " + fileName);
    }

    @Override
    public void playAVI(String fileName) {
        // Not supported
    }
}