<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-10 table-responsive">
                            <table class="table table-hover" id = "myTable">
                                <thead>
                                <tr>
                                    <th scope="col"><fmt:message key="users.id"/></th>
                                    <th scope="col"><fmt:message key="users.firstName"/></th>
                                    <th scope="col"><fmt:message key="users.lastName"/></th>
                                    <th scope="col"><fmt:message key="users.email"/></th>
                                    <th scope="col"><fmt:message key="users.phoneNumber"/></th>
                                    <th scope="col"><fmt:message key="users.role"/></th>
                                    <th scope="col"><fmt:message key="users.edit"/></th>
                                    <th scope="col"><fmt:message key="users.delete"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <th scope="row">${user.userId}</th>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>${user.role.role}</td>
                                        <td><button type="button" class="btn btn-primary"><fmt:message key="users.edit"/></button></td>
                                        <td><button type="button" class="btn btn-danger"><fmt:message key="users.delete"/></button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-lg-1"></div>
                    </div>


                    <div class="row">
                        <div class="col-lg-4"></div>
                        <div class="col-lg-4">
                            <nav aria-label="Navigation for user">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${currentPage != 1}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="users.previous"/></a>
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
                                                                         href="users?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${currentPage lt noOfPages}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="users.next"/></a>
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