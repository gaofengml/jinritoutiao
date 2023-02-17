package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AccessAllowFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp=(HttpServletResponse)response;
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,resp);
    }

    @Override
    public void destroy() {
    }
}
