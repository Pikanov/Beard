package com.beard.service.impl;

import com.beard.entity.ScheduleItem;
import com.beard.repository.ScheduleItemRepository;
import com.beard.service.ScheduleItemService;

import java.util.List;

public class ScheduleItemServiceImpl implements ScheduleItemService {

    private ScheduleItemRepository scheduleItemRepository;

    public ScheduleItemServiceImpl(ScheduleItemRepository scheduleItemRepository) {
        this.scheduleItemRepository = scheduleItemRepository;
    }


    @Override
    public List<ScheduleItem> findAll() {
        return scheduleItemRepository.findAll();
    }

    @Override
    public List<ScheduleItem> findByIdScheduleItemBasic(Long scheduleId) {
        return scheduleItemRepository.findByIdScheduleItemBasic(scheduleId);
    }

    @Override
    public boolean add(ScheduleItem scheduleItem) {
        return scheduleItemRepository.add(scheduleItem);
    }

    @Override
    public boolean deleteById(Long scheduleIdId) {
        return scheduleItemRepository.deleteById(scheduleIdId);
    }

    @Override
    public boolean update(ScheduleItem scheduleItem) {
        return scheduleItemRepository.update(scheduleItem);
    }


    @Override
    public List<ScheduleItem> findScheduleItemForPagination(int startRecord, int recordsPerPage) {
        return scheduleItemRepository.findScheduleItemForPagination(startRecord, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() {
        return scheduleItemRepository.getNumberOfRows();
    }

    @Override
    public ScheduleItem findById(Long scheduleItemId) {
        return scheduleItemRepository.findById(scheduleItemId);
    }
}
