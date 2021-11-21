package com.shop.constant;

public enum Role {
    ADMIN("관리자", 100), USER("유저", 101);

    private String krName;
    private int codeNumber;

    Role(String krName, int codeNumber) {
        this.krName = krName;
        this.codeNumber = codeNumber;
    }

    public String getKrName() {
        return krName;
    }

    public int getCodeNumber() {
        return codeNumber;
    }
}
