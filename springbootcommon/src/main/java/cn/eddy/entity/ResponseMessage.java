package cn.eddy.entity;

public class ResponseMessage {
    /**
     * 获取ResponseMessage实体
     *
     * @return
     */
    public static ResponseMessage create() {
        return new ResponseMessage();
    }

    /**
     * 错误编码 0表示没有错误
     */
    private String code = "0";

    /**
     * 访问状态 200表示正常
     */
    private int status = 200;

    /**
     * 通信信息，默认表示成功
     */
    private String message = "操作成功";

    public ResponseMessage() {}

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(String code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public ResponseMessage setCode(String code) {
        this.code = code;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ResponseMessage setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
