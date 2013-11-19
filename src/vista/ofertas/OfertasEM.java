package vista.ofertas;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

@Controller
public class OfertasEM {
	
	@RequestMapping(value = "/ofertasEM.do", method = RequestMethod.GET)
	public String requestMappingServicios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() )
			return "ofertas/ofertasEM";
		else
			return "redirect:/login.do";
	}
	
	final private String SUBMENU_OPTION = "submenu-option";
	final private String SUBMENU_ACTIVE = "submenu-active";
	
	private String msg = new String();
	
	private String sclassSub1;
	private String sclassSub2;
	private String sclassSub3;
	private boolean form1;
	private boolean form2;
	private boolean form3;
	
	
	@Init
	public void init(){	
		selectMenu(1);
	}
	
	@Command
	@NotifyChange("*")
	public void selectMenu(int sel){
		sclassSub1 = sel == 1 ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		sclassSub2 = sel == 2 ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		sclassSub3 = sel == 3 ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		
		form1 = sel == 1 ? true : false;
		form2 = sel == 2 ? true : false;
		form3 = sel == 3 ? true : false;
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSclassSub1() {
		return sclassSub1;
	}
	public void setSclassSub1(String sclassSub1) {
		this.sclassSub1 = sclassSub1;
	}
	public String getSclassSub2() {
		return sclassSub2;
	}
	public void setSclassSub2(String sclassSub2) {
		this.sclassSub2 = sclassSub2;
	}
	public String getSclassSub3() {
		return sclassSub3;
	}
	public void setSclassSub3(String sclassSub3) {
		this.sclassSub3 = sclassSub3;
	}

	public boolean isForm1() {
		return form1;
	}

	public void setForm1(boolean form1) {
		this.form1 = form1;
	}

	public boolean isForm2() {
		return form2;
	}

	public void setForm2(boolean form2) {
		this.form2 = form2;
	}

	public boolean isForm3() {
		return form3;
	}

	public void setForm3(boolean form3) {
		this.form3 = form3;
	}

}
