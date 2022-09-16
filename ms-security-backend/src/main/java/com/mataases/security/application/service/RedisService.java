package com.mataases.security.application.service;

public interface RedisService {
    public void addToken( String token);
    public boolean existToken( String token);
}
