package com.mark.coinmarketapi.enums;

import lombok.Getter;

@Getter
public enum ListingStatus {

    ACTIVE("active"),
    INACTIVE("inactive"),
    UNTRACKED("untracked"),
    ;

    private final String value;

    ListingStatus(String value) {
        this.value = value;
    }

}
