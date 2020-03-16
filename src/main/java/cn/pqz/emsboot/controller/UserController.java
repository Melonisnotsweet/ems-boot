package cn.pqz.emsboot.controller;

import cn.pqz.emsboot.entity.RespBean;
import cn.pqz.emsboot.entity.Role;
import cn.pqz.emsboot.entity.User;
import cn.pqz.emsboot.entity.User_role;
import cn.pqz.emsboot.mapper.RoleMapper;
import cn.pqz.emsboot.mapper.UserMapper;
import cn.pqz.emsboot.mapper.User_roleMapper;
import cn.pqz.emsboot.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private User_roleMapper user_roleMapper;
    /**
     * 展示用户列表
     * @param pageNum
     * @param size
     * @param query
     * @return
     */
    @GetMapping("/userList")
    public RespBean userList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("size") Integer size,
                             @RequestParam("query") String query){
        JSONObject obj=new JSONObject();
        obj.put("data",userService.userList(pageNum,size,query));
        obj.put("total",userMapper.selectCount(null));
        RespBean resp=RespBean.ok("",obj);
        return resp;
    }

    /**
     * 改变用户状态enable
     * @param id
     * @param enabled
     * @return
     */
    @PutMapping("/updateEnabled/{userId}/{userEnabled}")
    public RespBean updateEnabled(@PathVariable("userId") Integer id,
                                  @PathVariable("userEnabled") Boolean enabled){
        RespBean respBean=null;
        User user=new User();
        user.setId(id);
        user.setEnabled(enabled);
        int i=userMapper.updateById(user);
        if (i!=0){
            respBean=RespBean.ok("更新状态成功");
        }else{
            respBean=RespBean.error("更新状态失败");
        }
        return respBean;
    }
    @PostMapping("/addUser")
    public RespBean addUser(@RequestBody User user){
        RespBean respBean=null;
        int i=userService.addUser(user);
        if (i!=0){
            respBean=RespBean.ok("添加成功");
        }else{
            respBean=RespBean.error("添加失败");
        }
        return respBean;
    }
    @PutMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user){
        RespBean respBean=null;
        int i=userMapper.updateById(user);
        if (i!=0){
            respBean=RespBean.ok("修改成功");
        }else {
            respBean=RespBean.error("修改失败");
        }
        return respBean;
    }
    @DeleteMapping("/deleteUser/{id}")
    public RespBean deleteUser(@PathVariable("id") Integer id){
        RespBean respBean=null;
        int i=userMapper.deleteById(id);
        if (i!=0){
            respBean=RespBean.ok("删除成功");
        }else {
            respBean=RespBean.error("删除失败");
        }
        return respBean;
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping("/allRole")
    public RespBean allRole(){
        RespBean respBean=null;
        List<Role> allRole = roleMapper.selectList(null);
        if (allRole!=null)
            respBean=RespBean.ok("",allRole);
        else
            respBean=RespBean.error("获取错误");
        return respBean;
    }
    @PutMapping("/addUr/{uid}/{rid}")
    public RespBean addUr(@PathVariable("uid") Integer uid,
                          @PathVariable("rid") Integer rid){
        RespBean respBean=null;
        QueryWrapper query=new QueryWrapper();
        query.eq("uid",uid);
        user_roleMapper.delete(query);
        User_role ur=new User_role();
        User user=userMapper.selectById(uid);
        Role role=roleMapper.selectById(rid);
        user.setRoleName(role.getNameZh());//在user表中设置名称
        userMapper.updateById(user);//更新user表
        ur.setUid(uid);
        ur.setRid(rid);
        int i = user_roleMapper.insert(ur);
        if (i!=0)
            respBean=RespBean.ok("添加角色成功");
        else
            respBean=RespBean.error("添加角色失败");
        return respBean;

    }
}
