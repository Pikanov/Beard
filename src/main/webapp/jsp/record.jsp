<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Record</title>
</head>
<body>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
    <form action = "record" method="post">

        <div class="form-group">
            <input class="form-control" id="scheduleItemId" name="scheduleItemId" value="${item.scheduleItemId}" readonly/>
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

        <div class="form-group">
            <label for="inputPriceService"><fmt:message key="record.selectService"/></label>
            <select id="inputPriceService" name="inputPriceService" class="form-control">
                <c:forEach items="${priceOffersList}" var="priceOffers">
                    <option value="${priceOffers.priceOffersId}">${priceOffers.name} ${priceOffers.price}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="<fmt:message key="record.recordButton"/>" class="btn btn-primary"/>
    </form>
    </div>
    <div class="col-lg-3"></div>
</div>
</div>
</body>
</html>
