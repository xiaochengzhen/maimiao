package com.example.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplusdemo.mapper.TableUserMapper;
import com.example.mybatisplusdemo.mapper.UserDao;
import com.example.mybatisplusdemo.model.PageUtils;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.User;
import com.example.mybatisplusdemo.service.UserService;
import com.github.fashionbrot.validated.annotation.Validated;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = MybatisPlusDemoApplication.class)
class MybatisPlusDemoApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private TableUserMapper tableUserMapper;

    @Test
    public void getUser() {
         User user = userService.listUser();
         System.out.println(user);
     }

    @Test
    public void listUserByParam() {
        List<User> list = userService.listUserByParam();
        System.out.println(list);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUserName("123111211212");
        user.setAge1(10);
       userService.save1(user);
    }


    @Test
    public void update() {//字段为null ，不修改
        User user = new User();
        user.setUserName("");
        user.setId(50L);
        user.setAge1(11);
        user.setVersion(2);
        user.setCompanyId(1);
      //  userService.update(user);
        boolean b = userService.updateById(user);
        System.out.println("=============="+b);
    }

    @Test
    public void delete() {
      userService.delete(6L);
    }

    @Test
    public void saveOrUpdate() {
        User user = new User();
        user.setUserName("Jone");
        user.setAge1(100);
        user.setId(2L);
        userDao.save(user);
    }

    @Test
    public void saveBatch() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("12301");
        user1.setAge1(1011334);
        user1.setId(51L);
        User user2 = new User();
        user2.setUserName("Jone611111");
        user2.setAge1(102);
      /*  user2.setId(36L);*/
        list.add(user1);
        list.add(user2);
      //  userService.saveBatch(list);

       // userService.updateBatchById(list);

      // userService.saveOrUpdate(user1);//只能根据id判断
      // userService.saveOrUpdateBatch(list);//只能根据id判断
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq("user_name", user1.getUserName());
        wrapper.eq("age", user1.getAge1());
    //    wrapper.set("id", user1.getId());
        userService.saveOrUpdate(user1, wrapper);//update 的时候默认不添加id 的 set

    }

    @Test
    public void listUserMsg() {
        List<User> users = userDao.selectList(null);
        System.out.println(users);
    }

    @Test
    public void saveUserTable() {
        TableUser user = new TableUser();
        user.setUserName("xxx");
        //user.setAge(null);
        user.setId(1L);
        tableUserMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void updateUserTable() {
        TableUser user = new TableUser();
        user.setUserName("Jone1");
        user.setAge(100);
        user.setId(60L);
        user.setVersion(4);
        tableUserMapper.updateById(user);
        System.out.println(user.getId());
    }

    @Test
    public void select() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", "Jone1");
        queryWrapper.clear();
        queryWrapper.eq("age", 10);
        TableUser tableUser = tableUserMapper.selectOne(queryWrapper);
        System.out.println(tableUser);

    }

    @Test
    public void updateWrapper() {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        /*updateWrapper.set("user_name", "Jone1");
        updateWrapper.eq("age", "2");*/
        updateWrapper.set("email", null);
        TableUser tableUser = new TableUser();
        tableUser.setAge(222);
        tableUser.setId(2L);
        tableUserMapper.update(tableUser, updateWrapper);

    }

    @Test
    public void deleted() {
        List<Integer> ids = new ArrayList<>();
        ids.add(56);
        ids.add(57);
        tableUserMapper.deleteBatchIds(ids);
        List<TableUser> tableUsers = tableUserMapper.selectList(null);
        System.out.println(tableUsers.size());
    }

    @Test
    public void page() {
        PageHelper.startPage(1, 5);
        List<TableUser> tableUsers = tableUserMapper.selectList(null);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        PageInfo pageInfo = new PageInfo(tableUsers);
   //     PageUtils tableUserPageUtils = PageUtils.genByList(list);
        PageUtils tPageUtils = new PageUtils(pageInfo.getPageNum(), pageInfo.getPageSize(), (int)pageInfo.getTotal(), list);
        System.out.println(tPageUtils);

    }

    @Test
    public void saveUserTableTestTime() {
        TableUser user = new TableUser();
        user.setUserName("xxx");
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        user.setUpdateTime(date);
        user.setCreateTime(now);
        user.setUpdateTe(date.getTime());
        user.setCreateTe(now.toInstant(ZoneOffset.UTC).toEpochMilli());
        tableUserMapper.insertTableUser(user);
        System.out.println(user.getId());
    }

    @Test
    public void getUserTableTestTime() {
        TableUser tableUser = tableUserMapper.selectByPrimaryKey(2L);
        Date updateTime = tableUser.getUpdateTime();
        LocalDateTime createTime = tableUser.getCreateTime();
        System.out.println(updateTime);
        System.out.println(updateTime.getTime());
        System.out.println(createTime);
        System.out.println(createTime.toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(createTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void getUserTableTestTime2() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        Calendar calendar = Calendar.getInstance() ;
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        System.out.println(zoneOffset);
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        System.out.println(dstOffset);

    }

    @Test
    public void getUserTableTestTime3() throws ParseException {
        String s = "1970-01-01 00:00:00";
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now2);
        Date date = new Date();
        System.out.println(date);
        System.out.println(now.getHour());
        System.out.println(now2.getHour());
        System.out.println(date.getHours());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(s, dateTimeFormatter);
        System.out.println(parse);
        System.out.println(parse.toInstant(ZoneOffset.UTC).toEpochMilli());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse1 = simpleDateFormat.parse(s);
        System.out.println(parse1.toString());
        System.out.println(parse1.getTime());
    }

}
