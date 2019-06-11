package com.beard.controller;

import com.beard.entity.ScheduleItem;
import com.beard.repository.impl.ScheduleItemRepositoryImpl;
import com.beard.service.ScheduleItemService;
import com.beard.service.impl.ScheduleItemServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/record_list")
public class RecordListServlet extends HttpServlet {

    private ScheduleItemService scheduleItemService = new ScheduleItemServiceImpl(new ScheduleItemRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int currentPage;
        if (req.getParameter("currentPage") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }

        int recordsPerPage;
        if (req.getParameter("recordsPerPage") == null) {
            recordsPerPage = 11;
        } else {
            recordsPerPage = Integer.valueOf(req.getParameter("recordsPerPage"));
        }


        int numberOfRows = scheduleItemService.getNumberOfRows();

        int nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }

        int start = currentPage * recordsPerPage - recordsPerPage;

        List<ScheduleItem> record = scheduleItemService.findScheduleItemForPagination(start, recordsPerPage);

        req.setAttribute("noOfPages", nOfPages);
        req.getSession().setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("recordList", record);

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/record_list.jsp");
        dispatcher.forward(req, resp);
    }
}
