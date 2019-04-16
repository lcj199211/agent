package org.springrain.lottery.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.*;
import org.springrain.lottery.entity.BetAgent;
import org.springrain.lottery.entity.BetAgentgold;
import org.springrain.lottery.entity.BetAgentgoldDaylyRank;
import org.springrain.lottery.entity.BetGold;
import org.springrain.lottery.service.IBetAgentService;
import org.springrain.lottery.service.IBetAgentgoldService;
import org.springrain.lottery.service.IBetGoldService;
import org.springrain.lottery.service.IBetPaymentInterfaceService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * TODO 在此加入类描述
 * @copyright
 * @author chaojian_liangs
 * @version  2019-04-11 09:41:49
 * @see
 */
@Controller
@RequestMapping(value="/betagentgoldrank")
public class BetAgentGoldRankController  extends BaseController {
    @Resource
    private IBetAgentgoldService betAgentgoldService;
    @Resource
    private IBetAgentService betAgentService;
    @Resource
    private IBetGoldService betGoldService;
    @Resource
    private IBetPaymentInterfaceService betPaymentInterfaceService;
    /**
     * 代理充值排行列表
     */
    @RequestMapping("/list")
    public String betagentgoldrank(HttpServletRequest request, Model model,BetAgent httpBetAgent) throws Exception {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        String requestagentid=httpBetAgent.getAgentid();
        String agentaccount="A101";
        String agentnickname="所有公司";
        //String agentid="A101";
        String agentid = SessionUser.getAgentId(); //当前登录的代理id
        Page page = newPage(request);
        page.setOrder("mon");
        page.setSort("desc");
        String starttime = request.getParameter("starttime");
        String endtime = request.getParameter("endtime");
        Date date1 = DateUtils.convertString2Date(endtime);
        Calendar calendar = new GregorianCalendar();
        if(date1!=null){
            calendar.setTime(date1);
            calendar.add(Calendar.DATE,1);
            Date date3=calendar.getTime();
            endtime = DateUtils.convertDate2String(date3);
        }
        if(StringUtils.isBlank(starttime)){
           // starttime="0000-00-00";    //默认查今天

            SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
            starttime =sp.format(new Date());

        }
        if(StringUtils.isBlank(endtime)){
            //endtime="9999-00-00";       //默认查明天
            Calendar cal= Calendar.getInstance();
            cal.add(Calendar.DATE,1);//获取明日日期
            Date date=cal.getTime();
            SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
            endtime =sp.format(date);
        }
        List<BetAgentgoldDaylyRank> datas=null;
        if(requestagentid!=null&&!requestagentid.equals("")){ //非一级代理
            agentid=requestagentid;
            model.addAttribute("agentid", agentid);
            BetAgent betAgent = betAgentService.queryForObject(new Finder("select *from bet_agent where agentid=:agentid").setParam("agentid", agentid), BetAgent.class);
            agentaccount=betAgent.getAccount();
            agentnickname=betAgent.getNickname();
            datas=betAgentgoldService.queryForList(new Finder("select *, sum(totalmoney) mon from bet_agentgold_daylyrank where  creatTime>=:starttime and creatTime<:endtime and parentid=:parentid  GROUP BY agentid ").setParam("parentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentgoldDaylyRank.class, page);

        }else{
            datas=betAgentgoldService.queryForList(new Finder("select *,sum(totalmoney) mon from bet_agentgold_daylyrank where  creatTime>=:starttime and creatTime<:endtime and agentid=:agentid  GROUP BY agentid ").setParam("agentid", agentid).setParam("starttime",starttime ).setParam("endtime", endtime), BetAgentgoldDaylyRank.class, page);

        }

        if(!"9999-00-00".equals(endtime)){
            Date date2 =DateUtils.convertString2Date(endtime);
            calendar.setTime(date2);
            calendar.add(Calendar.DATE,-1);
            Date date3=calendar.getTime();
            endtime = DateUtils.convertDate2String(date3);
            model.addAttribute("endTime", endtime);
        }
        model.addAttribute("startTime", starttime);
        model.addAttribute("agentaccount", agentaccount);
        model.addAttribute("agentnickname", agentnickname);
        model.addAttribute("daylyRankData", datas);

        returnObject.setPage(page);
        returnObject.setData(datas);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return  "/lottery/betagentgolddaylyrank/betreportforbetagentgold";
    }


    /**
    * 用户充值详细
    */
    @RequestMapping("/goldList")
    public String goldList(HttpServletRequest request, Model model,BetAgent httpBetAgent) throws Exception {
        List<BetGold>datas=null;
        Double yesterdayRecharge=0.0;
        Double todayRecharge=0.0;
        Double chargeNumber=0.;
        Integer member=0;
        Double totolRecharge=0.0;
        //分页
        Page page = newPage(request);
        page.setOrder("submittime");
        page.setSort("desc");
        page.setPageSize(50);
        //代理
        String agentId=httpBetAgent.getAgentid();
        //日期条件
        String starttime = request.getParameter("startTime");
        String endtime = request.getParameter("endTime");
        Date date1 =DateUtils.convertString2Date(endtime);
        Calendar calendar = new GregorianCalendar();
        if(date1!=null){
            calendar.setTime(date1);
            calendar.add(Calendar.DATE,1);
            Date date3=calendar.getTime();
            endtime = DateUtils.convertDate2String(date3);
        }
        if(StringUtils.isBlank(starttime)){
            starttime="0000-00-00";
        }
        if(StringUtils.isBlank(endtime)){
            endtime="9999-00-00";
        }
        List<Map<String, Object>> idandbanktypelist = betPaymentInterfaceService.queryForList(new Finder("select banktype,id from bet_payment_interface where agentid=:agentid ").setParam("agentid", agentId));
        datas=betGoldService.findListDataByFinder(new Finder("select a.*,b.id2 as memberid2 from bet_gold a LEFT JOIN bet_member b ON a.memberid=b.id where a.submittime>=:starttime and a.submittime<:endtime and (a.agentid=:agentid or a.agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), page, BetGold.class,null);
        totolRecharge=betGoldService.queryForObject(new Finder("select sum(money) from bet_gold where submittime>=:starttime and state=2 and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
        chargeNumber=betGoldService.queryForObject(new Finder("select count(1) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Double.class);
        member=betGoldService.queryForObject(new Finder("select count(distinct memberid) from bet_gold where submittime>=:starttime  and submittime<:endtime and (agentid=:agentid or agentparentids like :agentids ) ").setParam("agentid", agentId).setParam("agentids","%,"+agentId+",%").setParam("starttime",starttime ).setParam("endtime", endtime), Integer.class);
        if(datas!=null){
            for (BetGold betGold2 : datas) {
                String source = betGold2.getSource();
                if(idandbanktypelist!=null){
                    for (Map<String, Object> map : idandbanktypelist) {
                        if(source.equals(map.get("id"))){
                            betGold2.setSource((String)map.get("banktype"));
                            break;
                        }else{
                            betGold2.setSource(null);
                        }
                    }
                }
            }
        }

        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setPage(page);
        returnObject.setData(datas);

        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        if(!"0000-00-00".equals(starttime)){
            model.addAttribute("startTime", starttime);
        }
        if(!"9999-00-00".equals(endtime)){
            Date date2 =DateUtils.convertString2Date(endtime);
            calendar.setTime(date2);
            calendar.add(Calendar.DATE,-1);
            Date date3=calendar.getTime();
            endtime = DateUtils.convertDate2String(date3);
            model.addAttribute("endTime", endtime);
        }
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        model.addAttribute("chargeNumber", chargeNumber);
        model.addAttribute("member", member);
        model.addAttribute("totolRecharge", totolRecharge);

        return "/lottery/betgold/betgoldList1";
    }



}
