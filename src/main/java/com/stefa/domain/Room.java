package com.stefa.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private int number;
    private String type;
    private int capacity;
    private double price;
    private String description;
}