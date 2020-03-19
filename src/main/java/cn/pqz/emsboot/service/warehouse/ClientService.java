package cn.pqz.emsboot.service.warehouse;

import cn.pqz.emsboot.entity.warehouse.Client;
import cn.pqz.emsboot.mapper.ClientMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientMapper clientMapper;
    public List<Client> getClientList(String name){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("enabled",true);
        queryWrapper.like("name",name);
        List<Client> clientList = clientMapper.selectList(queryWrapper);
        return clientList;
    }

    /**
     * 加入黑名单
     * @param id
     * @return
     */
    public Integer enterBlack(Integer id){
        Client client=clientMapper.selectById(id);
        Boolean enabled1=!client.getEnabled();
        client.setEnabled(enabled1);
        int i=clientMapper.updateById(client);
        return i;
    }

}
