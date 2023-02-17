package mapper;

import entity.UserPictures;

import java.util.List;

public interface UserPicturesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPictures record);

    int insertSelective(UserPictures record);

    UserPictures selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPictures record);

    int updateByPrimaryKey(UserPictures record);

    List<UserPictures> selectAll();

    List<UserPictures> selectByCollected();

    int getTotalPageNumSuCai();

    int getTotalPageNumSuCaiCollected();

    int updateStatusById(UserPictures record);
}