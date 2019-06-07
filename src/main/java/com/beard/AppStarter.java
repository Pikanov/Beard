package com.beard;

import com.beard.repository.ScheduleItemRepository;
import com.beard.repository.impl.ScheduleItemRepositoryImpl;
import com.beard.service.ScheduleItemService;
import com.beard.service.impl.ScheduleItemServiceImpl;

public class AppStarter {
    public static void main(String[] args) {

        ScheduleItemRepository scheduleItemRepository = new ScheduleItemRepositoryImpl();

        ScheduleItemService scheduleItemService = new ScheduleItemServiceImpl(scheduleItemRepository);
        }
    }
