package mapper;

import entity.Pingdao;

public interface PingdaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pingdao record);

    int insertSelective(Pingdao record);

    Pingdao selectByPrimaryKey(Integer id);

    Pingdao selectByName(String name);

    int updateByPrimaryKeySelective(Pingdao record);

    int updateByPrimaryKey(Pingdao record);


}