package com.example.mybatisplusdemo.service.impl;

import com.example.mybatisplusdemo.mapper.TableUserMapper;
import com.example.mybatisplusdemo.mapper.TblRoleMapper;
import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.TblRole;
import com.example.mybatisplusdemo.model.TblRoleVO;
import com.example.mybatisplusdemo.service.TableUserService;
import com.example.mybatisplusdemo.service.TblRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/21 10:27
 */
@Service
public class TblRoleServiceImpl implements TblRoleService {
    @Resource
    private TblRoleMapper tblRoleMapper;
    @Resource
    private TableUserMapper tableUserMapper;
    @Lazy
    @Resource
    private TblRoleServiceImpl tblRoleService;

    @Override
    public TblRoleVO getTblRoleVO(Integer roleId) {
        TblRoleVO tblRoleVO = new TblRoleVO();
        TblRole tblRole = tblRoleMapper.selectById(roleId);
        BeanUtils.copyProperties(tblRole, tblRoleVO);
        return tblRoleVO;
    }

    @Override
    public List<TblRoleVO> listTblRoleVO() {
        List<TblRoleVO> tblRoleVOList = new ArrayList<>();
        List<TblRole> tblRoleList = tblRoleMapper.selectList(null);
        for (TblRole tblRole : tblRoleList) {
            TblRoleVO tblRoleVO = new TblRoleVO();
            BeanUtils.copyProperties(tblRole, tblRoleVO);
            tblRoleVOList.add(tblRoleVO);
        }
        return tblRoleVOList;
    }

    @Override
    public void saveTblRole(TblRole tblRole) {
        tblRoleMapper.insert(tblRole);
    }

    @Transactional
    @Override
    public void testSynTran() {
        TblRole tblRole = new TblRole();
        tblRole.setUserId(1);
        tblRole.setRoleCode("1");
        tblRole.setRoleName("1");
        tblRole.setUserName("1");
        tblRoleMapper.insert(tblRole);
        new Thread(()->{
            tblRoleService.testTableUser();
        }).start();
        System.out.println("==================================");

    }

  //  @Transactional
    public void testTableUser() {
        TableUser tableUser = new TableUser();
        tableUser.setId(1L);
        tableUser.setUserName("1");
        tableUserMapper.insert(tableUser);
        int i = 1/0;
    }
}
