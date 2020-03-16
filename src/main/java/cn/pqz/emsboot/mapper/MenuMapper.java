package cn.pqz.emsboot.mapper;

import cn.pqz.emsboot.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getAllMenus();
    List<Menu> menuList();
    List<Menu> allMenuList();
    List<Menu> menuTree();
}