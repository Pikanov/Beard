<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/index">Beard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" roleId="navbarNavDropdown">
        <ul class="navbar-nav">

            <li class="nav-item">
                <a class="nav-link" href="/profile"><fmt:message key="navAdmin.profile"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/comment"><fmt:message key="navAdmin.comments"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/schedule"><fmt:message key="navAdmin.schedule"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/record_list"><fmt:message key="navAdmin.recordList"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/users"><fmt:message key="navAdmin.editUsers"/></a>
            </li>

        </ul>
        <div class="navbar-text mr-3" >${email}</div>
        <div class="navbar-text mr-3" ><a href="/logout" ><fmt:message key="navAdmin.logout"/></a></div>
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