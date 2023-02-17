package servlet;

import mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/AdminTotalCountServlet/*")
public class AdminTotalCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url=request.getRequestURL().toString();
        String methodName=url.substring(url.lastIndexOf("/")+1);
        Class clazz=this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(clazz.newInstance(),request,response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    //admin所有数据
    public void getTotalPageNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        int totalCount=adminMapper.getTotalPageNum();
        response.getWriter().println(totalCount);
    }
}
