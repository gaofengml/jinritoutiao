package mapper;

import entity.FansRead;

public interface FansReadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FansRead record);

    int insertSelective(FansRead record);

    FansRead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FansRead record);

    int updateByPrimaryKey(FansRead record);
}