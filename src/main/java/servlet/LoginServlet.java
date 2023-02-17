package servlet;

import entity.Admin;
import entity.ResultData;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/loginServlet/*")
public class LoginServlet extends HttpServlet {

    AdminService adminService =new AdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getRequestURL().toString();
        String methodName=url.substring(url.lastIndexOf("/")+1);
        Class clazz=this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(clazz.newInstance(),req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp){
        String name =req.getParameter("name");
        String password= req.getParameter("password");

        Admin admin =new Admin();
        admin.setAdmin_name(name);
        admin.setAdmin_password(password);

        ResultData resultData = adminService.userLogin(admin);
        try {
            PrintWriter printWriter=resp.getWriter();
            printWriter.println(resultData.parseObject());
            printWriter.flush();
            printWriter.close();
            resp.getWriter().println(resultData.parseObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
