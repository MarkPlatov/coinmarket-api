package com.mark.coinmarketapi.dto.externalresponse;

import com.mark.coinmarketapi.dto.ResponseStatus;
import lombok.Data;

@Data
public abstract class BasicResponse<T> {

    private ResponseStatus status;

    private T data;


}
