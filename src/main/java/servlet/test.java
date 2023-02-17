package servlet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class test {
    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(date);
//        String time = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(date);
//        System.out.println(time);
//        String a=time.substring(8,10);
//        String hh=time.substring(11,13);
//        int h=Integer.parseInt(hh);
//
//        String mm=time.substring(14,16);
//        int m=Integer.parseInt(mm);
//
//        String ss=time.substring(17,19);
//        int s=Integer.parseInt(ss);
//
//        int allS=h*24*60*60+m*60+s;
//        System.out.println(a);
//        System.out.println(hh);
//        System.out.println(mm);
//        System.out.println(ss);
//
//        System.out.println(allS);
//        int shi=Integer.parseInt(time.substring(12,14));
//        System.out.println(shi);


//        Date date=new Date();
//
//        SimpleDateFormat sdf=new SimpleDateFormat("dd");
//
//        String todayTime_ym = new SimpleDateFormat("yyyy-MM-").format(date);
//        String beginTime = todayTime_ym + String.valueOf(1);
//        System.out.println(beginTime);
//        String timeRange = "2022-11-01-2022-11-11";
//        String beginTime= timeRange.substring(0,10);
//        String endTime= timeRange.substring(11,21);
//        System.out.println(beginTime);
//        System.out.println(endTime);


        Date date = new Date();
        String time = new SimpleDateFormat("yyyyMMddhhMMss").format(date);
        String out_trade_no="389473729276466" +  time;
        System.out.println(out_trade_no);
//        String out_trade_no="389473729276466" + new Random(100) + "";

    }
}
