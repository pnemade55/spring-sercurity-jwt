package com.pn.springsercurityjwt.models;

/**
 * created by : pnema
 * on 6/30/2021
 */
public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
