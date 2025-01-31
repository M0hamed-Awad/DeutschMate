package com.example.deutschmate.models;

public class WordModel {
    private final String englishText;
    private final String germanText;
    private final String soundPath;
    private final String imagePath;

    public WordModel(
            String englishText,
            String germanText,
            String imagePath,
            String soundPath) {
        this.englishText = englishText;
        this.germanText = germanText;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }

    public String getSoundAssetPath(){
        return soundPath;
    }

    public String getImageAssetPath() {
        return imagePath;
    }

    public String getEnglishText (){
        return englishText;
    }

    public String getGermanText () {
        return germanText;
    }

}
