package com.mabao.admin.util.aop;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.pojo.History;
import com.mabao.admin.repository.AdminRepository;
import com.mabao.admin.repository.HistoryRepository;
import com.mabao.admin.util.security.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WriteInLogServiceImpl implements WriteInLogService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminInfoService adminInfoService;

    private Admin getNowAdmin() {
        return this.adminRepository.findByUsername(this.adminInfoService.getAdminUsername());
    }

    @Override
    public void writeOperation(String operation) {
        History history = new History();
        Date date = new Date();
        history.setOperationTime(date);
        history.setOperation(operation);
        Admin admin = this.getNowAdmin();
        history.setAdmin(admin);
        history.setIpAddress(this.adminInfoService.getRemoteAddress());
        this.historyRepository.saveAndFlush(history);

        admin = this.adminRepository.findOne(admin.getId());
        admin.setOperation(operation);
        admin.setOperationTime(date);
        this.adminRepository.saveAndFlush(admin);
    }

    @Override
    public void deleteSomeAdmins(String ids) {
        String opt = "删除后台用户";
        String[] strs = ids.split(",");
        for (String adminId : strs) {
            opt += this.adminRepository.findOne(Long.decode(adminId)).getUsername();
        }
        opt.substring(0,opt.length()-1);
        this.writeOperation(opt);
    }
}
