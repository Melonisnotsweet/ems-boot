package cn.pqz.emsboot.service.system;

import cn.pqz.emsboot.entity.system.Menu;
import cn.pqz.emsboot.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @CachePut(key = "#root.methodName")
    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
    public List<Menu> menuList(){return menuMapper.menuList();}
    public List<Menu> allMenuList(String name){return menuMapper.allMenuList(name);}
}
