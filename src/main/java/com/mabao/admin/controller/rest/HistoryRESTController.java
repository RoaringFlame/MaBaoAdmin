package com.mabao.admin.controller.rest;

import com.mabao.admin.controller.vo.HistoryVO;
import com.mabao.admin.pojo.History;
import com.mabao.admin.service.HistoryService;
import com.mabao.admin.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/history")
public class HistoryRESTController {
    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/searchHistory", method = RequestMethod.GET)
    public PageVO<HistoryVO> showSelectGoods(
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String startStr,
            @RequestParam(required = false, defaultValue = "") String endStr,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "7") int pageSize) {
        Page<History> historyPage;
        if (!"".equals(startStr) && !"".equals(endStr)) {
            startStr += " 00:00:00";
            endStr += " 23:59:59";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            Date endTime = null;
            try {
                startTime = formatter.parse(startStr);
                endTime = formatter.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            historyPage = this.historyService.searchHistory(username, startTime, endTime, page - 1, pageSize);
//不用判断时间前后
//            if (startTime.before(endTime)) {
//                historyPage = this.historyService.searchHistory(username, startTime, endTime, page - 1, pageSize);
//            } else {
//                historyPage = this.historyService.serchHistory(username, page - 1, pageSize);
//            }
        } else {
            historyPage = this.historyService.serchHistory(username, page - 1, pageSize);
        }
        PageVO<HistoryVO> voPage = new PageVO<>();
        voPage.toPage(historyPage);
        voPage.setItems(HistoryVO.generateBy(historyPage.getContent()));
        return voPage;
    }
}
