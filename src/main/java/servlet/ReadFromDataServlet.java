package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.ReadFrom;
import mapper.ReadFromMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getReadFromData")
public class ReadFromDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ReadFromMapper readFromMapper=sqlSession.getMapper(ReadFromMapper.class);
        ReadFrom readFromData = readFromMapper.getReadFromData();
        resp.getWriter().println(JSONObject.parseObject(JSON.toJSON(readFromData).toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
