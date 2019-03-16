package com.business.hall.controller.modules;

import com.business.hall.core.Result;
import com.business.hall.modules.entity.SysDict;
import com.business.hall.modules.service.SysDictService;
import com.business.hall.sys.dao.SysLogsDao;
import com.business.hall.sys.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value="业务字典", description = "业务字典API")
@RestController
@RequestMapping(value = "/api/sysDict", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SysDictController extends BaseController{
	@Autowired
	private SysLogsDao sysLogsDao;

	@Autowired
	private SysDictService sysDictService;

    @ApiOperation(value = "根据类型查询字典列表", notes = "根据类型查询字典列表", response = Result.class)
    @GetMapping(value = "/findByConfigType" )
    public Result findByConfigType(@ApiParam(name = "type", value = "字典类型", required = true)
                                   @RequestParam(value = "type", required = true) String type,
                                   @ApiParam(name = "parentValue", value = "父级", required = false)
                                   @RequestParam(value = "parentValue", required = false) Integer parentValue) {

		try{
			User user = getCurrentUser();
			user.getBusinessHallId();

			SysDict sysDict = new SysDict();
			sysDict.setConfigType(type);
			sysDict.setParentValue(parentValue);
			List<SysDict> sysDicts = sysDictService.selectSysDictByConditions(sysDict);

			//排序
			Collections.sort(sysDicts, new Comparator<SysDict>() {
				public int compare(SysDict sysDict1, SysDict sysDict2) {
					return sysDict1.getOrder() - sysDict2.getOrder();
				}
			});

			//格式化输出
			List<Map<String, String>> dicts = new ArrayList<>();
			for(SysDict sd: sysDicts){
				Map<String, String> dict = new HashMap<>();
				dict.put("id", String.valueOf(sd.getValue()));
				dict.put("text", sd.getName());
				dicts.add(dict);
			}

			return Result.success("查询成功！", dicts);
		}catch (Exception e){
			logger.error("查询失败，原因：" + e.getMessage());
			e.printStackTrace();
			return Result.error("查询失败，原因: " + e.getMessage());
		}
    }

	@ApiOperation(value="新增字典列表")
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Result save(@RequestBody SysDict sysDict){
		try{
			Map<String, Integer> map = sysDictService.selectMax();
			sysDict.setOrder(map.get("order")+1);
			sysDict.setValue(map.get("value")+1);
			sysDictService.insert(sysDict);
			return Result.success("新增成功！");
		}catch (Exception e){
			logger.error("新增失败，原因：" + e.getMessage());
			e.printStackTrace();
			return Result.error("新增失败，原因: " + e.getMessage());
		}
	}

}
