package com.business.hall.helper;

/**
 * 返回信息
 */
public enum Message {

	/** 成功 */
	success       (200, "操作成功！"),
	/** 错误 */
	error         (400, "操作失败！"),
	/** 警告 */
	warn          (500, "警告！"),
	/** 超时*/
	timeout       (408, "超时！"),

	/** webservice签名错误 */
	webservice_sign_error       (401, "签名错误！");

	private int infoCode;
	private String message;

	// 构造方法
	private Message(int code, String msg) {
		this.infoCode = code;
		this.message = msg;
	}

//	// 普通方法
//	public static String getMsg(int code) {
//		for (Message c : Message.values()) {
//			if (c.getCode() == code) {
//				return c.msg;
//			}
//		}
//		return null;
//	}


	public int getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(int infoCode) {
		this.infoCode = infoCode;
	}

	public String getMessage(int code) {
		for (Message c : Message.values()) {
			if (c.getInfoCode() == code) {
				return c.message;
			}
		}
		return null;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}