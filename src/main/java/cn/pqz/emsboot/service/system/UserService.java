package cn.pqz.emsboot.service.system;

import cn.pqz.emsboot.entity.system.User;
import cn.pqz.emsboot.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param username
     * @return 用户信息及其拥有的权限
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param size
     * @return
     */
    public List<User> userList(Integer pageNum, Integer size, String query) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", query);
        IPage<User> page = userMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        List<User> users = page.getRecords();
        return users;
    }

    public Integer addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pawd = encoder.encode(user.getPassword());
        user.setPassword(pawd);
        int i = userMapper.insert(user);
        return i;
    }


}
