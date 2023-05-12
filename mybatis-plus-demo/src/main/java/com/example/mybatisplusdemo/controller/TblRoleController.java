package com.example.mybatisplusdemo.controller;

import com.example.mybatisplusdemo.model.ResultDTO;
import com.example.mybatisplusdemo.model.TblRole;
import com.example.mybatisplusdemo.model.TblRoleVO;
import com.example.mybatisplusdemo.model.TestQuery;
import com.example.mybatisplusdemo.service.TblRoleService;
import com.panpan.maimiaoautoconfigure.annotation.CheckUnique;
import com.panpan.maimiaoautoconfigure.annotation.Quote;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/21 10:30
 */
@RestController
@RequestMapping("/role")
public class TblRoleController {

    @Resource
    private TblRoleService tblRoleService;

    @Quote
    @GetMapping("/get/{id}")
    public ResultDTO getTblRole(@PathVariable Integer id) {
        ResultDTO resultDTO = new ResultDTO("200", "success", tblRoleService.getTblRoleVO(id));
        return resultDTO;
    }
    @Quote
    @GetMapping("/list")
    public ResultDTO listTblRole() {
        ResultDTO resultDTO = new ResultDTO("200", "success", tblRoleService.listTblRoleVO());
        return resultDTO;
    }
    @CheckUnique
    @PostMapping("/save")
    public ResultDTO saveTblRole(@RequestBody TblRole tblRole) {
        String msg = "";
        try {
            tblRoleService.saveTblRole(tblRole);
        } catch (RuntimeException e) {
            msg = e.getMessage();
        }
        ResultDTO resultDTO = new ResultDTO("200", "success", msg);
        return resultDTO;
    }

    @GetMapping("test")
    public void test(TestQuery testQuery){
        System.out.println(testQuery.getList());

    }
}
