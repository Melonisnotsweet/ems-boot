package cn.pqz.emsboot.controller.system;

import cn.pqz.emsboot.entity.system.Menu;
import cn.pqz.emsboot.entity.system.RespBean;
import cn.pqz.emsboot.mapper.MenuMapper;
import cn.pqz.emsboot.mapper.RoleMapper;
import cn.pqz.emsboot.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/menuList")
    public RespBean menuList(){
        RespBean result=null;
        List<Menu> menuList=menuService.menuList();
        if (menuList==null){
            result=RespBean.error("获取菜单列表失败");
        }else
        result=RespBean.ok("",menuList);
        return result;
    }

    /**
     * 获取权限列表
     * @return
     */
    @GetMapping("/staff/powList/")
    public RespBean allMenuList(@RequestParam("query") String query){
        RespBean respBean=null;
        List<Menu> allMenuList=menuService.allMenuList(query);
        if (allMenuList==null){
            respBean=RespBean.error("获取权限列表失败");
        }else
        respBean=RespBean.ok("",allMenuList);
        return respBean;
    }

    /**
     * 是否在菜单栏显示
     */
    @PutMapping("/staff/changeMenuEnable/{id}/{enable}")
    public RespBean changeMenuEnable(@PathVariable("id") Integer id,
                                     @PathVariable("enable") Boolean enable){
        RespBean respBean=null;
        Menu menu=new Menu();
        menu.setId(id);
        menu.setEnable(enable);
        int i=menuMapper.updateById(menu);
        if (i!=0){
            respBean=RespBean.ok("更新状态成功");
        }else{
            respBean=RespBean.error("更新状态失败");
        }
        return respBean;
    }
    /**
     * 添加权限
     */
    @PostMapping("/staff/addPow")
    public RespBean addPow(@RequestBody Menu menu){
        RespBean respBean=null;
        int i=menuMapper.insert(menu);
        if (i!=0){
            respBean=RespBean.ok("权限添加成功");
        }
        else {
            respBean=RespBean.error("权限添加失败");
        }
        return respBean;
    }
    /**
     * 修改权限
     */
    @PutMapping("/staff/editPow")
    public RespBean editPow(@RequestBody Menu menu){
        RespBean respBean=null;
        int i=menuMapper.updateById(menu);
        if (i!=0){
            respBean=RespBean.ok("权限修改成功");
        }
        else {
            respBean=RespBean.error("权限修改失败");
        }
        return respBean;
    }
    @DeleteMapping("/staff/deletePow/{id}")
    public RespBean deletePow(@PathVariable("id") Integer id ){
        RespBean respBean=null;
        int i=menuMapper.deleteById(id);
        if (i!=0){
            respBean=RespBean.ok("权限删除成功");
        }
        else {
            respBean=RespBean.error("权限删除失败");
        }
        return respBean;
    }

}
