package com.beard.entity;

import java.util.Objects;

public class PriceOffers {

    private Long priceOffersId;
    private String name;
    private Integer price;

    private PriceOffers(Builder builder) {
        this.priceOffersId = builder.priceOffersId;
        this.name = builder.name;
        this.price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getPriceOffersId() {
        return priceOffersId;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceOffers that = (PriceOffers) o;
        return Objects.equals(priceOffersId, that.priceOffersId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceOffersId, name, price);
    }

    @Override
    public String toString() {
        return "PriceOffersRepository{" +
                "priceOffersId=" + priceOffersId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private Long priceOffersId;
        private String name;
        private Integer price;

        private Builder() {
        }

        public PriceOffers build() {
            return new PriceOffers(this);
        }

        public Builder withPriceOffersId(Long priceOffersId) {
            this.priceOffersId = priceOffersId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(Integer price) {
            this.price = price;
            return this;
        }
    }
}
