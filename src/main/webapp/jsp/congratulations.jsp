<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Congratulation</title>
</head>
<body>

<h4 align="center"><fmt:message key="congratulation.congratulation"/></h4>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10 table-responsive">
            <table class="table table-hover" id = "myTable">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="congratulation.master"/></th>
                    <th scope="col"><fmt:message key="congratulation.firstName"/></th>
                    <th scope="col"><fmt:message key="congratulation.lastName"/></th>
                    <th scope="col"><fmt:message key="congratulation.price"/></th>
                    <th scope="col"><fmt:message key="congratulation.service"/></th>
                    <th scope="col"><fmt:message key="congratulation.date"/></th>
                    <th scope="col"><fmt:message key="congratulation.time"/></th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">${scheduleItem.scheduleItemId}</th>
                        <td>${scheduleItem.master.firstName}</td>
                        <td>${scheduleItem.master.lastName}</td>
                        <td>${scheduleItem.priceOffers.price}</td>
                        <td>${scheduleItem.priceOffers.name}</td>
                        <td>${scheduleItem.date}</td>
                        <td>${scheduleItem.time}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
</div>
</body>
</html>
