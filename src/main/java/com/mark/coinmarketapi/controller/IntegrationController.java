package com.mark.coinmarketapi.controller;

import com.mark.coinmarketapi.facade.CoinmarketServiceFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
@RequiredArgsConstructor
public class IntegrationController {

    private final CoinmarketServiceFacade coinmarketServiceFacade;

    @GetMapping(value = "/key-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Quotes latest")
    @ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> getKeyInfo() {
        return ResponseEntity.ok(coinmarketServiceFacade.keyInfo());
    }

    @GetMapping(value = "/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Quotes latest")
    @ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> getQuotes(@RequestParam Integer cmcId, @RequestParam Integer convertCmcId) {
        return ResponseEntity.ok(coinmarketServiceFacade.getQuotes(cmcId, convertCmcId));
    }

    @GetMapping(value = "/update-coins-cache")
    @Operation(summary = "Refresh coins cache")
    @ApiResponses({
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<?> updateCoinsCache() {
        coinmarketServiceFacade.updateCoinsCache();
        return ResponseEntity.ok().build();
    }
}
