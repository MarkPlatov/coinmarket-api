package com.mark.coinmarketapi.mq;

import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.coinmarketapi.dto.ExchangeRateRequest;
import com.mark.coinmarketapi.dto.ExchangeRateResponse;
import com.mark.coinmarketapi.facade.CoinmarketServiceFacade;
import com.mark.coinmarketapi.model.Coin;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Consumer {


    private final String EXCHANGE_QUEUE = "exchange.rate.";
    private final String COIN_QUEUE = "coin.";
    private final ObjectMapper objectMapper;
    private final CoinmarketServiceFacade facade;

    @JmsListener(destination = EXCHANGE_QUEUE + "request")
    @SendTo(EXCHANGE_QUEUE + "response")
    public ExchangeRateResponse receiveAndForwardExchangeRate(final Message jsonMessage) throws JMSException, JsonProcessingException {
        ExchangeRateRequest request = null;
        if (jsonMessage instanceof TextMessage textMessage) {
            request = objectMapper.readValue(textMessage.getText(), ExchangeRateRequest.class);
        }
        return facade.getExchangeRate(request);

    }

    @JmsListener(destination = COIN_QUEUE + "request")
    @SendTo(COIN_QUEUE + "response")
    public List<Coin> receiveAndForwardCoin(final Message jsonMessage) throws JMSException {
        String namePart = null;
        if (jsonMessage instanceof TextMessage textMessage) {
            namePart = textMessage.getText().replace("\"", "");
        }
        if (StringUtils.isBlank(namePart)) {
            return null;
        }

        return facade.findCoinsByNamePart(namePart);

    }


}
