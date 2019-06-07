package com.beard.repository;

import com.beard.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemRepository {

    List<ScheduleItem> findAll();

    List<ScheduleItem> findByIdScheduleItemBasic(Long scheduleId);

    boolean add(ScheduleItem scheduleItem);

    boolean deleteById(Long scheduleIdId);

    boolean update(ScheduleItem scheduleItem);

    boolean updateFreeBusyById(ScheduleItem scheduleItem);

    List<ScheduleItem> findScheduleItemForPagination(int startRecord, int recordsPerPage);

    int getNumberOfRows();
}
