package com.mark.coinmarketapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseStatus {

    @Schema(example = "2023-04-15T10:47:58.520Z")
    private LocalDateTime timestamp;
    @Schema(example = "0")
    private Integer errorCode;
    @Schema(example = "null")
    private String errorMessage;
    @Schema(example = "31")
    private Integer elapsed;
    @Schema(example = "1")
    private Integer creditCount;
    @Schema(example = "null")
    private String notice;

}
