package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2022/9/6 07:57
 */
public interface UserMapper {
    User selectUser(@Param("id") Integer id);

    /*
    * 单个参数，可以不对应
    * */
    User selectUser1(Integer id);

    /*
     * 多个参数必须对应，mybatis会封装两个map    arg0， value   param1， value
     * */
    User selectUser2(Integer id, String username);

    /*
     * 多个参数必须对应，mybatis会封装两个map    arg0， value   param1， value
     * */
    User selectUser3(Integer id, String username);

    /*
     * 多个参数必须对应，@Param() 相当于重命名了
     * */
    User selectUser4(@Param("id") Integer id, @Param("username") String username);

    /*
     * 参数是list的情况，也会封装map ，   list， list
     * */
    List<User> selectUser5(List<Integer> ids);

    /*
     * 参数是list的情况，也会封装map ，   list， list,  @Param重名名
     * */
    List<User> selectUser6(@Param("ids") List<Integer> ids);

    /*
     * 参数是array的情况，也会封装map ，   array， array,  @Param重名名
     * */
    List<User> selectUser7(Integer[] ids);

    /*
     * 参数是组合的情况下, 用param1 param2   也可以用重命名
     * */
    List<User> selectUser8(Integer id, User user);

    /*
     * 参数对象， 不能用 param1， 一个参数的时候，不再封装成map了
     * */
    List<User> selectUser9(User user);

    /*
     * 参数是组合的情况下, 用param1 param2   也可以用重命名
     * */
    List<User> selectUser10(@Param("id") Integer id, @Param("user") User user);



}
