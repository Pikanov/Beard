<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Schedule</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-0"></div>
        <div class="col-lg-12">
<c:forEach var="item" items="${scheduleItemList}">
    <form method="post" class="form-inline">

        <div class="form-group">
            <input class="form-control" id="scheduleItemId" name="scheduleItemId" value="${item.scheduleItemId}" hidden/>
        </div>
        <div class="form-group">
            <input class="form-control" id="date" name="date" value="${item.date}" readonly/>
        </div>

        <div class="form-group">
            <input class="form-control" id="time" name="time" value="${item.time}" readonly/>
        </div>

        <div class="form-group">
            <input class="form-control" id="masterId" name="masterId" value="${item.master.userId}" hidden/>
            <input class="form-control" id="firstName" name="firstName" value="${item.master.firstName}" readonly/>
        </div>

        <div class="form-group">
            <input class="form-control" id="lastName" name="lastName" value="${item.master.lastName}" readonly/>
        </div>

        <div class="form-group">
            <input class="form-control" id="freeBusy" name="freeBusy" value="${item.freeBusy}" hidden/>
        </div>
        <c:if test="${item.freeBusy eq 'true'}">
            <input type="submit" value="<fmt:message key="schedule.singUpButton"/>" class="btn btn-primary" />
        </c:if>
    </form>
</c:forEach>
        </div>
        <div class="col-lg-0"></div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <form method="post">

                <div class="form-group">
                    <label for="choiceDate"><fmt:message key="schedule.choiceDate"/></label>
                    <input class="form-control" id="choiceDate" name="choiceDate"  value=""/>
                    <script>
                        $(function () {
                            $('input[name="choiceDate"]').daterangepicker({
                                singleDatePicker: true,
                                showDropdowns: true,
                                minYear: 2019,
                                maxYear: parseInt(moment().format('YYYY'), 10)
                            });
                        });
                    </script>
                </div>

<%--                <input type="submit" value="Confirm" class="btn btn-success"/>--%>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>