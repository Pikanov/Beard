package com.beard.servlet;

import com.beard.entity.PriceOffers;
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
//        String date = req.getParameter("date");
//        String time = req.getParameter("time");
//        Long masterId = Long.valueOf(req.getParameter("masterId"));
        HttpSession session = req.getSession();

        Long customerId = (Long) session.getAttribute("userId");
        Long inputPriceService = Long.valueOf(req.getParameter("inputPriceService"));


        ScheduleItem item = (ScheduleItem)session.getAttribute("item");
        item.setFreeBusy(false);
        item.setCustomer(User.builder().withUserId(customerId).build());

        item.setPriceOffers(PriceOffers.builder()
                .withPriceOffersId(inputPriceService)
                .build());

        //1. записывать item уже не нужно, нужно только апдейтить поле FreeBusy и Customer и PriceOffer
        //2. Добавленные

        scheduleItemService.update(item); //TODO мы должны передавать ScheduleItem

        scheduleItemService.updateFreeBusyById(item);

        resp.sendRedirect("/congratulations");
    }

    private ScheduleItem buildScheduleItem(Long inputPriceService, Long customerId) {

        return ScheduleItem.builder()
                .withCustomer(User.builder()
                        .withUserId(customerId)
                        .build())
                .withPriceOffers(PriceOffers.builder()
                        .withPriceOffersId(inputPriceService)
                        .build())
                .withFreeBusy(false)
                .build();

    }
}
