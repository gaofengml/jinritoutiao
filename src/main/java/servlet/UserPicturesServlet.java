package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import entity.Artical;
import entity.Pingdao;
import entity.UserPictures;
import mapper.AdminMapper;
import mapper.ArticalMapper;
import mapper.PingdaoMapper;
import mapper.UserPicturesMapper;
import org.apache.ibatis.session.SqlSession;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet("/UserPicturesServlet/*")
@MultipartConfig
public class UserPicturesServlet extends HttpServlet {
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


    //素材列表展示页--所有素材--每页展示24条
    public void getSuCaiData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,24);//第一页,每页24条
        List<UserPictures> list= userPicturesMapper.selectAll();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }

    //素材列表展示页--收藏的素材--每页展示24条
    public void getSuCaiCollectedData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,24);//第一页,每页24条
        List<UserPictures> list= userPicturesMapper.selectByCollected();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }
    //素材列表展示页--素材图片修改status---收藏/取消收藏--每页展示24条
    public void updateStatusById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        String id = req.getParameter("id");
        int id1=Integer.parseInt(id);
        String status = req.getParameter("status");
        UserPictures userPictures = new UserPictures();
        userPictures.setId(id1);
        userPictures.setStatus(status);
        int index= userPicturesMapper.updateStatusById(userPictures);
        resp.getWriter().println(index);
    }

    //素材列表展示页--素材图片修改status--每页展示24条
    public void deleteOnePicture(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper=sqlSession.getMapper(UserPicturesMapper.class);
        String id = req.getParameter("id");
        int id1=Integer.parseInt(id);
        int index= userPicturesMapper.deleteByPrimaryKey(id1);
        resp.getWriter().println(index);
    }

    public void upLoadPicture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "*");/*允许跨域*/

        //获取all_id


        //向数据库插入图片的虚拟路径
        String url = "http://localhost:8080/top/images/";
//        String path = req.getServletContext().getRealPath("images/");//文件的绝对路径1

        String path = req.getSession().getServletContext().getRealPath("/images/");//文件的绝对路径2
        System.out.println(path);

        Collection<Part> parts = req.getParts();//所有的form信息都存入在part集合中
        System.out.println(parts);

        String fileName = "";
        for (Part part : parts) {
            if (part.getSubmittedFileName() != null) {//是文件
                fileName = System.currentTimeMillis() + part.getSubmittedFileName();
                part.write(path + fileName);
            }
        }
        System.out.println("数据库存储路径:" + url + fileName);//虚拟路径
        String pic_path = url + fileName;

        //添加进数据库
        SqlSession sqlSession = DBUtil.getSqlSession();
        UserPicturesMapper userPicturesMapper = sqlSession.getMapper(UserPicturesMapper.class);
        UserPictures userPictures = new UserPictures();

        userPictures.setAll_id(34);
        userPictures.setImageUrl(pic_path);
        userPictures.setStatus("0");

        int index = userPicturesMapper.insert(userPictures);
        if (index > 0) {
            System.out.println("上传图片成功");

        }


    }
}
