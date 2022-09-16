package com.mataases.security.domain.dto;

import lombok.Data;

@Data
public class TokenRedisDto {
    String username;
    Long dateTime;
}