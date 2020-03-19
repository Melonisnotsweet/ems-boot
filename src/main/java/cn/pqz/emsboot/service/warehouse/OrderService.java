package cn.pqz.emsboot.service.warehouse;

import cn.pqz.emsboot.entity.warehouse.Client;
import cn.pqz.emsboot.entity.warehouse.Client_order;
import cn.pqz.emsboot.entity.warehouse.OrderList;
import cn.pqz.emsboot.mapper.ClientMapper;
import cn.pqz.emsboot.mapper.Client_orderMapper;
import cn.pqz.emsboot.mapper.OrderMapper;
import cn.pqz.emsboot.util.OrderNumUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Client_orderMapper client_orderMapper;
    /**
     * 订单分页查询
     */
    public List<OrderList> orderList(Integer pageNum, Integer size, String query) {
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", query);
        IPage<OrderList> page = orderMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        List<OrderList> orderList = page.getRecords();
        return orderList;
    }
    /**
     * 添加订单
     * 新建时间，生成订单号，关联客户
     */
    public void addOrder(String name,Double price,Integer count,Integer cid){
        OrderList order=new OrderList();
        order.setName(name);
        order.setCount(count);
        order.setPrice(price);
        Date date=new Date();
        order.setDate(date);
        String orderNum= OrderNumUtil.GetRandom();
        order.setOrderNum(orderNum);
        order.setPay(false);
        order.setTransport(false);
        order.setOrderState(1);
        orderMapper.insert(order);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("orderNum",orderNum);
        OrderList one=orderMapper.selectOne(queryWrapper);
        Client_order co=new Client_order();
        co.setCid(cid);
        co.setOid(one.getId());
        client_orderMapper.insert(co);

    }
}
