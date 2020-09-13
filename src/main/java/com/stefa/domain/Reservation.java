package com.stefa.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Reservation {
    private int id;
    private String clientName;
    private Date checkInDate;
    private Date checkOutDate;
    private int numberOfGuests;
}
