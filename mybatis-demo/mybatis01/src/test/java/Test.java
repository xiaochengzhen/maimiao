import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

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

    @org.junit.Test
    public void getUser1() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
           User user = session.selectOne("user.selectUser", 1);
            System.out.println(user);
        }

    }

    @org.junit.Test
    public void getUser2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser3() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser4() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {//true  默认自动提交
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser(1);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser5() {
        SqlSession session = sqlSessionFactory.openSession();//需要手动提交
        try{
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("agds");
            user.setAge(123);
            mapper.save(user);
            System.out.println(user);
            session.commit();
        } catch (Exception ex) {
            session.rollback();
            System.out.println(ex);
        } finally {
            session.close();
        }
    }
}
