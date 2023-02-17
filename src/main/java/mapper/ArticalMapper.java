package mapper;

import entity.Artical;


import java.util.Date;
import java.util.List;

public interface ArticalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Artical record);

    int insertSelective(Artical record);

    Artical selectByPrimaryKey(Integer id);

    Artical selectByArticleId(Integer id);

    List<Artical> selectAll();

    List<Artical> selectByToday(String time);

    List<Artical> selectByThisWeek(String beginTime,String endTime);

    List<Artical> selectBySevenDays(String beginTime,String endTime);

    List<Artical> selectByMonthDays(String beginTime,String endTime);

    List<Artical> selectByTimeRange(String beginTime,String endTime);

    int getTotalPageNum();

    int getTotalPageNumNr();

    int getTotalNumToday(String time);

    int getTotalNumWeek(String beginTime,String endTime);

    int getTotalNumSevenDays(String beginTime,String endTime);

    int getTotalNumMonthDays(String beginTime,String endTime);

    int getTotalNumTimeRange(String beginTime,String endTime);

    int updateByPrimaryKeySelective(Artical record);

    int updateByPrimaryKeyWithBLOBs(Artical record);

    int updateByPrimaryKey(Artical record);

    int updateReadNumByArticleId(Integer article_id);




}