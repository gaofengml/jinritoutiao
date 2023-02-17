package util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Admin;
import mapper.AdminMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DBUtil {
   public static SqlSession getSqlSession(){
       InputStream inputStream = null;
       SqlSession sqlSession=null;
       try {
           inputStream = Resources.getResourceAsStream("mybatis-config.xml");
           SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession=sqlSessionFactory.openSession(true);//参数true是为了开启自动提交事务
       } catch (IOException e) {
           e.printStackTrace();
       }
       return sqlSession;
   }

    public static void main(String[] args) {
        SqlSession sqlSession=getSqlSession();
        AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);
        PageHelper.startPage(4,3);//第一页,每页3条
        List<Admin> list= adminMapper.selectAll();
        for(Admin admin:list){
            System.out.println(admin);
        }
    }
}
