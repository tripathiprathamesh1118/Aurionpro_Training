package com.aurionpro.structural.adapter.model;

public class AVIPlayer implements IAdvancedMediaPlayer {

    @Override
    public void playMP4(String fileName) {
        // Not supported
    }

    @Override
    public void playAVI(String fileName) {
        System.out.println("Playing AVI: " + fileName);
    }
}