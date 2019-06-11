package com.beard.repository.impl;

import com.beard.entity.PriceOffers;
import com.beard.repository.PriceOffersRepository;
import com.beard.util.ConnectorDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceOffersRepositoryImpl implements PriceOffersRepository {
    private final static Logger LOGGER = Logger.getLogger(PriceOffersRepositoryImpl.class);

    private ConnectorDB connectorDB;

    private static final String FIND_ALL = "SELECT * FROM price_offers";

    private static final String FIND_BY_ID = "SELECT * FROM beard.price_offers " +
            "WHERE price_offers_id=?";

    public PriceOffersRepositoryImpl() {
        this.connectorDB = new ConnectorDB();
    }

    @Override
    public List<PriceOffers> findAll() {
        List<PriceOffers> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(priceOffersBuilder(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findAll price_offers");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public PriceOffers findById(Long id) {
        PriceOffers result = null;
        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = priceOffersBuilder(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findAll price_offers");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new RuntimeException();
    }

    @Override
    public boolean add(PriceOffers priceOffers) {
        throw new RuntimeException();
    }

    @Override
    public boolean update(PriceOffers priceOffers) {
        throw new RuntimeException();
    }

    private PriceOffers priceOffersBuilder(ResultSet rs) throws SQLException {
        return PriceOffers.builder()
                .withPriceOffersId(rs.getLong("price_offers_id"))
                .withName(rs.getString("name"))
                .withPrice(rs.getInt("price"))
                .build();
    }
}
