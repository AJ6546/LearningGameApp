package com.example.gameapp.model;

public class MemoryCard {
    boolean isFaceUP =false, isMatched=false;
    int identifier;

    public MemoryCard(boolean isFaceUP, boolean isMatched, int identifier) {
        this.isFaceUP = isFaceUP;
        this.isMatched = isMatched;
        this.identifier = identifier;
    }

    public boolean isFaceUP() {
        return isFaceUP;
    }

    public void setFaceUP(boolean faceUP) {
        isFaceUP = faceUP;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }


}
