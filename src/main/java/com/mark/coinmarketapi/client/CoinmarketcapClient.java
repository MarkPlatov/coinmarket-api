package com.mark.coinmarketapi.client;

import com.mark.coinmarketapi.config.CoinmarketFeignConfig;
import com.mark.coinmarketapi.dto.externalresponse.CryptocurrencyMapResponse;
import com.mark.coinmarketapi.dto.externalresponse.KeyInfoResponse;
import com.mark.coinmarketapi.dto.externalresponse.QuotesLatestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "coin-market-cap-client", url = "${application.clients.coin-market-cap.url}", configuration = CoinmarketFeignConfig.class)
public interface CoinmarketcapClient {

    @GetMapping("/v2/cryptocurrency/quotes/latest")
    QuotesLatestResponse quotesLatest(
        @RequestParam Integer id,
        @RequestParam(name = "convert_id") Integer convertId,
        @RequestParam String aux,
        @RequestParam(name = "skip_invalid") boolean skipInvalid
    );

    @GetMapping("/v1/cryptocurrency/map")
    CryptocurrencyMapResponse cryptocurrencyMap(
        @RequestParam(name = "listing_status") String listingStatus,
        @RequestParam String aux,
        @RequestParam Integer start,
        @RequestParam Integer limit
    );

    @GetMapping("/v1/fiat/map")
    CryptocurrencyMapResponse fiatMap(
        @RequestParam(name = "include_metals") boolean includeMetals,
        @RequestParam Integer start,
        @RequestParam Integer limit
    );

    @GetMapping("/v1/key/info")
    KeyInfoResponse keyInfo();
}
