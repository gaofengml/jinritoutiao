package mapper;

import entity.ReadFrom;

public interface ReadFromMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReadFrom record);

    int insertSelective(ReadFrom record);

    ReadFrom selectByPrimaryKey(Integer id);

    ReadFrom getReadFromData();

    int updateByPrimaryKeySelective(ReadFrom record);

    int updateByPrimaryKey(ReadFrom record);


}