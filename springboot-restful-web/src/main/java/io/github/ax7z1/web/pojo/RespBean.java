package io.github.ax7z1.web.pojo;

/**
 * 消息响应类
 */
public class RespBean {

    private Integer code; //状态码； 200正常 500错误
    private String message; //消息
    private Object data;    //返回的数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
