package cn.pqz.emsboot.service.system;

import cn.pqz.emsboot.entity.system.Role;
import cn.pqz.emsboot.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public int addRole(Role role){
        String name="ROLE_"+role.getName();
        role.setName(name);
        int i=roleMapper.insert(role);
        return i;
    }
    public int editRole(Role role){
        int i=roleMapper.updateById(role);
        return i;
    }
}
