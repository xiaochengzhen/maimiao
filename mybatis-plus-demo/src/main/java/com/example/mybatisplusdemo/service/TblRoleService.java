package com.example.mybatisplusdemo.service;

import com.example.mybatisplusdemo.model.TableUser;
import com.example.mybatisplusdemo.model.TblRole;
import com.example.mybatisplusdemo.model.TblRoleVO;

import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/21 10:25
 */
public interface TblRoleService {

    TblRoleVO getTblRoleVO(Integer roleId);
    List<TblRoleVO> listTblRoleVO();
    void saveTblRole(TblRole tblRole);

    void testSynTran();
}
