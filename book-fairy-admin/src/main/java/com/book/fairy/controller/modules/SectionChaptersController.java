package com.book.fairy.controller.modules;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.book.fairy.core.Result;
import com.book.fairy.modules.entity.SectionChapters;
import com.book.fairy.modules.service.SectionChaptersService;
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
 * 章节内容表 前端控制器
 * </p>
 *
 * @author zk
 * @since 2019-03-16
 */
@Api(description = "小说章节")
@RestController
@RequestMapping(value = "/api/sectionChapters",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SectionChaptersController extends BaseController{
    @Autowired
    private SectionChaptersService sectionChaptersService;

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    @ApiOperation(value="根据小说ID获取小说章节信息")
    public Result getList(
            @ApiParam(name="bookname_id",value="小说ID",required=true)
            @RequestParam(value = "bookname_id", required = true) String bookname_id
    ){
        try {
            List<SectionChapters> list = sectionChaptersService.selectList(new EntityWrapper<SectionChapters>().eq("bookname_id",bookname_id));
            return Result.success("查询成功！", list);
        } catch (Exception e) {
            logger.error("查询信息失败，原因：" + e.getMessage());
            e.printStackTrace();
            return Result.error("查询信息失败，原因: " + e.getMessage());
        }

    }

}

