<%@include file="parts/common_head.jsp" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <br>
            <form method="post">

                <div class="form-group">
                    <label for="firstName"><fmt:message key="registration.firstName"/></label>
                    <input type="text" class="form-control" id="firstName" name="firstName"/>
                    <small roleId="firstNameHelp" class="form-text text-muted"><fmt:message key="registration.enterFirstName"/></small>
                </div>

                <div class="form-group">
                    <label for="lastName"><fmt:message key="registration.lastName"/></label>
                    <input type="text" class="form-control" id="lastName" name="lastName"/>
                    <small roleId="lastNameHelp" class="form-text text-muted"><fmt:message key="registration.enterLastName"/></small>
                </div>

                <div class="form-group">
                    <label for="phoneNumber"><fmt:message key="registration.phoneNumber"/></label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"/>
                    <small roleId="phoneNumberHelp" class="form-text text-muted"><fmt:message key="registration.enterPhoneNumber"/></small>
                </div>

                <div class="form-group">
                    <label for="email"><fmt:message key="registration.email"/></label>
                    <input type="email" class="form-control" id="email" name="email"/>
                    <small roleId="emailHelp" class="form-text text-muted"><fmt:message key="registration.enterEmail"/></small>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="registration.password"/></label>
                    <input type="password" class="form-control" id="password" name="password" minlength="8" maxlength="128"/>
                    <small roleId="passwordHelp" class="form-text text-muted"><fmt:message key="registration.passwordHelp"/>
                    </small>
                </div>

                <div class="form-group">
                    <label for="password2"><fmt:message key="registration.password"/></label>
                    <input type="password" class="form-control" id="password2" name="password2"
                           placeholder="<fmt:message key="registration.passwordOnceMore"/>" minlength="8" maxlength="128"/>
                </div>
                <div class="text-danger">
                    ${exception}
                </div>
                <input type="submit" value="<fmt:message key="registration.register"/>" class="btn btn-success"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>


