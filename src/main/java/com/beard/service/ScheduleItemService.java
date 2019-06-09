package com.beard.service;

import com.beard.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemService {

    List<ScheduleItem> findAll();

    List<ScheduleItem> findByIdScheduleItemBasic(Long scheduleId);

    boolean add(ScheduleItem scheduleItem);

    boolean deleteById(Long scheduleIdId);

    boolean update(ScheduleItem scheduleItem);

    List<ScheduleItem> findScheduleItemForPagination(int startRecord, int recordsPerPage);

    int getNumberOfRows();

    ScheduleItem findById(Long scheduleItemId);
}
