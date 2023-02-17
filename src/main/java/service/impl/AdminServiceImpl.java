package service.impl;

import entity.Admin;
import entity.ResultData;
import mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import service.AdminService;
import util.DBUtil;
import util.MD5Util;

public class AdminServiceImpl implements AdminService {

    SqlSession sqlSession= DBUtil.getSqlSession();

    @Override
    public ResultData userLogin(Admin admin) {
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        Admin admin1=adminMapper.selectByAdminName(admin.getAdmin_name());//先查询当前用户名是否存在
        if(admin1 != null){
            if (MD5Util.checkPassword(admin.getAdmin_password(),admin1.getAdmin_password())){
                return new ResultData("200","登录成功");
            }else {
                return new ResultData("400","密码错误，请重新输入");
            }
        }else {
            return new ResultData("300","用户不存在");
        }
    }
}
