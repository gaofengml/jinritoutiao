package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import entity.Admin;
import entity.Artical;
import mapper.AdminMapper;
import mapper.ArticalMapper;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ArticalServlet/*")
public class ArticalServlet extends HttpServlet {
    Date date = new Date();

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
    //前台首页-每页展示6条
    public void getFrontDeskPageData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        List<Artical> articals= articalMapper.selectAll();

        AdminMapper adminMapper =sqlSession.getMapper(AdminMapper.class);
        List<Admin> admins = adminMapper.selectAll();

        JSONObject object =new JSONObject();
        object.put("article",articals);
        object.put("admin",admins);
        resp.getWriter().println(object);
    }
    //文章页面--通过文章id获取每个文章的信息
    public void getArtilceDataById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        Integer articleId= Integer.valueOf(req.getParameter("article_id"));


        Artical artical = articalMapper.selectByArticleId(articleId);//查到文章
        Admin admin = adminMapper.selectById(artical.getAdmin_id());//查到作者
//        JSONObject obj_article=JSONObject.parseObject(JSON.toJSON(artical).toString());
//        JSONObject obj_admin=JSONObject.parseObject(JSON.toJSON(admin).toString());
//        JSONArray array ;
//        array.put()

        JSONObject obj_article = JSONObject.parseObject(JSON.toJSON(artical).toString());
        JSONObject obj_admin= JSONObject.parseObject(JSON.toJSON(admin).toString());
        JSONObject two= new JSONObject();
        two.put("article" ,obj_article);
        two.put("admin",obj_admin);
//        obj1.put("article",JSON.toJSON(artical));



//        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(artical).toString()));

        resp.getWriter().println(two);

    }
    //阅读量 +1
    public void updateArticleReadNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer articleId= Integer.valueOf(req.getParameter("article_id"));
        int index = articalMapper.updateReadNumByArticleId(articleId);
        boolean flag;
        if (index>0){
            flag=true;
        }else {
            flag=false;
        }
        resp.getWriter().println(flag);
    }





    //图文数据页-每页展示6条
    public void getTuWenPageData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        List<Artical> list= articalMapper.selectAll();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }
    //内容列表页-每业展示10条
    public void getNrData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPage"));
        PageHelper.startPage(pageNum,10);//第一页,每页10条
        List<Artical> list= articalMapper.selectAll();
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
    }


    //获取今日数据
    public void getTodayData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【今日");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPageToday"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        Date date = new Date();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(time);
        List<Artical> list= articalMapper.selectByToday(time);
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
        System.out.println("今日数据结束】");
    }

    //获取本周数据
    public void getThisWeekData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【查询本周数据开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPageThisWeek"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        //后端算出今天是哪天
//        Date date = new Date();
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("本周:今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
        //获取前端发来的今日是周几
        String today_string = req.getParameter("today");
        System.out.println("前端发来的今日是周几:"+today_string);
        //周几换成是数字
        int  today_int=Integer.parseInt(today_string);
        //今日减去周几，计算本周开始是某个月某日
        int begin_time=time_d_int-today_int +1;
        System.out.println("本周开始是某个月的某日"+begin_time);
        String beginTime="";
        //获取本周开始时间，赋值给beginTime
        if (begin_time >= 1){//判断本周开始时间，是在同一个月，直接 拼接
            if (begin_time <10 ){
                //把本周开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym +'0'+ String.valueOf(begin_time);
            }else {
                //把本周开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym + String.valueOf(begin_time);
            }

        }
        else {//本周开始时间不是在同一个月
            if (begin_time == 0){
                //获取上个月天数
                int lastMonthdays=getLastMonthDays();
                begin_time = lastMonthdays;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time);
            }else if (begin_time < 0){
                int begin_time2= -(begin_time);
                int lastMonthdays=getLastMonthDays();
                int begin_time3= lastMonthdays - begin_time2;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
            }
        }
        System.out.println("本周开始时间："+beginTime);
        //传递--本周开始时间和今日时间
        List<Artical> list= articalMapper.selectByThisWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
        System.out.println("查询本周数据--结束】");
    }
    //获取近7天数据
    public void getSevenDaysData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【查询近7天数据开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPageThisWeek"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        //后端算出今天是哪天
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("近7天:今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
//        //获取前端发来的今日是周几
//        String today_string = req.getParameter("today");
//        System.out.println("前端发来的今日是周几:"+today_string);
//        //周几换成是数字
//        int  today_int=Integer.parseInt(today_string);
        //今日减去7天，计算近7天（包括今天）最开始的那天是某个月某日
        int begin_time=time_d_int-7 +1;
        System.out.println("近7天的开始时间是某个月的某日"+begin_time);
        String beginTime="";
        //获取近7天的开始时间，赋值给beginTime
        if (begin_time >= 1){//判断近7天开始时间，是在同一个月，直接 拼接
            if (begin_time <10 ){
                //把近7天的开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym +'0'+ String.valueOf(begin_time);
            }else {
                //把开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym + String.valueOf(begin_time);
            }
        }
        else {//近7天的开始时间不是在同一个月
            if (begin_time == 0){//在上个月月底
                //获取上个月天数
                int lastMonthdays=getLastMonthDays();
                begin_time = lastMonthdays;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time);
            }else if (begin_time < 0){
                int begin_time2= -(begin_time);
                int lastMonthdays=getLastMonthDays();
                int begin_time3= lastMonthdays - begin_time2;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
            }
        }
        System.out.println("近7天的开始时间："+beginTime);
        //传递--开始时间和今日时间
        List<Artical> list= articalMapper.selectByThisWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
        System.out.println("查询近7天的数据--结束】");
    }

    //获取近30天数据
    public void getMonthDaysData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【查询近30天数据开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPageThisWeek"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        //后端算出今天是哪天
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("近30天:今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
//        //获取前端发来的今日是周几
//        String today_string = req.getParameter("today");
//        System.out.println("前端发来的今日是周几:"+today_string);
//        //周几换成是数字
//        int  today_int=Integer.parseInt(today_string);
        //今日减去30天，计算近30天（包括今天）最开始的那天是某个月某日
        int begin_time=time_d_int-30 +1;
        System.out.println("近30天的开始时间是某个月的某日"+begin_time);
        String beginTime="";
        //获取今年是哪年
        String yearToday = new SimpleDateFormat("yyyy").format(date);
        //获取近30天的开始时间，赋值给beginTime
        if (begin_time >= 1){//判断近30天开始时间，是在同一个月，直接 拼接
                //把近30天的开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym +'0'+ String.valueOf(begin_time);
        }
        else {//近30天的开始时间不是在同一个月
            if (begin_time == 0){//不在同一个月,在上个月月底
                //获取上个月天数
                int lastMonthdays=getLastMonthDays();
                begin_time = lastMonthdays;
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time);
            }else if (begin_time < 0 && begin_time> -28 ){
                int begin_time2= -(begin_time);
                int lastMonthdays=getLastMonthDays();
                int begin_time3= lastMonthdays - begin_time2;
                if (begin_time3 <10){
                    //转String 拼接年月日
                    beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ "0" + String.valueOf(begin_time3);
                }else {
                    //转String 拼接年月日
                    beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
                }
            }else if (begin_time == -28 ){
                int begin_time2= -(begin_time);
                if (getLastMonthDays() == 28){
                    //转String 拼接年月日
                    beginTime = yearToday + "-01-31";
                }else {
                    //获取上个月的天数
                    int lastMonthdays=getLastMonthDays();
                    int begin_time3= lastMonthdays - begin_time2;
                    if (getLastMonth()<10){
                        //转String 拼接年月日
                        beginTime = yearToday +"-" + "0" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
                    }else {
                        //转String 拼接年月日
                        beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ "0"+  String.valueOf(begin_time3);
                    }

                }
            }
        }
        System.out.println("近30天的开始时间："+beginTime);
        //传递--开始时间和今日时间
        List<Artical> list= articalMapper.selectByThisWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
        System.out.println("查询近30天的数据--结束】");
    }

    //获取上个月是哪月--int
    public int getLastMonth(){
//        Date date = new Date();
        //获取这个月是哪月
        String monthToday = new SimpleDateFormat("MM").format(date);
        int  month=Integer.parseInt(monthToday);
        //计算上个月是哪月
        int lastMonth = month - 1;
        return lastMonth;
    }
    //获取上个月的天数--int
    public int getLastMonthDays(){
//        Date date = new Date();
//        //获取这个月是哪月
//        String monthToday = new SimpleDateFormat("MM").format(date);
//        int  month=Integer.parseInt(monthToday);
//        //计算上个月是哪月
        int lastMonth = getLastMonth();
        //判断上个月多少天
        int lastMonthDays=0;
        if (lastMonth == 7 || lastMonth == 8 || lastMonth == 10 || lastMonth == 12 || lastMonth == 1 || lastMonth == 3 || lastMonth == 5 ){
            lastMonthDays =31;
        }else if (lastMonth == 9 || lastMonth == 11 || lastMonth == 4 || lastMonth == 6 ){
            lastMonthDays =30;
        }else if (lastMonth == 2){
            //获取今年是哪年
            String yearToday = new SimpleDateFormat("yyyy").format(date);
            int  year=Integer.parseInt(yearToday);
            //判断今年是闰年还是平年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                System.out.println(year + "是闰年");
                lastMonthDays=29;
            } else {
                System.out.println(year + "是平年");
                lastMonthDays=28;
            }
        }
        return lastMonthDays;
    }

    //获取时间范围的数据
    public void getTimeRangeData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【你选择的时间范围的数据-开始:");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Integer pageNum= Integer.valueOf(req.getParameter("currentPageToday"));
        PageHelper.startPage(pageNum,6);//第一页,每页6条
        String timeRange = req.getParameter("timeRange");
        String beginTime= timeRange.substring(0,10);
        String endTime= timeRange.substring(13,23);
        System.out.println(beginTime);
        System.out.println(endTime);
        List<Artical> list= articalMapper.selectByTimeRange(beginTime,endTime);
        resp.getWriter().println(JSONArray.parseArray(JSON.toJSON(list).toString()));
        System.out.println("时间范围数据查询结束】");
    }

}
