package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import entity.Artical;
import entity.Fansdetails;
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
import java.util.List;

@WebServlet("/FansdetailsServlet/*")
public class FansdetailsServlet extends HttpServlet {
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

    //获取粉丝详情表的所有数据--每页6条数据
    public void getFansDetailsData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        FansdetailsMapper fansdetailsMapper=sqlSession.getMapper(FansdetailsMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        List<Fansdetails> list= fansdetailsMapper.selectAll();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }
}
