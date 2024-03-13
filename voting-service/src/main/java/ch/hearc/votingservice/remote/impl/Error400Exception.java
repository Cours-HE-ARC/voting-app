package ch.hearc.votingservice.remote.impl;

public class Error400Exception extends RuntimeException{

    private String message;
    public Error400Exception(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
