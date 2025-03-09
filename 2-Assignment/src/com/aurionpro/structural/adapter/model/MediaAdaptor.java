package com.aurionpro.structural.adapter.model;

public class MediaAdaptor implements IMediaPlayer {

    private IAdvancedMediaPlayer advancedPlayer;

    public MediaAdaptor(String fileType) {
        if (fileType.equalsIgnoreCase("AVI")) {
            advancedPlayer = new AVIPlayer();
        } else if (fileType.equalsIgnoreCase("MP4")) {
            advancedPlayer = new MP4Player();
        }
    }

    @Override
    public void play(String fileType, String fileName) {
        if (fileType.equalsIgnoreCase("AVI")) {
            advancedPlayer.playAVI(fileName);
        } else if (fileType.equalsIgnoreCase("MP4")) {
            advancedPlayer.playMP4(fileName);
        }
    }
}