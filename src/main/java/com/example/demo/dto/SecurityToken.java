package com.example.demo.dto;

public record SecurityToken (
    String token,
    String message
) {}