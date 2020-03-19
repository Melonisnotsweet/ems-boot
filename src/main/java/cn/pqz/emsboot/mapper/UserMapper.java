package cn.pqz.emsboot.mapper;

import cn.pqz.emsboot.entity.system.Role;
import cn.pqz.emsboot.entity.system.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);

}