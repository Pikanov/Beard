package com.beard.repository;

import com.beard.entity.PriceOffers;

import java.util.List;

public interface PriceOffersRepository {

    List<PriceOffers> findAll();

    PriceOffers findById(Long id);

    boolean deleteById(Long id);

    boolean add(PriceOffers priceOffers);

    boolean update(PriceOffers priceOffers);
}
