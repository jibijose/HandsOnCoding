package com.test.dsalg.javastream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    String trader;
    String city;
    int amount;

}
