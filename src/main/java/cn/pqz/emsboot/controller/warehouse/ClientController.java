package cn.pqz.emsboot.controller.warehouse;

import cn.pqz.emsboot.entity.system.RespBean;
import cn.pqz.emsboot.entity.warehouse.Client;
import cn.pqz.emsboot.entity.warehouse.OrderList;
import cn.pqz.emsboot.mapper.ClientMapper;
import cn.pqz.emsboot.mapper.OrderMapper;
import cn.pqz.emsboot.service.warehouse.ClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ClientService clientService;

    /**
     * 获取客户列表
     * @param name
     * @return
     */
    @GetMapping("/clientList")
    public RespBean getClientList(@RequestParam("clientName") String name) {
        RespBean respBean = null;
        try {
           List<Client> clientList = clientService.getClientList(name);
           respBean = RespBean.ok("", clientList);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("获取客户列表失败");
        }
        return respBean;
    }

    /**
     * 获取用户的订单
     * @param id
     * @return
     */
    @GetMapping("/getOrderByCid/{id}")
    public RespBean getOrderById(@PathVariable("id") Integer id){
        RespBean respBean=null;
        try{
            List<OrderList> orders=orderMapper.getOrderByCid(id);
            respBean=RespBean.ok("",orders);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("订单查询失败");
        }
        return respBean;
    }

    @PostMapping("/addClient")
    public RespBean addClient(@RequestBody Client client) {
        RespBean respBean = null;
        int i = clientMapper.insert(client);
        if (i != 0) {
            respBean = RespBean.ok("添加成功");
        } else
            respBean = RespBean.error("添加失败");
        return respBean;
    }
    @PutMapping("/editClient")
    public RespBean editClient(@RequestBody Client client){
        RespBean respBean=null;
        int i=clientMapper.updateById(client);
        if (i!=0)
            respBean=RespBean.ok("修改成功");
        else
            respBean=RespBean.error("修改失败");
        return respBean;
    }
    @PutMapping("/enterBlacklist/{id}")
    public RespBean enterBlacklist(@PathVariable("id") Integer id){
        RespBean respBean=null;
        int i=clientService.enterBlack(id);
        if (i!=0)
            respBean=RespBean.ok("受理成功");
        else
            respBean=RespBean.error("受理失败");
        return respBean;
    }

    @GetMapping("/getBlacklist")
    public RespBean getBlacklist(){
        RespBean respBean=null;
        try{QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("enabled",false);
        List<Client> clients=clientMapper.selectList(queryWrapper);
        respBean=RespBean.ok("",clients);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("查询失败");
        }
        return respBean;
    }


}
