package com.beard.entity;

import java.util.List;
import java.util.Objects;

/**
 * Schedule - model determines the days in the schedule
 */
public class Schedule {
    private Long scheduleId;
    private List<ScheduleItem> list;

    public Schedule(Long scheduleId, List<ScheduleItem> list) {
        this.scheduleId = scheduleId;
        this.list = list;
    }

    public Schedule(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<ScheduleItem> getList() {
        return list;
    }

    public void setList(List<ScheduleItem> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId) &&
                Objects.equals(list, schedule.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleId, list);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", list=" + list +
                '}';
    }
}