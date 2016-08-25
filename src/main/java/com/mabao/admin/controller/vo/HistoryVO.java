package com.mabao.admin.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mabao.admin.pojo.History;
import com.mabao.admin.util.CustomDateSerializer;
import com.mabao.admin.util.VoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryVO {
    private String username;            //操作人员
    private Date operationTime;         //操作时间
    private String wechart;             //微信公众号
    private String ipAddress;           //ip地址
    private String operation;           //操作内容

    public static HistoryVO generateBy(History history) {
        HistoryVO vo = VoUtil.copyBasic(HistoryVO.class, history);
        assert vo != null;
        vo.setUsername(history.getAdmin().getUsername());
        vo.setWechart(history.getAdmin().getWechart());
        return vo;
    }

    public static List<HistoryVO> generateBy(List<History> historyList) {
        List<HistoryVO> list = new ArrayList<>();
        for (History h : historyList) {
            list.add(generateBy(h));
        }
        return list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
