package servlet;

import mapper.ArticalMapper;
import mapper.FansdetailsMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@WebServlet("/FansdetailsTotalCountServlet/*")
public class FansdetailsTotalCountServlet extends HttpServlet {
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

    //粉丝管理--粉丝概况--获取粉丝详情表的所有数据个数
    public void getTotalPageNum_fans(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        FansdetailsMapper fansdetailsMapper=sqlSession.getMapper(FansdetailsMapper.class);
        int totalCount=fansdetailsMapper.getTotalPageNum();
        resp.getWriter().println(totalCount);
    }


}
