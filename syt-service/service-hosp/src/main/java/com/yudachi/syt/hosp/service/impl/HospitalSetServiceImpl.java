package com.yudachi.syt.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yudachi.model.model.hosp.HospitalSet;
import com.yudachi.syt.hosp.mapper.HospitalSetMapper;
import com.yudachi.syt.hosp.service.HospitalSetService;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
}
