<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 table-responsive">
            <table class="table table-hover" roleId = "myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="comment.number"/></th>
                    <th scope="col"><fmt:message key="comment.comment"/></th>
                    <th scope="col"><fmt:message key="comment.email"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="comment" items="${commentList}">
                    <tr>
                        <th scope="row">${comment.commentId}</th>
                        <th>${comment.comment}</th>
                        <th>${comment.user.email}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <form method="post" accept-charset="ISO-8859-1">
                <div class="form-group">
                    <label for="comment"><fmt:message key="comment.writeComment"/></label>
                    <textarea class="form-control" name="comment" id="comment" rows="3"></textarea>
                </div>
                <input type="submit" value="<fmt:message key="comment.send"/>" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-lg-1"></div>
    </div>

    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <nav aria-label="Navigation for comment">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="comment?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"><fmt:message key="comment.previous"/></a>
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
                                                         href="comment?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link"
                                                 href="comment?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}"><fmt:message key="comment.next"/></a>
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
