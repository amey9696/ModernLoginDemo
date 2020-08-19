package com.amey.login_app;

public class UserHelperClass {
    String regname,regusername,regemail,regphoneNo,regpassword;//by using id of ur edittext in java file but note that this value are different

    public UserHelperClass() {
    }
//right click-generate-constructor-select all value
    public UserHelperClass(String regname, String regusername, String regemail, String regphoneNo, String regpassword) {
        this.regname = regname;
        this.regusername = regusername;
        this.regemail = regemail;
        this.regphoneNo = regphoneNo;
        this.regpassword = regpassword;
    }
//right click-generate-getter & setter-select all value
    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public String getRegusername() {
        return regusername;
    }

    public void setRegusername(String regusername) {
        this.regusername = regusername;
    }

    public String getRegemail() {
        return regemail;
    }

    public void setRegemail(String regemail) {
        this.regemail = regemail;
    }

    public String getRegphoneNo() {
        return regphoneNo;
    }

    public void setRegphoneNo(String regphoneNo) {
        this.regphoneNo = regphoneNo;
    }

    public String getRegpassword() {
        return regpassword;
    }

    public void setRegpassword(String regpassword) {
        this.regpassword = regpassword;
    }
}
