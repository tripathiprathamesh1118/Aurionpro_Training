package com.aurionpro.creation.builder.model;

public class SmartphoneDirector {
    private ISmartphoneBuilder builder;

    public SmartphoneDirector(ISmartphoneBuilder builder) {
        this.builder = builder;
    }

    public Smartphone constructSmartphone(String screenSize, String storage, String battery) {
        builder.buildOperatingSystem();
        builder.buildScreenSize(screenSize);
        builder.buildStorage(storage);
        builder.buildBattery(battery);
        return builder.getSmartphone();
    }
}