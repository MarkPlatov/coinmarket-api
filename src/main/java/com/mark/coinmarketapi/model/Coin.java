package com.mark.coinmarketapi.model;

import javax.persistence.*;

import com.mark.coinmarketapi.dto.CoinDto;
import com.mark.coinmarketapi.enums.ListingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cmcId;

    private String name;

    private String symbol;

    private String sign;

    private String slug;

    private boolean isActive;

    private boolean isFiat;

    private ListingStatus listingStatus;


    public static Coin of(CoinDto dto) {
        Coin coin = new Coin();
        coin.setCmcId(dto.getCmcId());
        coin.setName(dto.getName());
        coin.setSymbol(dto.getSymbol());
        coin.setSign(dto.getSign());
        coin.setSlug(dto.getSlug());
        coin.setActive(dto.getIsActive());
        coin.setListingStatus(dto.getListingStatus());
        coin.setFiat(dto.isFiat());
        return coin;
    }
}
