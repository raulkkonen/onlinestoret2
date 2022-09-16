package com.mataases.shopping.domain.dto;

import lombok.Data;

@Data
public class TokenRedisDto {
    String username;
    Long dateTime;
}