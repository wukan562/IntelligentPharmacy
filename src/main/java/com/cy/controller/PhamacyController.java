package com.cy.controller;import com.cy.bean.DrugClassification;import com.cy.bean.PhamacyDrug;import com.cy.biz.PhamacyService;import com.cy.util.StringUtils;import com.github.pagehelper.PageInfo;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.ResponseBody;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;@Controller@RequestMapping("/phamacy")public class PhamacyController {    private Map<String,Object> map = new HashMap<>();    @Autowired    private PhamacyService phamacyService;    @RequestMapping("/phamacyAllDrugs.action")    public String  phamacyAllDrugs(HttpServletRequest request, String num , String drugName){        if(!StringUtils.isEmpty(drugName)){            map.put("drugName",drugName);        }        PageInfo  drugpage = phamacyService.selectPhamacyAllDrugsPageInfoPageInfo(map, num,4);                if (drugpage!=null) {            HttpSession session = request.getSession();            session.setAttribute("drugpage",drugpage);            return "/pharmacyPage/phamacyAllDrugs";        } else {            return "error";        }    }    @RequestMapping("/phamacyDrugDetils.action")    public String phamacyDrugDetils(HttpServletRequest request ,String pharmacyId ){        if(!StringUtils.isEmpty(pharmacyId)){           int a = Integer.parseInt(pharmacyId);           PhamacyDrug phamacyDrug = phamacyService.selectByIdPhamacyDrug(a);            request.setAttribute("phamacyDrug",phamacyDrug);        }        return "/pharmacyPage/phamacyDrugDetils";    }    //查询药品分类信息    @RequestMapping("/selectPharmacyClassification.action")    public String selectPharmacyClassification(HttpServletRequest request){        List<DrugClassification> drugClassification = phamacyService.selectDrugClassification();        request.setAttribute("drugClassification",drugClassification);                return "/pharmacyPage/phamacyLeadDrug";    }    //请领药品通过药品分类查询药品    @RequestMapping(value="/selectPharmacyClassificationPhamacyDrugDetils.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")    public @ResponseBody List<PhamacyDrug> selectPharmacyClassificationPhamacyDrugDetils(String drugClassificationId ){        List<PhamacyDrug> phamacyDrugs = new ArrayList<PhamacyDrug>();        if(!StringUtils.isEmpty(drugClassificationId)){            int a = Integer.parseInt(drugClassificationId);            phamacyDrugs = phamacyService.selectPhamacyClassificationdDrug(a);        }        return phamacyDrugs;    }    //请领药品通查询药品详细信息    @RequestMapping(value="/selectPhamacyDrugDetils.action",method=RequestMethod.POST, produces="application/json;charset=utf-8")    public @ResponseBody PhamacyDrug selectPhamacyDrugDetils(String pharmacyId ){        PhamacyDrug phamacyDrugs = new PhamacyDrug();        if(!StringUtils.isEmpty(pharmacyId)){            int a = Integer.parseInt(pharmacyId);            phamacyDrugs = phamacyService.selectByIdPhamacyDrug(a);        }        return phamacyDrugs;    }    //请领药品    @RequestMapping("/addRecevieDrug.action")    public String addRecevieDrug(String phamacyLeadDrugNumber,String pharmacyId){        if(!StringUtils.isEmpty(pharmacyId)||!StringUtils.isEmpty(phamacyLeadDrugNumber)){                    }                return "receiveDrugs";    }    public Map<String, Object> getMap() {        return map;    }    public void setMap(Map<String, Object> map) {        this.map = map;    }    public PhamacyService getPhamacyService() {        return phamacyService;    }    public void setPhamacyService(PhamacyService phamacyService) {        this.phamacyService = phamacyService;    }}    