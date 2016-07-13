/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.dto;

/**
 *
 * @author dungnv.ptit83@gmail.com
 */
public class Response<T> {

    /**
     * Function có thành công hay không
     */
    private boolean status;
    /**
     * Message thông báo từ function
     */
    private String message;
    /**
     * Dữ liệu trả về của function
     */
    private T data;

    public Response() {
    }

    public Response(boolean status) {
        this.status = status;
    }

    public Response(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}