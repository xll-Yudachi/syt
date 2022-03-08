package com.yudachi.syt.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yudachi.common.result.Result;
import com.yudachi.model.model.hosp.HospitalSet;
import com.yudachi.model.vo.hosp.HospitalSetQueryVo;
import com.yudachi.service.utils.MD5;
import com.yudachi.syt.hosp.service.HospitalSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/hosp/hospitalSet")
public class HospitalSetController {
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("/findAll")
    public Result findAllHospitalSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("/delete/{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if (flag){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value = "条件分页查询")
    @PostMapping("/findPageHospSet/{page}/{size}")
    public Result findPageHospSet(@PathVariable long page, @PathVariable long size, @RequestBody(required = false)HospitalSetQueryVo hospitalSetQueryVo){
        Page<HospitalSet> Ipage = new Page<>(page, size);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(hospitalSetQueryVo.getHosname()), "hosname", hospitalSetQueryVo.getHosname());
        wrapper.eq(StringUtils.isNotEmpty(hospitalSetQueryVo.getHoscode()),"hoscode", hospitalSetQueryVo.getHoscode());

        Page<HospitalSet> res = hospitalSetService.page(Ipage, wrapper);
        return Result.ok(res);
    }

    @ApiOperation(value = "添加设置")
    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        hospitalSet.setStatus(1);
        // 签名密钥
        ThreadLocalRandom random = ThreadLocalRandom.current();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        boolean flag = hospitalSetService.save(hospitalSet);
        if (flag){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation(value = "根据id获取设置")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation(value = "修改设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if(flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量删除设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

}
