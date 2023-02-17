package servlet;

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

@WebServlet("/ArticalTotalCountServlet/*")
public class ArticalTotalCountServlet extends HttpServlet {
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

    //图文数据界面-获取所有数据个数
    public void getTotalPageNum_tuWen(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        int totalCount=articalMapper.getTotalPageNum();
        resp.getWriter().println(totalCount);
    }

    //内容列表界面-获取所有数据
    public void getTotalPageNum_nr(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        int totalCount=articalMapper.getTotalPageNumNr();//每页--10条数据
        resp.getWriter().println(totalCount);
    }

    //图文数据界面-获取今日所有数据--分页展示几页
    public void getTotalTodayNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【获取今日数据开始");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        Date date = new Date();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(time);
        int totalTodayCount=articalMapper.getTotalNumToday(time);
        resp.getWriter().println(totalTodayCount);
        System.out.println("获取今日数据结束】");
    }

    //图文数据界面-获取本周所有数据--分页展示几页
    public void getTotalWeekNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【计算本周数据数量开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        //后端算出今天是哪天
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("本周（计数）：今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
        //获取前端发来的今日是周几
        String today_string = req.getParameter("today");
        System.out.println("计数-获取前端发来的今日是周几："+today_string);
        //周几换成是数字
        int  today_int=Integer.parseInt(today_string);
        //今日减去周几，计算本周开始是某个月某日
        int begin_time=time_d_int-today_int +1;
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
                int begin_time3= lastMonthdays - begin_time2 +1;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
            }
        }
        System.out.println("计数:本周开始时间："+beginTime);
        int totalTodayCount=articalMapper.getTotalNumWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(totalTodayCount);
        System.out.println("计算本周数据数量---结束。。。】");
    }

    //图文数据界面-获取近7天所有数据--分页展示几页
    public void getTotalSevenDaysNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【计算近7天数据数量开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        //后端算出今天是哪天
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("近7天（计数）：今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
//        //获取前端发来的今日是周几
//        String today_string = req.getParameter("today");
//        System.out.println("计数-获取前端发来的今日是周几："+today_string);
//        //周几换成是数字
//        int  today_int=Integer.parseInt(today_string);
        //今日减去7天，计算近7天的开始时间是某个月某日
        int begin_time=time_d_int-7 +1;
        String beginTime="";
        //获取近7天的开始时间，赋值给beginTime
        if (begin_time >= 1){//判断近7天的开始时间，是在同一个月，直接 拼接
            if (begin_time <10 ){
                //把近7天的开始时间转换成：年-月-日
                String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
                beginTime = todayTime_ym +'0'+ String.valueOf(begin_time);
            }else {
                //把近7天的开始时间转换成：年-月-日
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
                int begin_time3= lastMonthdays - begin_time2 +1;
                //获取今年是哪年
                String yearToday = new SimpleDateFormat("yyyy").format(date);
                //转String 拼接年月日
                beginTime = yearToday +"-" + String.valueOf(getLastMonth()) +"-"+ String.valueOf(begin_time3);
            }
        }
        System.out.println("计数:近7天的开始时间："+beginTime);
        int totalTodayCount=articalMapper.getTotalNumWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(totalTodayCount);
        System.out.println("计算近7天数据数量--分几页展示---结束。。。】");
    }


    //图文数据界面-获取近30天所有数据--分页展示几页
    public void getTotalMonthDaysNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【计算近30天数据数量开始。。。");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        //后端算出今天是哪天
        //今日：年-月-日
        String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println("近30天（计数）：今天是:"+todayTime);
        //今日：哪天
        String time_d_string=new SimpleDateFormat("dd").format(date);
        //今日：哪天--换成数字计算
        int  time_d_int=Integer.parseInt(time_d_string);
//        //获取前端发来的今日是周几
//        String today_string = req.getParameter("today");
//        System.out.println("计数-获取前端发来的今日是周几："+today_string);
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
        System.out.println("计数:近30天的开始时间："+beginTime);
        int totalTodayCount=articalMapper.getTotalNumWeek(beginTime,todayTime);//sql语句错误，参数没获取到
        resp.getWriter().println(totalTodayCount);
        System.out.println("计算近30天数据数量--分几页展示---结束。。。】");
    }

    public int getLastMonth(){
//        Date date = new Date();
        //获取这个月是哪月
        String monthToday = new SimpleDateFormat("MM").format(date);
        int  month=Integer.parseInt(monthToday);
        //计算上个月是哪月
        int lastMonth = month - 1;
        return lastMonth;
    }
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

    //图文数据界面-获取时间范围的所有数据--分页展示几页
    public void getTotalTimeRangeNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("【获取时间范围数据--分页数--开始");
        SqlSession sqlSession= DBUtil.getSqlSession();
        ArticalMapper articalMapper=sqlSession.getMapper(ArticalMapper.class);
        String timeRange = req.getParameter("timeRange");
        String beginTime= timeRange.substring(0,10);
        String endTime= timeRange.substring(13,23);
        System.out.println(beginTime);
        System.out.println(endTime);
        int totalTodayCount=articalMapper.getTotalNumTimeRange(beginTime,endTime);
        resp.getWriter().println(totalTodayCount);
        System.out.println("获取时间范围数据--分页数--结束】");
    }


}
