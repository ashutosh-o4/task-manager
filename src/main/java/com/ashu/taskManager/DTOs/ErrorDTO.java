package com.ashu.taskManager.DTOs;

public class ErrorDTO {

    private String msg;

    public ErrorDTO(String msg) {
        this.msg = msg;
    }
    public ErrorDTO() {
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
