package com.mabao.admin.util.aop;


public interface WriteInLogService {
    void writeOperation(String operation);

    void deleteSomeAdmins(String ids);
}
