package servlet;

import entity.Artical;
import entity.Pingdao;
import mapper.AdminMapper;
import mapper.ArticalMapper;
import mapper.PingdaoMapper;
import mapper.ReadFromMapper;
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

@WebServlet("/PublishArticlesServlet/*")
@MultipartConfig
public class PublishArticlesServlet extends HttpServlet {
    SqlSession sqlSession= DBUtil.getSqlSession();
    
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

    public void upLoadPicture(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin","*");/*????????????*/

        //???????????????
        String admin_name="admin";
        //???????????????????????????id
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        int admin_id = adminMapper.selectByName(admin_name);
        //??????????????????
        String title = req.getParameter("title");
        //??????????????????
        String articaltext = req.getParameter("content");

        //??????????????????
        PingdaoMapper pingdaoMapper = sqlSession.getMapper(PingdaoMapper.class);
        String pd_data = req.getParameter("pindao");
        System.out.println("?????????"+pd_data);
        //?????????????????????????????????id
        Pingdao pingdao = pingdaoMapper.selectByName(pd_data);
        System.out.println("??????id="+pingdao.getId());
        int pd_id =pingdao.getId();

        //???????????????????????????????????????
        String url="http://localhost:8080/top/images/";
        String path = req.getServletContext().getRealPath("images/");//?????????????????????1

//        String path = req.getSession().getServletContext().getRealPath("/images/");//?????????????????????2
        System.out.println(path);

        Collection<Part> parts= req.getParts();//?????????form??????????????????part?????????
        System.out.println(parts);

        String fileName="";
        for (Part part:parts){
            if (part.getSubmittedFileName()!=null){//?????????
                fileName=System.currentTimeMillis()+part.getSubmittedFileName();
                part.write(path+fileName);
            }
        }
        System.out.println("?????????????????????:"+url+fileName);//????????????
        String pic_path=url+fileName;

        //????????????=????????????
        String time =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //????????????0--?????????
        String status="0";

        //??????????????????
        //admin_id, articalTitle,
        //      readNum, fansReadNum, collectNum,
        //      upvoteNum, fansUpvoteNum, sendTime,
        //      imageUrl, pd_id, status,articalText
        ArticalMapper articalMapper = sqlSession.getMapper(ArticalMapper.class);
        Artical artical = new Artical();
        artical.setAdmin_id(admin_id);
        artical.setArticalTitle(title);
        artical.setReadNum(0);
        artical.setFansReadNum(0);
        artical.setCollectNum(0);
        artical.setUpvoteNum(0);
        artical.setFansUpvoteNum(0);
        artical.setSendTime(time);
        artical.setImageUrl(pic_path);
        artical.setPd_id(pd_id);
        artical.setStatus("0");
        artical.setArticalText(articaltext);
        int index = articalMapper.insert(artical);
        if (index>0){
            System.out.println("??????????????????");

        }

    }
}
