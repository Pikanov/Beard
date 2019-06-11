package com.beard.controller;

import com.beard.entity.PriceOffers;
import com.beard.entity.Schedule;
import com.beard.entity.ScheduleItem;
import com.beard.entity.User;
import com.beard.repository.impl.PriceOffersRepositoryImpl;
import com.beard.repository.impl.ScheduleItemRepositoryImpl;
import com.beard.service.PriceOffersService;
import com.beard.service.ScheduleItemService;
import com.beard.service.impl.PriceOffersServiceImpl;
import com.beard.service.impl.ScheduleItemServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

    private ScheduleItemService scheduleItemService = new ScheduleItemServiceImpl(new ScheduleItemRepositoryImpl());

    private PriceOffersService priceOffersService = new PriceOffersServiceImpl(new PriceOffersRepositoryImpl());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ScheduleItem> scheduleItemList = scheduleItemService.findByIdScheduleItemBasic(1L);
        req.setAttribute("scheduleItemList", scheduleItemList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/schedule.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleItem item = getScheduleItem(req);
        HttpSession session = req.getSession();
        List<PriceOffers> priceOffersList = priceOffersService.findAll();

        session.setAttribute("item", item);
        req.setAttribute("item", item);
        req.setAttribute("priceOffersList", priceOffersList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/record.jsp");
        dispatcher.forward(req, resp);
    }

    private ScheduleItem getScheduleItem(HttpServletRequest req) {
        String scheduleItemId = req.getParameter("scheduleItemId");
        String date = req.getParameter("date");
        String time = req.getParameter("time");
        String masterId = req.getParameter("masterId");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Boolean freeBusy = Boolean.valueOf(req.getParameter("updateFreeBusyById"));

        return ScheduleItem.builder()
                .withScheduleItemId(Long.parseLong(scheduleItemId))
                .withDate(LocalDate.parse(date))
                .withTime(LocalTime.parse(time))
                .withMaster(User.builder()
                        .withUserId(Long.valueOf(masterId))
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .build())
                .withFreeBusy(freeBusy)
                .withSchedule(new Schedule(1L))
                .build();
    }
}
