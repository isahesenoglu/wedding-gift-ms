package com.example.weddinggift.enums;



public enum CredentialDataStatus {
    ACTIVE(1),
    DE_ACTIVE(0);

    private final int dataStatus;

   private CredentialDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int getDataStatus() {
        return dataStatus;
    }
}
