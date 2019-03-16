package com.business.hall.controller.modules;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.business.hall.core.Result;
import com.business.hall.modules.entity.SectionDetails;
import com.business.hall.modules.service.SectionDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 章节详情表 前端控制器
 * </p>
 *
 * @author zk
 * @since 2019-03-16
 */
@Api(description = "小说章节内容详情")
@RestController
@RequestMapping(value = "/api/sectionDetails",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SectionDetailsController extends BaseController{

    @Autowired
    private SectionDetailsService sectionDetailsService;

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ApiOperation(value="根据章节ID获取章节内容信息")
    public Result getList(
            @ApiParam(name="section_chapter_id",value="章节ID",required=true)
            @RequestParam(value = "section_chapter_id", required = true) String section_chapter_id
    ){
        try {
            List<SectionDetails> list = sectionDetailsService.selectList(new EntityWrapper<SectionDetails>().eq("section_chapter_id",section_chapter_id));
            return Result.success("查询成功！", list);
        } catch (Exception e) {
            logger.error("查询信息失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("查询信息失败，原因: " + e.getMessage());
        }

    }


}

