package com.beard.repository.impl;

import com.beard.entity.PriceOffers;
import com.beard.entity.Schedule;
import com.beard.entity.ScheduleItem;
import com.beard.entity.User;
import com.beard.repository.ScheduleItemRepository;
import com.beard.util.ConnectorDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ScheduleItemRepositoryImpl implements ScheduleItemRepository {
    private final static Logger LOGGER = Logger.getLogger(ScheduleItemRepositoryImpl.class);

    private ConnectorDB connectorDB;

    private static final String FIND_ALL = "SELECT * FROM schedule_items " +
            "JOIN users i1 ON (schedule_items.master_id = i1.user_id) " +
            "JOIN users i2 ON (schedule_items.customer_id = i2.user_id) " +
            "JOIN schedules ON schedule_items.schedule_id = schedules.schedule_id " +
            "JOIN price_offers ON schedule_items.price_offers_id = price_offers.price_offers_id";

    private static final String FIND_BY_ID = "SELECT * FROM schedule_items " +
            "JOIN users i1 ON (schedule_items.master_id = i1.user_id) " +
//            "JOIN users i2 ON (schedule_items.customer_id = i2.user_id) " +
            "JOIN schedules ON schedule_items.schedule_id = schedules.schedule_id " +
//            "JOIN price_offers ON schedule_items.price_offers_id = price_offers.price_offers_id " +
            "WHERE schedule_items.schedule_id = ?";

    private static final String ADD = "INSERT INTO schedule_items (date, time, " +
            "master_id, customer_id, free_busy, schedule_id, price_offers_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_BY_ID = "DELETE FROM schedule_items " +
                                               "WHERE schedule_item_id = ?";

    private static final String UPDATE = "UPDATE users SET date=?,time=?, master_id=?, " +
            "customer_id=?, free_busy=?, schedule_id=?, price_offers_id=? " +
            "WHERE schedule_item_id=?";

    private static final String UPDATE_FREE_BUSY = "UPDATE schedule_items SET free_busy=? " +
                                                   "WHERE schedule_item_id =?";

    private static final String FIND_FOR_PAGINATION = "SELECT * FROM schedule_items " +
            "JOIN users i1 ON (schedule_items.master_id = i1.user_id) " +
            "JOIN users i2 ON (schedule_items.customer_id = i2.user_id) " +
            "JOIN schedules ON schedule_items.schedule_id = schedules.schedule_id " +
            "JOIN price_offers ON schedule_items.price_offers_id = price_offers.price_offers_id " +
            "LIMIT ?, ?";

    private static final String GET_NUMBER_OF_ROWS = "SELECT COUNT(schedule_item_id) " +
                                                     "FROM schedule_items";


    public ScheduleItemRepositoryImpl() {
        this.connectorDB = new ConnectorDB();
    }


    @Override
    public List<ScheduleItem> findAll() {
        List<ScheduleItem> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(scheduleItemBuilder(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findAll record");
        }
        return result;
    }

    @Override
    public List<ScheduleItem> findByIdScheduleItemBasic(Long scheduleId) {

        List<ScheduleItem> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, scheduleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(basicScheduleItemBuilder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean add(ScheduleItem scheduleItem) {
        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(ADD);
            ps.setDate(1, Date.valueOf(scheduleItem.getDate()));
            ps.setTime(2, Time.valueOf(scheduleItem.getTime()));
            ps.setLong(3, scheduleItem.getMaster().getUserId());
            ps.setLong(4, scheduleItem.getCustomer().getUserId());
            ps.setBoolean(5, scheduleItem.getFreeBusy());
            ps.setLong(6, scheduleItem.getSchedule().getScheduleId()); //TODO тут падаем
            ps.setLong(7, scheduleItem.getPriceOffers().getPriceOffersId());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query add scheduleItem");
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Long scheduleIdId) {

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setLong(1, scheduleIdId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query deleteById user");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(ScheduleItem scheduleItem) {

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setDate(1, Date.valueOf(scheduleItem.getDate()));
            ps.setTime(2, Time.valueOf(scheduleItem.getTime()));
            ps.setLong(3, scheduleItem.getMaster().getUserId());
            ps.setLong(4, scheduleItem.getCustomer().getUserId());
            ps.setBoolean(5, scheduleItem.getFreeBusy());
            ps.setLong(6, scheduleItem.getSchedule().getScheduleId());
            ps.setLong(7, scheduleItem.getPriceOffers().getPriceOffersId());
            ps.setLong(8, scheduleItem.getScheduleItemId());

            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query add scheduleItem");
            return false;
        }
        return true;
    }


    @Override
    public boolean updateFreeBusyById(ScheduleItem scheduleItem) {
        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_FREE_BUSY);
            ps.setBoolean(1, scheduleItem.getFreeBusy());
            ps.setLong(2, scheduleItem.getScheduleItemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query update user");
            return false;
        }
        return true;
    }

    @Override
    public List<ScheduleItem> findScheduleItemForPagination(int startRecord, int recordsPerPage) {

        List<ScheduleItem> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_FOR_PAGINATION)) {
            ps.setInt(1, startRecord);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(scheduleItemBuilder(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findUsersForPagination user");
        }
        return result;
    }

    @Override
    public int getNumberOfRows() {
        int numberOfRows = 0;

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_ROWS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numberOfRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query getNumberOfRows");
        }
        return numberOfRows;
    }


    private ScheduleItem  scheduleItemBuilder(ResultSet resultSet) throws SQLException {

        return ScheduleItem.builder()
                .withScheduleItemId(resultSet.getLong("schedule_item_id"))
                .withDate(resultSet.getDate("date").toLocalDate())
                .withTime(resultSet.getTime("time").toLocalTime())
                .withMaster(User.builder()
                        .withUserId(resultSet.getLong("master_id"))
                        .withFirstName(resultSet.getString("i1.first_name"))
                        .withLastName(resultSet.getString("i1.last_name"))
                        .build())
                .withCustomer(User.builder()
                        .withUserId(resultSet.getLong("customer_id"))
                        .withFirstName(resultSet.getString("i2.first_name"))
                        .withLastName(resultSet.getString("i2.last_name"))
                        .withEmail(resultSet.getString("i2.email"))
                        .build())
                .withFreeBusy(resultSet.getBoolean("free_busy"))
                .withSchedule(new Schedule(resultSet.getLong("schedule_id")))
                .withPriceOffers(PriceOffers.builder()
                        .withPriceOffersId(resultSet.getLong("price_offers_id"))
                        .withName(resultSet.getString("name"))
                        .withPrice(resultSet.getInt("price"))
                        .build())
                .build();
    }

    private ScheduleItem  basicScheduleItemBuilder(ResultSet resultSet) throws SQLException {

        return ScheduleItem.builder()
                .withScheduleItemId(resultSet.getLong("schedule_item_id"))
                .withDate(resultSet.getDate("date").toLocalDate())
                .withTime(resultSet.getTime("time").toLocalTime())
                .withMaster(User.builder()
                        .withUserId(resultSet.getLong("master_id"))
                        .withFirstName(resultSet.getString("first_name"))
                        .withLastName(resultSet.getString("last_name"))
                        .build())
                .withFreeBusy(resultSet.getBoolean("free_busy"))
                .withSchedule(new Schedule(resultSet.getLong("schedule_id")))
                .build();
    }
}
