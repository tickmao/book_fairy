package com.book.fairy.sys.service;

import com.book.fairy.sys.model.SysLogs;

/**
 * 日志service
 * 
 * @author 何杨洲
 *
 *         2017年8月19日
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
