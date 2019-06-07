package com.beard.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class ScheduleItem {
    private Long scheduleItemId;
    private LocalDate date;
    private LocalTime time;
    private User master;
    private User customer;
    private Boolean freeBusy;
    private Schedule schedule;
    private PriceOffers priceOffers;

    private ScheduleItem(Builder builder) {
        this.scheduleItemId = builder.scheduleItemId;
        this.date = builder.date;
        this.time = builder.time;
        this.master = builder.master;
        this.customer = builder.customer;
        this.freeBusy = builder.freeBusy;
        this.schedule = builder.schedule;
        this.priceOffers = builder.priceOffers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getScheduleItemId() {
        return scheduleItemId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public User getMaster() {
        return master;
    }

    public Boolean getFreeBusy() {
        return freeBusy;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public User getCustomer() {
        return customer;
    }

    public PriceOffers getPriceOffers() {
        return priceOffers;
    }

    public void setFreeBusy(Boolean freeBusy) {
        this.freeBusy = freeBusy;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setPriceOffers(PriceOffers priceOffers) {
        this.priceOffers = priceOffers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScheduleItem that = (ScheduleItem) o;
        return Objects.equals(scheduleItemId, that.scheduleItemId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(master, that.master) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(freeBusy, that.freeBusy) &&
                Objects.equals(schedule, that.schedule) &&
                Objects.equals(priceOffers, that.priceOffers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleItemId, date, time, master, customer,
                freeBusy, schedule, priceOffers);
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "scheduleItemId=" + scheduleItemId +
                ", date=" + date +
                ", time=" + time +
                ", master=" + master +
                ", customer=" + customer +
                ", freeBusy=" + freeBusy +
                ", schedule=" + schedule +
                ", priceOffers=" + priceOffers +
                '}';
    }

    public static class Builder {
        private Long scheduleItemId;
        private LocalDate date;
        private LocalTime time;
        private User master;
        private User customer;
        private Boolean freeBusy;
        private Schedule schedule;
        private PriceOffers priceOffers;

        private Builder() {
        }

        public ScheduleItem build() {
            return new ScheduleItem(this);
        }

        public Builder withScheduleItemId(Long scheduleItemId) {
            this.scheduleItemId = scheduleItemId;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withTime(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder withMaster(User master) {
            this.master = master;
            return this;
        }

        public Builder withFreeBusy(Boolean freeBusy) {
            this.freeBusy = freeBusy;
            return this;
        }

        public Builder withSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public Builder withCustomer(User customer) {
            this.customer = customer;
            return this;
        }

        public Builder withPriceOffers(PriceOffers priceOffers) {
            this.priceOffers = priceOffers;
            return this;
        }
    }
}
