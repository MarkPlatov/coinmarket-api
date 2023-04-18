package com.mark.coinmarketapi.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest implements Serializable {

    private Integer sourceCmcId;
    private Integer destinationCmcId;

}
