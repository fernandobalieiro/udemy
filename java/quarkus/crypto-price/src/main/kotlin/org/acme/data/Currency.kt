package org.acme.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Currency(
        val id: String,

        val symbol: String,

        val name: String,

        @JsonProperty("name_id")
        val nameId: String,

        val rank: Int,

        @JsonProperty("price_usd")
        val priceUsd: String,

        @JsonProperty("percent_change_24h")
        val percentChange24h: String,

        @JsonProperty("percent_change_1h")
        val percentChange1h: String,

        @JsonProperty("percent_change_7d")
        val percentChange7d: String,

        @JsonProperty("market_cap_usd")
        val marketCapUsd: String,

        val volume24: String,

        @JsonProperty("volume24_native")
        val volume24Native: String,

        @JsonProperty("csupply")
        val cSupply: String,

        @JsonProperty("price_btc")
        val priceBtc: String,

        @JsonProperty("tsupply")
        val tSupply: String,

        @JsonProperty("msupply")
        val mSupply: String
)
