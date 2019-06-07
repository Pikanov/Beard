package com.beard.service.impl;

import com.beard.entity.PriceOffers;
import com.beard.repository.PriceOffersRepository;
import com.beard.service.PriceOffersService;

import java.util.List;

public class PriceOffersServiceImpl implements PriceOffersService {

    private PriceOffersRepository priceOffersRepository;

    public PriceOffersServiceImpl(PriceOffersRepository priceOffersRepository) {
        this.priceOffersRepository = priceOffersRepository;
    }

    @Override
    public List<PriceOffers> findAll() {
        return priceOffersRepository.findAll();
    }

    @Override
    public PriceOffers findById(Long id) {
        return priceOffersRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return priceOffersRepository.deleteById(id);
    }

    @Override
    public boolean add(PriceOffers priceOffers) {
        return priceOffersRepository.add(priceOffers);
    }

    @Override
    public boolean update(PriceOffers priceOffers) {
        return priceOffersRepository.update(priceOffers);
    }
}
