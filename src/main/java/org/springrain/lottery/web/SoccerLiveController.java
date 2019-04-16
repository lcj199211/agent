package org.springrain.lottery.web;

import java.io.File;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springrain.frame.cached.ICached;
import org.springrain.frame.util.Finder;
import org.springrain.lottery.entity.SoccerLive;
import org.springrain.lottery.service.ISoccerLiveService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;


/**
 * 足球实况管理
 */
@Controller
@RequestMapping(value = "/soccerlive")
public class SoccerLiveController extends BaseController {
    @Resource
    private ISoccerLiveService soccerLiveService;
    @Resource
    private ICached cached;
    private String listurl = "/lottery/soccerlive/soccerliveList";


    /**
     * 列表数据,调用listjson方法,保证和app端数据统一
     *
     * @param request
     * @param model
     * @param soccerLive
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model, SoccerLive soccerLive)
            throws Exception {
        String starttime = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");
        if (StringUtils.isBlank(starttime)) {
            starttime = "0000-01-01";
        }
        if (StringUtils.isBlank(endtime)) {
            endtime = "9999-01-01";
        }
        java.sql.Date startDate = java.sql.Date.valueOf(starttime);
        java.sql.Date endDate = java.sql.Date.valueOf(endtime);
        ReturnDatas returnObject = listjson(request, model, soccerLive, startDate, endDate);
        if (starttime == "0000-01-01") {
            startDate = null;
        }
        if (endtime == "9999-01-01") {
            endDate = null;
        }
        model.addAttribute("startTime", startDate);
        model.addAttribute("endTime", endDate);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return listurl;
    }

    /**
     * json数据,为APP提供数据
     *
     * @param request
     * @param model
     * @param soccerLive
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/json")
    @ResponseBody
    public ReturnDatas listjson(HttpServletRequest request, Model model, SoccerLive soccerLive, Date startDate, Date endDate) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        Page page = newPage(request);
        // ==执行分页查询
        List<SoccerLive> datas = soccerLiveService.findListDataByFinder(new Finder("select * from soccer_live where substr(starttime, 1, 10) >=:starttime and substr(starttime, 1, 10) <=:endtime ").setParam("starttime", startDate).setParam("endtime", endDate), page, SoccerLive.class, soccerLive);
        returnObject.setQueryBean(soccerLive);
        returnObject.setPage(page);
        returnObject.setData(datas);
        return returnObject;
    }

    @RequestMapping("/list/export")
    public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, SoccerLive soccerLive) throws Exception {
        // ==构造分页请求
        Page page = newPage(request);

        File file = soccerLiveService.findDataExportExcel(null, listurl, page, SoccerLive.class, soccerLive);
        String fileName = "soccerLive" + GlobalStatic.excelext;
        downFile(response, file, fileName, true);
        return;
    }

    /**
     * 查看操作,调用APP端lookjson方法
     */
    @RequestMapping(value = "/look")
    public String look(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnDatas returnObject = lookjson(model, request, response);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return "/lottery/soccerlive/soccerliveLook";
    }


    /**
     * 查看的Json格式数据,为APP端提供数据
     */
    @RequestMapping(value = "/look/json")
    @ResponseBody
    public ReturnDatas lookjson(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        String strId = request.getParameter("id");
        java.lang.Integer id = null;
        if (StringUtils.isNotBlank(strId)) {
            id = java.lang.Integer.valueOf(strId.trim());
            SoccerLive soccerLive = soccerLiveService.findSoccerLiveById(id);
            returnObject.setData(soccerLive);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }


    /**
     * 新增/修改 操作吗,返回json格式数据
     */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnDatas saveorupdate(Model model, SoccerLive soccerLive, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            if (soccerLive.getId() == null) {
                soccerLiveService.saveorupdate(soccerLive);
            } else {
                soccerLiveService.update(soccerLive, true);
            }
            cached.deleteCached("endMatchs".getBytes());
//            soccerLiveService.saveorupdate(soccerLive);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }

    /**
     * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
     */
    @RequestMapping(value = "/update/pre")
    public String updatepre(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnDatas returnObject = lookjson(model, request, response);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return "/lottery/soccerlive/soccerliveCru";
    }

    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnDatas delete(HttpServletRequest request) throws Exception {

        // 执行删除
        try {
            String strId = request.getParameter("id");
            java.lang.Integer id = null;
            if (StringUtils.isNotBlank(strId)) {
                id = java.lang.Integer.valueOf(strId.trim());
                soccerLiveService.deleteById(id, SoccerLive.class);
                return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
    }

    /**
     * 删除多条记录
     */
    @RequestMapping("/delete/more")
    @ResponseBody
    public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
        String records = request.getParameter("records");
        if (StringUtils.isBlank(records)) {
            return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_FAIL);
        }
        String[] rs = records.split(",");
        if (rs == null || rs.length < 1) {
            return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_FAIL);
        }
        try {
            List<String> ids = Arrays.asList(rs);
            soccerLiveService.deleteByIds(ids, SoccerLive.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ALL_FAIL);
        }
        return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_ALL_SUCCESS);


    }

}
