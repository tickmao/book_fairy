package com.book.fairy.controller.sys;

import com.book.fairy.annotation.LogAnnotation;
import com.book.fairy.sys.dto.BeanField;
import com.book.fairy.sys.dto.GenerateDetail;
import com.book.fairy.sys.dto.GenerateInput;
import com.book.fairy.sys.service.GenerateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 代码生成接口
 * 
 * @author 何杨洲
 *
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@ApiOperation("根据表名显示表信息")
	@GetMapping(params = { "tableName" })
	@RequiresPermissions("generate:edit")
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@LogAnnotation
	@ApiOperation("生成代码")
	@PostMapping
	@RequiresPermissions("generate:edit")
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}

}
