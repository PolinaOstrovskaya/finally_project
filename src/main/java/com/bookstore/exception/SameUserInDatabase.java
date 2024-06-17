package com.bookstore.exception;

public class SameUserInDatabase extends Throwable {
    private final String message;

    public SameUserInDatabase(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Registration problem! We already have user with login: " + message;
    }
}
