<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Record list</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-0"></div>
        <div class="col-lg-12 table-responsive">
            <table class="table table-hover" id="myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="recordList.id"/></th>
                    <th scope="col"><fmt:message key="recordList.masterFirstName"/></th>
                    <th scope="col"><fmt:message key="recordList.masterLastName"/></th>
                    <th scope="col"><fmt:message key="recordList.price"/></th>
                    <th scope="col"><fmt:message key="recordList.service"/></th>
                    <th scope="col"><fmt:message key="recordList.date"/></th>
                    <th scope="col"><fmt:message key="recordList.time"/></th>
                    <th scope="col"><fmt:message key="recordList.customerFirstName"/></th>
                    <th scope="col"><fmt:message key="recordList.customerLastName"/></th>
                    <th scope="col"><fmt:message key="recordList.email"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="records" items="${recordList}">
                    <tr>
                        <th scope="row">${records.scheduleItemId}</th>
                        <td>${records.master.firstName}</td>
                        <td>${records.master.lastName}</td>
                        <td>${records.priceOffers.price}</td>
                        <td>${records.priceOffers.name}</td>
                        <td>${records.date}</td>
                        <td>${records.time}</td>
                        <td>${records.customer.firstName}</td>
                        <td>${records.customer.lastName}</td>
                        <td>${records.customer.email}</td>
                        <td>
                            <form action="record_list" method="post">
                                <a class="btn btn-primary" href="mailto:${records.customer.email}}" role="button">Send
                                    email</a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-0"></div>
    </div>


    <%--    <form action="contact" method="post">--%>
    <%--        <p>Your email address: <input name="email"></p>--%>
    <%--        <p>Mail subject: <input name="subject"></p>--%>
    <%--        <p>Mail message: <textarea name="message"></textarea></p>--%>
    <%--        <p><input type="submit"><span class="message">${message}</span></p>--%>
    <%--    </form>--%>

    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <nav aria-label="Navigation for record_list">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="record_list?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message
                                key="recordList.previous"/></a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="record_list?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link"
                                                 href="record_list?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message
                                key="recordList.next"/></a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>
</body>
</html>
