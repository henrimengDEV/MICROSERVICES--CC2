package io.swagger.application;

public class RetrieveTransactionRequest {

    private final String UUID;

    private RetrieveTransactionRequest(String uuid) {
        UUID = uuid;
    }

    public static RetrieveTransactionRequest of(String uuid) {
        return new RetrieveTransactionRequest(uuid);
    }

    public String getUUID() {
        return UUID;
    }
}
