<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <c:set var="user" value="${user}"/>
            <form method="post">

                <div class="form-group">
                    <label for="firstName"><fmt:message key="profile.firstName"/></label>
                    <input class="form-control" id="firstName" name="firstName" value="${user.firstName}"/>
                    <small id="firstNameHelp" class="form-text text-muted"><fmt:message key="profile.changeFirstName"/></small>
                </div>

                <div class="form-group">
                    <label for="lastName"><fmt:message key="profile.lastName"/></label>
                    <input class="form-control" id="lastName" name="lastName" value="${user.lastName}"/>
                    <small id="lastNameHelp" class="form-text text-muted"><fmt:message key="profile.changeLastName"/></small>
                </div>

                <div class="form-group">
                    <label for="password"><fmt:message key="profile.password"/></label>
                    <input type="text" class="form-control" id="password" name="password"
                           value="${user.password}" minlength="8" maxlength="128"/>
                    <small id="passwordHelp" class="form-text text-muted"><fmt:message key="profile.changePassword"/></small>
                </div>

                <div class="form-group">
                    <label for="phoneNumber"><fmt:message key="profile.phoneNumber"/></label>
                    <input class="form-control" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}"/>
                    <small id="phoneNumberHelp" class="form-text text-muted"><fmt:message key="profile.changePhoneNumber"/></small>
                </div>

                <div class="form-group">
                    <label for="email"><fmt:message key="profile.email"/></label>
                    <input type="email" class="form-control" id="email" name="email"
                           value="${user.email}" readonly/>
                </div>

                <div class="text-danger">
                    ${exception}
                </div>
                <input type="submit" value="<fmt:message key="profile.update"/>" class="btn btn-success"/>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>