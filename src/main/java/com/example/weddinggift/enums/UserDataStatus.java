package com.example.weddinggift.enums;



public enum UserDataStatus {
    ACTIVE(1),
    DE_ACTIVE(0);

    private final int dataStatus;

   private UserDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int getDataStatus() {
        return dataStatus;
    }
}
