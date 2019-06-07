package com.beard.service;

import com.beard.entity.PriceOffers;

import java.util.List;

public interface PriceOffersService {

    List<PriceOffers> findAll();

    PriceOffers findById(Long id);

    boolean deleteById(Long id);

    boolean add(PriceOffers priceOffers);

    boolean update(PriceOffers priceOffers);
}
