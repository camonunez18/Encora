package com.example.practice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Conteo {
    private long count;
    private double percentage;
}
