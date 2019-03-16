package com.business.hall.core;

import java.io.Serializable;

public interface IResult<T> extends Serializable {
	public Object getData();

	public int getInfoCode();

	public String getMessage();

}