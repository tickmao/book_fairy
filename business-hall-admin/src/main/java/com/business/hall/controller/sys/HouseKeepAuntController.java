package com.business.hall.controller.sys;

import com.business.hall.core.Result;
import com.business.hall.sys.dao.HouseKeepAuntDao;
import com.business.hall.sys.model.HouseKeepAunt;
import com.business.hall.sys.service.HouseKeepAuntService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api/houseKeepAunt")
public class HouseKeepAuntController {
    private static final Logger logger = LoggerFactory.getLogger(HouseKeepAuntController.class);

    @Autowired
    HouseKeepAuntDao houseKeepAuntDao;

    @Autowired
    private HouseKeepAuntService houseKeepAuntService;

    @GetMapping
    @ApiOperation(value = "获取阿姨列表")
    public Object findList(@RequestParam(value = "token", required = false) String token,
                           @RequestParam(value = "pageNum", required = false) Integer pageNum,
                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                           @RequestParam(value = "hksId", required = false) Integer hksId,//家政商店id
                           @RequestParam(value = "hkiId", required = false) Integer hkiId,//家政项目id
                           @RequestParam(value = "ageBegin", required = false) Integer ageBegin,//
                           @RequestParam(value = "ageEnd", required = false) Integer ageEnd,//家政商店id
                           @RequestParam(value = "birthPlace", required = false) String birthPlace,//家政商店id
                           @RequestParam(value = "treatmentBegin", required = false) Integer treatmentBegin,//家政商店id
                           @RequestParam(value = "treatmentEnd", required = false) Integer treatmentEnd,//家政商店id
                           @RequestParam(value = "khaName", required = false) String khaName,//名称
                           @RequestParam(value = "workYearBegin", required = false) Integer workYearBegin,//工作年限开始
                           @RequestParam(value = "workYearEnd", required = false) Integer workYearEnd,//工作年限开始
                           @RequestParam(value = "familyStatus", required = false) Integer familyStatus) {//家庭状况
        Result result = new Result();
        try {
            Map<String, Object> dg = new HashMap<>();

            if(khaName!=null && khaName.equals("")){
                khaName = null;
            }

            List<HouseKeepAunt> houseKeepAunts = new ArrayList<>();

            if(pageNum != null && pageSize != null){//分页查询

                Integer total = houseKeepAuntDao.countBySimpleCondition(hksId, hkiId, ageBegin, ageEnd, null
                        ,null, null, null
                        ,khaName, null,null, null
                        ,null, null, null);

                Integer beginNum = (pageNum - 1) * pageSize;
                houseKeepAunts = houseKeepAuntDao.selectBySimpleCondition(hksId, hkiId, ageBegin, ageEnd, null
                        ,null, null, null
                        ,khaName, null,null, null
                        ,null, null, null,beginNum, pageSize);

                dg.put("total", total);
                dg.put("rows", houseKeepAunts);

            }else{
                houseKeepAunts = houseKeepAuntDao.selectBySimpleCondition(hksId, hkiId, ageBegin, ageEnd, null
                        ,null, null, null
                        ,khaName, null,null, null
                        ,null, null, null,null, null);

                dg.put("total", houseKeepAunts.size());
                dg.put("rows", houseKeepAunts);
            }

            return Result.success("查询成功！", dg);
        } catch (Exception e) {
            logger.error("查询Banner列表信息失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("查询Banner列表信息失败，原因: " + e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "新增阿姨")
    public Object add(@RequestParam(value = "token", required = false) String token,
                      @RequestBody HouseKeepAunt houseKeepAunt,
                      HttpSession session) {
        try {
            houseKeepAunt.setCreateTime(new Date());
            houseKeepAunt.setUpdateTime(new Date());
            houseKeepAuntService.insert(houseKeepAunt);

            return Result.success("新增成功！", houseKeepAunt);
        } catch (Exception e) {
            logger.error("新增失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("新增失败！" + e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取")
    public Object getHouseKeepAunt(@RequestParam(value = "token", required = false) String token,
                                   @PathVariable Integer id,
                                   HttpSession session) {
        try {
            HouseKeepAunt houseKeepAunt = houseKeepAuntService.selectByPrimaryKey(id);

            return Result.success("查询成功！", houseKeepAunt);
        } catch (Exception e) {
            logger.error("查询失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("查询失败！" + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "根据id修改阿姨信息")
    public Object putHouseKeepAunt(@RequestParam(value = "token", required = false) String token,
                                   @PathVariable Integer id,
                                   @RequestBody HouseKeepAunt houseKeepAunt,
                                   HttpSession session) {
        try {
            houseKeepAunt.setHkaId(id);
            houseKeepAunt.setUpdateTime(new Date());
            houseKeepAuntService.updateByPrimaryKeySelective(houseKeepAunt);

            return Result.success("编辑成功! ", houseKeepAunt);
        } catch (Exception e) {
            logger.error("编辑失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("编辑失败！" + e.getMessage());
        }
    }

    //删除阿姨
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "根据id删除阿姨信息")
    public Object deleteHouseKeepAunt(@RequestParam(value = "token", required = false) String token,
                                      @PathVariable Integer id,
                                      HttpSession session) {
        try {
            houseKeepAuntService.deleteByPrimaryKey(id);

            return Result.success("删除成功! ", null);
        } catch (Exception e) {
            logger.error("删除失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("删除失败！" + e.getMessage());
        }
    }

}
