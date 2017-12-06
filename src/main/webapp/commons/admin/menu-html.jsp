<nav>
    <div class="container">
        <div class="item">
            <a class="list-book" href="<% out.print(request.getContextPath().concat("/dashboard-admin.jsp")); %>">
                Dashboard
            </a>
        </div>
        <div class="item">
            <a class="list-book" href="<% out.print(request.getContextPath().concat("/books/list?operation=LIST&USER=1")); %>">
                Livros
            </a>
        </div>
        <div class="item">
            <a class="list-disable-book" href="<% out.print(request.getContextPath().concat("/books/list-disable?operation=LIST-DISABLE")); %>">
                Livros Inativos
            </a>
        </div>
        <div class="item">
            <a class="list-customer" href="<% out.print(request.getContextPath().concat("/customers/list?operation=LIST")); %>">
                Clientes
            </a>
        </div>
        <div class="item">
            <a class="list-disable-customer" href="<% out.print(request.getContextPath().concat("/customers/list-disable?operation=LIST-DISABLE")); %>">
                Clientes Inativos
            </a>
        </div>
        <div class="item">
            <a class="list-sale" href="<% out.print(request.getContextPath().concat("/sales/list?operation=LIST")); %>">
                Vendas
            </a>
        </div>
        <div class="item">
            <a class="logout" href="<% out.print(request.getContextPath() + "/log-out?operation=DELETE"); %>">
                Logout
            </a>
        </div>
    </div>
</nav>