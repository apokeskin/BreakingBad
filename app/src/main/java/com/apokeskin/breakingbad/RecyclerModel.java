package com.apokeskin.breakingbad;

import android.graphics.Bitmap;

public class RecyclerModel  {
    private String image;
    private String characterName;
    private String CharacterNickName;

    public RecyclerModel(String image, String characterName, String characterNickName) {
        this.image = image;
        this.characterName = characterName;
        CharacterNickName = characterNickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterNickName() {
        return CharacterNickName;
    }

    public void setCharacterNickName(String characterNickName) {
        CharacterNickName = characterNickName;
    }
}
