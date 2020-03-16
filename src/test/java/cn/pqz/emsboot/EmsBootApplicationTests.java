package cn.pqz.emsboot;

import cn.pqz.emsboot.entity.Menu;
import cn.pqz.emsboot.entity.Role;
import cn.pqz.emsboot.entity.User;
import cn.pqz.emsboot.mapper.MenuMapper;
import cn.pqz.emsboot.mapper.RoleMapper;
import cn.pqz.emsboot.mapper.UserMapper;
import cn.pqz.emsboot.mapper.User_roleMapper;
import cn.pqz.emsboot.service.MenuService;
import cn.pqz.emsboot.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmsBootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private User_roleMapper user_roleMapper;
    @Test
    void contextLoads() {
    }
//    @Test
//    void loadUserByName(){
//        System.out.println(userMapper.loadUserByUsername("admin"));
//    }
//    @Test
//    void getUserRolesByUid(){
//        System.out.println(userMapper.getUserRolesByUid(1));
//    }
//    @Test
//    void loadUserByUsername(){
//        System.out.println(userService.loadUserByUsername("admin"));
//    }
//    @Test
//    void getAllMenus(){
//        System.out.println(menuMapper.getAllMenus());
//    }
//    @Test
//    void MeunService(){
//        System.out.println(menuService.getAllMenus());
//    }
//    @Test
//    void UserList(){
//        IPage<User> page=userMapper.selectPage(new Page<>(2,1),null);
//        System.out.println(page);
//        System.out.println(userMapper.selectCount(null));
//        List<User> users=page.getRecords();
//        for (User user:users){
//            System.out.println(user);
//        }
//
//    }
//    @Test
//    void updateStatus(){
//        User user=new User();
//        user.setId(3);
//        user.setEnabled(false);
//        int i=userMapper.updateById(user);
//        System.out.println(i);
//
    @Test
    void menuList(){
        System.out.println(menuMapper.menuTree());
    }
    @Test
    void testur(){
        System.out.println(user_roleMapper.selectList(null));
    }

}
