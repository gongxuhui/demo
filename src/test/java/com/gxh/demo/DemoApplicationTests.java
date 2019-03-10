package com.gxh.demo;

import com.gxh.demo.dao.AssetDao;
import com.gxh.demo.dao.UserDao;
import com.gxh.demo.entity.Asset;
import com.gxh.demo.entity.User;
import com.gxh.demo.service.ExcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@EnableAutoConfiguration
public class DemoApplicationTests {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AssetDao assetDao;

    @Autowired
    private ExcelService excelService;

    @Test
    public void getUser(){
        List<User> user = userDao.getUser();
        System.out.println(user);
    }
    @Test
    public void insertUser(){
        User user = new User();
        user.setName("gongxuhui");
        user.setAge(26);
        user.setEmail("gongxuhui@aliyun.com");
        userDao.insert(user);
    }
    @Test
    public void insertAsset() throws Exception {
        excelService.getExcelData();
    }

    @Test
    public void deleteUser(){
        userDao.deleteById(8);
    }

    @Test
    public void selectCount(){
        User userCondition = new User();

    }



    @Test
    public void contextLoads() {
    }

}
