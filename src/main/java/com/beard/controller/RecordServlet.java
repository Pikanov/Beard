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
import com.beard.util.MailSender;

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

@WebServlet("/record")
public class RecordServlet extends HttpServlet {

    private ScheduleItemService scheduleItemService = new ScheduleItemServiceImpl(new ScheduleItemRepositoryImpl());

    private PriceOffersService priceOffersService = new PriceOffersServiceImpl(new PriceOffersRepositoryImpl());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<PriceOffers> priceOffers = priceOffersService.findAll();
        req.setAttribute("priceOffersList", priceOffers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/record.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long scheduleItemId = Long.valueOf(req.getParameter("scheduleItemId"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        LocalTime time = LocalTime.parse(req.getParameter("time"));
        Long masterId = Long.valueOf(req.getParameter("masterId"));
        String masterFirstName = req.getParameter("firstName");
        String masterLastName = req.getParameter("lastName");

        HttpSession session = req.getSession();
        Long customerId = (Long) session.getAttribute("userId");
        String customerMail = String.valueOf(session.getAttribute("email"));
        Long inputPriceService = Long.valueOf(req.getParameter("inputPriceService"));

        ScheduleItem scheduleItem = ScheduleItem.builder()
                .withScheduleItemId(scheduleItemId)
                .withDate(date)
                .withTime(time)
                .withMaster(User.builder().withUserId(masterId)
                        .withFirstName(masterFirstName)
                        .withLastName(masterLastName)
                        .build())
                .withCustomer(User.builder().withUserId(customerId).build())
                .withFreeBusy(false)
                .withSchedule(new Schedule(1L))
                .withPriceOffers(priceOffersService.findById(inputPriceService))
                .build();

        session.setAttribute("scheduleItem", scheduleItem);

        scheduleItemService.update(scheduleItem);

        User mailTo = User.builder().withEmail(customerMail).build();

        MailSender.sendMessageToEmail(mailTo);

        req.setAttribute("item", scheduleItem);
        resp.sendRedirect("/congratulations");
    }
}
