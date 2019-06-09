package com.beard.servlet;

import com.beard.repository.impl.ScheduleItemRepositoryImpl;
import com.beard.service.ScheduleItemService;
import com.beard.service.impl.ScheduleItemServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/congratulations")
public class CongratulationServlet extends HttpServlet {

    private ScheduleItemService scheduleItemService = new ScheduleItemServiceImpl(new ScheduleItemRepositoryImpl());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//TODO
        HttpSession session = req.getSession();
       // ScheduleItem scheduleItem = (ScheduleItem) session.getAttribute("item");
        //System.out.println(scheduleItem);
        //session.setAttribute("scheduleItem", scheduleItem);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/congratulations.jsp");

        dispatcher.forward(req, resp);
    }
}
