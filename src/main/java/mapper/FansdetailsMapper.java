package mapper;


import entity.Fansdetails;

import java.util.List;

public interface FansdetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fansdetails record);

    int insertSelective(Fansdetails record);

    Fansdetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fansdetails record);

    int updateByPrimaryKey(Fansdetails record);

    List<Fansdetails> selectAll();

    int getTotalPageNum();

}