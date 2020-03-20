package cn.pqz.emsboot.controller.warehouse;

import cn.pqz.emsboot.entity.system.RespBean;
import cn.pqz.emsboot.entity.warehouse.OrderList;
import cn.pqz.emsboot.mapper.OrderMapper;
import cn.pqz.emsboot.service.warehouse.OrderService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表
     * @param pageNum
     * @param size
     * @param query
     * @return
     */
    @GetMapping("/orderList/")
    public RespBean orderList(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("size") Integer size,
                              @RequestParam("query") String query){
        RespBean respBean=null;
        JSONObject obj=new JSONObject();
        obj.put("data",orderService.orderList(pageNum,size,query));
        obj.put("total",orderMapper.selectCount(null));
        respBean=RespBean.ok("",obj);
        return respBean;
    }

    /**
     * 新建生产远程搜索
     * @param name
     * @return
     */
    @GetMapping("/orders")
    public RespBean orders(@RequestParam String name){
        RespBean respBean=null;
        try{QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("orderState",1);
        queryWrapper.like("name",name);
        List<OrderList> orders=orderMapper.selectList(queryWrapper);
        respBean=RespBean.ok("",orders);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取失败");
        }
        return respBean;
    }
    @PostMapping("/addOrder")
    public RespBean addOrder(@RequestBody JSONObject json){
        RespBean respBean=null;
        try{
            String name=json.getString("name");
            Double price=json.getDouble("price");
            Integer count=json.getInteger("count");
            Integer cid=json.getInteger("clientId");
            orderService.addOrder(name,price,count,cid);
            respBean=RespBean.ok("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("添加失败");
        }
        return respBean;
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @PutMapping("/editOrder")
    public RespBean editOrder(@RequestBody OrderList order){
        RespBean respBean=null;
        int i=orderMapper.updateById(order);
        if (i==0){
            respBean=RespBean.error("修改失败");
        }else {
            respBean=RespBean.ok("修改成功");
        }
        return respBean;
    }
    @DeleteMapping("/deleteOrder/{id}")
    public RespBean deleteOrder(@PathVariable("id") Integer id){
        RespBean respBean=null;
        int i=orderMapper.deleteById(id);
        if (i==0){
            respBean=RespBean.error("删除失败");
        }else {
            respBean=RespBean.ok("删除成功");
        }
        return respBean;
    }

}
