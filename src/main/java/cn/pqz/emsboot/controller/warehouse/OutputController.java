package cn.pqz.emsboot.controller.warehouse;

import cn.pqz.emsboot.entity.system.RespBean;
import cn.pqz.emsboot.entity.warehouse.Output;
import cn.pqz.emsboot.entity.warehouse.OutputLog;
import cn.pqz.emsboot.mapper.OutputLogMapper;
import cn.pqz.emsboot.mapper.OutputMapper;
import cn.pqz.emsboot.service.warehouse.OutputService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    private OutputMapper outputMapper;
    @Autowired
    private OutputService outputService;
    @Autowired
    private OutputLogMapper outputLogMapper;
    /**
     * 获取所有生产线
     *
     * @return
     */
    @GetMapping("/findOutputs")
    public RespBean findOutputs() {
        RespBean respBean = null;
        try {
            List<Output> outputs = outputMapper.selectList(null);
            respBean = RespBean.ok("", outputs);
        } catch (Exception e) {
            e.printStackTrace();
            respBean = RespBean.error("获取生产线失败");
        }
        return respBean;
    }

    /**
     * 添加生产线
     * @param output
     * @return
     */
    @PostMapping("/addOutput")
    public RespBean addOutput(@RequestBody Output output) {
        RespBean respBean = null;
        int i = outputMapper.insert(output);
        if (i == 0)
            respBean = RespBean.error("添加失败");
        else
            respBean = RespBean.ok("添加成功");
        return respBean;
    }

    /**
     * 更新状态
     * @param state
     * @param id
     * @return
     */
    @PutMapping("/updateOutputState/{state}/{id}")
    public RespBean updateOutputState(@PathVariable("state") Boolean state,
                                      @PathVariable("id") Integer id) {
        RespBean respBean = null;
        try{
            outputService.updateOutputState(state,id);
            respBean=RespBean.ok("更新状态成功");
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("更新状态失败");
        }
        return respBean;
    }

    /**
     * 更新生产线信息
     * @param output
     * @return
     */
    @PutMapping("/updateOutput")
    public RespBean updateOutput(@RequestBody Output output){
        RespBean respBean=null;
        int i=outputMapper.updateById(output);
        if (i==0)
            respBean=RespBean.error("更新失败");
        else
            respBean=RespBean.ok("更新成功");
        return respBean;
    }

    /**
     * 删除生产线
     * @param id
     * @return
     */
    @DeleteMapping("/deleteOutput/{id}")
    public RespBean deleteOutputById(@PathVariable("id") Integer id ){
        RespBean respBean=null;
        int i=outputMapper.deleteById(id);
        if (i==0)
            respBean=RespBean.error("删除失败");
        else
            respBean=RespBean.ok("删除成功");
        return respBean;
    }

    @PostMapping("/insertOutput")
    public RespBean insertOutput(@RequestBody JSONObject json){
        RespBean respBean=null;
        try{
            Integer id=json.getInteger("id");
            Integer orderId=json.getInteger("orderId");
            Integer total=json.getInteger("total");
            String orderNum=json.getString("orderNum");
            String orderName=json.getString("orderName");
            outputService.insertOutput(id,orderId,total,orderNum,orderName);
            respBean=RespBean.ok("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("操作失败");
        }
        return  respBean;
    }

    @GetMapping("/outputLog/{oid}")
    public RespBean outputLog(@PathVariable("oid") Integer oid){
        RespBean respBean=null;
        try{
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("oid",oid);
            List<OutputLog> outputLogs =outputLogMapper.selectList(queryWrapper);
            respBean=RespBean.ok("",outputLogs);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取日志列表失败");
        }
        return respBean;
    }

    /**
     * 工作进度
     * @param id
     * @param outNow
     * @return
     */
    @PutMapping("/workOutput/{id}/{outNow}")
    public RespBean workOutput(@PathVariable("id") Integer id,
                               @PathVariable("outNow") Integer outNow){
        RespBean respBean=null;
        try{
            outputService.workOutput(id,outNow);
            respBean=RespBean.ok("记录成功，已更新数据");
        }catch (Exception e){
            respBean=RespBean.error("记录失败");
        }
        return respBean;
    }

    @PutMapping("/achieve")
    public RespBean achieve(@RequestBody JSONObject json){
        RespBean respBean=null;
        try {
            Integer id=json.getInteger("id");
            String orderNum=json.getString("orderNum");
            String orderName=json.getString("orderName");
            Integer total=json.getInteger("total");
            Double complete=json.getDouble("complete");
            outputService.achieve(id,orderNum,orderName,total,complete);
            respBean=RespBean.ok("受理成功,已清空生产线");
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("操作失败");
        }
        return respBean;
    }

}
