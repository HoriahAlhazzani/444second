package com.example.a444app.web_services.objs;

public class GeneralResponse {
    private int status;
    private String msg;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//todo check msg form
    @Override
    public String toString() {
        return "GeneralResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}//end class
