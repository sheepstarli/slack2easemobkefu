package com.chenxing.data.easemob;

public class EasemobTxtMessageBody extends AbstractEasemobMessageBody {
    private String msg;

    public EasemobTxtMessageBody( String msg) {
        this.msg = msg;
    }

    public EasemobTxtMessageBody() {
    }

    public String getType() {
        return "txt";
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EasemobTxtMessageBody that = (EasemobTxtMessageBody) o;

        return !(msg != null ? !msg.equals(that.msg) : that.msg != null);

    }

    @Override
    public int hashCode() {
        return msg != null ? msg.hashCode() : 0;
    }
}
