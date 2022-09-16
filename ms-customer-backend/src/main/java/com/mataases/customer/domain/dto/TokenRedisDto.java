package com.mataases.customer.domain.dto;

import lombok.Data;

@Data
public class TokenRedisDto {
    String username;
    Long dateTime;
}