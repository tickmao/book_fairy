package com.book.fairy.sys.page.table;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询处理器
 * 
 * @author 何杨洲
 *
 */

public class PageTableHandler {

	private CountHandler countHandler;
	private ListHandler listHandler;

	public PageTableHandler(CountHandler countHandler, ListHandler listHandler) {
		super();
		this.countHandler = countHandler;
		this.listHandler = listHandler;
	}

	public com.book.fairy.sys.page.table.PageTableResponse handle(com.book.fairy.sys.page.table.PageTableRequest dtRequest) {
		int count = 0;
		List<?> list = null;

		count = this.countHandler.count(dtRequest);
		if (count > 0) {
			list = this.listHandler.list(dtRequest);
		}

		if (list == null) {
			list = new ArrayList<>();
		}

		return new com.book.fairy.sys.page.table.PageTableResponse(count, count, list);
	}

	public interface ListHandler {
		List<?> list(com.book.fairy.sys.page.table.PageTableRequest request);
	}

	public interface CountHandler {
		int count(com.book.fairy.sys.page.table.PageTableRequest request);
	}
}