package cn.pqz.emsboot.controller;

import cn.pqz.emsboot.entity.RespBean;
import cn.pqz.emsboot.entity.Role;
import cn.pqz.emsboot.mapper.Menu_roleMapper;
import cn.pqz.emsboot.mapper.RoleMapper;
import cn.pqz.emsboot.service.Menu_roleService;
import cn.pqz.emsboot.service.RoleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private Menu_roleMapper menu_roleMapper;
    @Autowired
    private Menu_roleService menuRoleService;
    @GetMapping("/roleList")
    public RespBean roleList(){
        RespBean respBean=null;
        List<Role> roleList=roleMapper.getRoleList();
        if (roleList==null){
            respBean=RespBean.error("获取角色列表失败");
        }else
            respBean=RespBean.ok("",roleList);
        return respBean;
    }
    @PostMapping("/addRole")
    public RespBean addRole(@RequestBody Role role){
        RespBean respBean=null;
        int i=roleService.addRole(role);
        if (i!=0){
            respBean=RespBean.ok("添加角色成功");
        }else
            respBean=RespBean.error("添加失败");
        return respBean;
    }
    @PutMapping("/editRole")
    public RespBean editRole(@RequestBody Role role){
        RespBean respBean=null;
//        role.setId(role.getId());
//        System.out.println(role);
        int i=roleService.editRole(role);
        if (i!=0){
            respBean=RespBean.ok("修改成功");
        }else
            respBean=RespBean.error("修改失败");
        return respBean;
    }
    @DeleteMapping("/deleteRole/{id}")
    public RespBean deleteRole(@PathVariable("id") Integer id){
        RespBean respBean=null;
        int i=roleMapper.deleteById(id);
        if (i!=0){
            respBean=RespBean.ok("删除成功");
        }else
            respBean=RespBean.error("删除失败");
        return respBean;
    }
    @DeleteMapping("/deletePow/{rid}/{mid}")
    public RespBean deletePow(@PathVariable("rid") Integer rid,
                              @PathVariable("mid") Integer mid){
        RespBean respBean=null;
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("rid",rid);
        queryWrapper.eq("mid",mid);
        int i=menu_roleMapper.delete(queryWrapper);
        if (i!=0){
            respBean=RespBean.ok("删除成功");
        }else
            respBean=RespBean.error("删除失败");
        return respBean;
    }

    /**
     * 为角色分配权限
     * @param json
     * @return
     */
    @PostMapping("/assPow")
    public RespBean assPow(@RequestBody JSONObject json){
        RespBean respBean=null;
        try{
            String rid=json.getString("roleId");
            String mids=json.getString("mids");
            menuRoleService.assPow(rid,mids);
            respBean=RespBean.ok("授权成功");
        }catch (Exception e){
            respBean=RespBean.error("授权失败");
            e.printStackTrace();
        }
        return respBean;
    }
}
