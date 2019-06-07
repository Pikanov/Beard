<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/index">Beard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" roleId="navbarNavDropdown">
        <ul class="navbar-nav">

            <li class="nav-item">
                <a class="nav-link" href="/registration"><fmt:message key="navDefault.registration"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login"><fmt:message key="navDefault.login"/></a>
            </li>
        </ul>
        <div class="navbar-text mr-3">${email}</div>
    </div>
</nav>
<div class="container-fluid bg-light">
    <div id="navigation" class="float-right" >
        <ul>
            <li><a href="?sessionLocale=en"><fmt:message key="menu.en"/></a></li>
            <li><a href="?sessionLocale=ru"><fmt:message key="menu.ru"/></a></li>
        </ul>
    </div>
</div>