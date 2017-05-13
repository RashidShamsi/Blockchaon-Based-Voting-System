/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ned.bcvs.login;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import ned.bcvs.admin.beans_local_interfaces.AdminLoginValidatorLocal;

/**
 *
 * @author StackHouse
 */
@Named
@Stateful
@SessionScoped
public class AdminLoginBean {

        @EJB
        private AdminLoginValidatorLocal loginValidator ;
    
        private HttpSession session ;
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

          /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }

        
	//validate login
	public String validateUsernamePassword() {
            System.out.println("****Calling loginValidator.validateCredentials()*************");
             System.out.println("===================Admin Login Bean =======================");
             System.out.println("user name = " + getUser() + " password = " + getPwd());
             System.out.println("=======================================================================");
            boolean valid = loginValidator.validateCredentials(getUser(), getPwd());
            System.out.println("value stored in valid = " + valid );
            if (valid) {
                        System.out.println("********Login validation is true (entering if condition)********");
                        System.out.println("********creating session********");
                        setSession((HttpSession) FacesContext.getCurrentInstance()
				     .getExternalContext().getSession(false));
			getSession().setAttribute("username", user);
			return "index";
		} else {
                        System.out.println("********Login validation is not true (entering else condition)********");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
//		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

  }
