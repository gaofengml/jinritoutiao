package servlet;

import mapper.ArticalMapper;
import mapper.UserPicturesMapper;
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

@WebServlet("/UserPicturesCountServlet/*")
public class UserPicturesTotalCountServlet extends HttpServlet {
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

    //素材展示界面-获取所有数据
    public void getTotalPageNum_SuCai(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        int totalCount=userPicturesMapper.getTotalPageNumSuCai();
        resp.getWriter().println(totalCount);
    }

    //素材展示界面-获取收藏数据
    public void getTotalPageNum_SuCaiCollected(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        int totalCount=userPicturesMapper.getTotalPageNumSuCaiCollected();
        resp.getWriter().println(totalCount);
    }

}
