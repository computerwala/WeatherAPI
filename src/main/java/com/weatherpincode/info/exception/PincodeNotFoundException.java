package com.weatherpincode.info.exception;

public class PincodeNotFoundException extends RuntimeException {
    public PincodeNotFoundException(String pincode) {
        super("Pincode not found: " + pincode);
    }
}