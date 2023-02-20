import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
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

    @org.junit.Test
    public void getUser1() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser1(1);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser2(1, "xiaobo");
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser3() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser3(1, "xiaobo");
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser4() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUser4(1, "xiaobo");
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser5() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> user = mapper.selectUser5(Arrays.asList(1,3));
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser6() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> user = mapper.selectUser6(Arrays.asList(1,3));
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser7() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> user = mapper.selectUser7(new Integer[]{1,3});
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser8() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User use = new User();
            use.setId(3);
            List<User> user = mapper.selectUser8(1, use);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser9() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User use = new User();
            use.setId(3);
            List<User> user = mapper.selectUser9(use);
            System.out.println(user);
        }
    }

    @org.junit.Test
    public void getUser10() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User use = new User();
            use.setId(3);
            List<User> user = mapper.selectUser10(1, use);
            System.out.println(user);
        }
    }


}
