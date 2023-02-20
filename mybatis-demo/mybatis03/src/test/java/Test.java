import com.example.mybatis.mapper.DeptMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
import com.example.mybatis.model.dto.DeptUser;
import com.example.mybatis.model.dto.UserDept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 08:00
 */
public class Test {

    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before() {
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    //一对多
    @org.junit.Test
    public void getUser1() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<UserDept> user = mapper.selectUserAndDept();
            System.out.println(user);
        }
    }
    //多对一
    @org.junit.Test
    public void getUser2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            List<DeptUser> user = mapper.listDetpUser();
            System.out.println(user);
        }
    }
    //嵌套   和多对一的区别是，如果是分页查询，多对一不准确，还的用嵌套查询，
    @org.junit.Test
    public void getUser3() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DeptMapper mapper = session.getMapper(DeptMapper.class);
            List<DeptUser> user = mapper.listDetpUser2();
            System.out.println(user);
        }
    }
}
