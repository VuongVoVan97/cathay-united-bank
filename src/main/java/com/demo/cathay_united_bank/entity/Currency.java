package com.demo.cathay_united_bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Currency")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {


    @Id
    private String code;

    private String description;

    private String rate;

    private double rateFloat;

    private String  updateDate;
}
