package com.business.hall.core;


/**
 * 返回结果 用于mvc 返回统一的结构的结果对象
 */
public class Result implements IResult {


    /**
     * <code>serialVersionUID</code> of comment
     */
    private static final long serialVersionUID = 1L;

    protected int infoCode;
    protected String message;
    protected Object data;

    /**
     * 类型
     */


    public Result() {
    }

    public Result(int code) {
        super();
        this.infoCode = code;
    }

    public Result(int code, String msg) {
        super();
        this.infoCode = code;
        this.message = msg;
    }

    public Result(int code, String msg, Object data) {
        super();
        this.infoCode = code;
        this.message = msg;
        this.data = data;
    }

    public static Result create() {
        return new Result();
    }

    public static Result create(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result create(int code, String msg) {
        return create(code, msg, null);
    }

    public static Result create(Message message) {
        return create(message.getInfoCode(), message.getMessage(), null);
    }

    public static Result create(Message message, Object data) {
        return create(message.getInfoCode(), message.getMessage(), data);
    }


    /**
     * 返回成功消息
     */
    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    /**
     * 返回成功消息
     */
    public static Result success(String msg, Object data) {
        return success(Message.success.getInfoCode(), msg, data);
    }

    public static Result success(Object data) {
        return success(Message.success.getInfoCode(), Message.success.getMessage(), data);
    }

    /**
     * 返回成功消息
     */
    public static Result success() {
        return success(Message.success.getInfoCode(), Message.success.getMessage(), null);
    }

    /**
     * 返回错误消息
     */
    public static Result error(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    /**
     * 返回错误消息
     */
    public static Result error(String msg, Object data) {
        return error(Message.error.getInfoCode(), msg, data);
    }

    /**
     * 返回错误消息
     */
    public static Result error(String msg) {
        return error(Message.error.getInfoCode(), msg, null);
    }

    /**
     * 返回错误消息
     */
    public static Result error() {
        return error(Message.error.getInfoCode(), Message.error.getMessage(), null);
    }

    /**
     * 返回警告消息
     */
    public static Result warn(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    /**
     * 返回警告消息
     */
    public static Result warn(String msg, Object data) {
        return warn(Message.warn.getInfoCode(), msg, data);
    }

    /**
     * 返回警告消息
     */
    public static Result warn() {
        return warn(Message.warn.getInfoCode(), Message.warn.getMessage(), null);
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(int infoCode) {
        this.infoCode = infoCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "infoCode=" + infoCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}