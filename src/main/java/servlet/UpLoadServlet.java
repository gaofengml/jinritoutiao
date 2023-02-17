package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = "/upLoad")
@MultipartConfig
public class UpLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin","*");/*允许跨域*/

        String url="http://localhost:8080/top/images/";
        String path = request.getServletContext().getRealPath("images/");//文件的绝对路径1

//        String path = request.getSession().getServletContext().getRealPath("/images/");//文件的绝对路径2
        System.out.println(path);

        Collection<Part> parts= request.getParts();//所有的form信息都存入在part集合中
        System.out.println(parts);

        String fileName="";
        for (Part part:parts){
            if (part.getSubmittedFileName()!=null){//是文件
                fileName=System.currentTimeMillis()+part.getSubmittedFileName();
                part.write(path+fileName);
            }
        }
        System.out.println("数据库存储路径:"+url+fileName);//虚拟路径







    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
