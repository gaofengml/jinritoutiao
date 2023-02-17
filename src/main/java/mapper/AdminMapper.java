package mapper;

import entity.Admin;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer admin_id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer admin_id);

    Admin selectById(Integer admin_id);

    Admin selectByAdminName(String name);

    int selectByName(String name);

    List<Admin> selectAll();

    int getTotalPageNum();

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}