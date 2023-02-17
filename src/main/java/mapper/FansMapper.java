package mapper;

import entity.Fans;

import java.util.List;

public interface FansMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fans record);

    int insertSelective(Fans record);

    Fans selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Fans record);

    int updateByPrimaryKey(Fans record);

    List<Fans> selectAll();

    int getTotalPageNum();
}