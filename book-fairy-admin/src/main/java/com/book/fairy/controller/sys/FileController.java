package com.book.fairy.controller.sys;

import com.book.fairy.annotation.LogAnnotation;
import com.book.fairy.sys.dao.FileInfoDao;
import com.book.fairy.sys.dto.LayuiFile;
import com.book.fairy.sys.model.FileInfo;
import com.book.fairy.sys.page.table.PageTableHandler;
import com.book.fairy.sys.page.table.PageTableRequest;
import com.book.fairy.sys.page.table.PageTableResponse;
import com.book.fairy.sys.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "文件")
@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private FileInfoDao fileInfoDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "文件上传")
	public FileInfo uploadFile(MultipartFile file) throws IOException {
		return fileService.save(file);
	}

	/**
	 * layui富文本文件自定义上传
	 * 
	 * @param file
	 * @param domain
	 * @return
	 * @throws IOException
	 */
	@LogAnnotation
	@PostMapping("/layui")
	@ApiOperation(value = "layui富文本文件自定义上传")
	public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
		FileInfo fileInfo = fileService.save(file);

		LayuiFile layuiFile = new LayuiFile();
		layuiFile.setCode(0);
		LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
		layuiFile.setData(data);
		data.setSrc(domain + "/files" + fileInfo.getUrl());
		data.setTitle(file.getOriginalFilename());

		return layuiFile;
	}

	@GetMapping
	@ApiOperation(value = "文件查询")
	@RequiresPermissions("sys:file:query")
	public PageTableResponse listFiles(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return fileInfoDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<FileInfo> list(PageTableRequest request) {
				List<FileInfo> list = fileInfoDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "文件删除")
	@RequiresPermissions("sys:file:del")
	public void delete(@PathVariable String id) {
		fileService.delete(id);
	}

}
