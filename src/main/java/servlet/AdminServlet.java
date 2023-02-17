package servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import entity.Admin;
import mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/page")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,3);//第一页,每页3条
        List<Admin> list= adminMapper.selectAll();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
