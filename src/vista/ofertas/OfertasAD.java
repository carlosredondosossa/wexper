package vista.ofertas;

import java.util.ArrayList;
import java.util.Date;

import locator.ServiceLocator;
import modelo.Admin;
import modelo.Empresario;
import modelo.Oferta;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import util.Converter;

@Controller
public class OfertasAD {
	
	@RequestMapping(value = "/ofertasAD.do", method = RequestMethod.GET)
	public String requestMappingServicios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() )
			return "ofertas/ofertasAD";
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
	private boolean form4;
	
	private Admin usuario;
	
	private ArrayList<Oferta> listaOfertas;
	private Oferta selOferta;
	
	
	@Init
	public void init(){
		usuario = (Admin) ServiceLocator.getPerfil("AD");
		selectMenu("m1");
	}

	@Command
	@NotifyChange("*")
	public void selectMenu(@BindingParam("pos") String sel){
		msg = new String();
		
		sclassSub1 = sel.equals("m1") ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		sclassSub2 = sel.equals("m2") ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		sclassSub3 = sel.equals("m3") ? SUBMENU_ACTIVE : SUBMENU_OPTION;
		
		form1 = sel.equals("m1") ? true : false;
		form2 = sel.equals("m2") ? true : false;
		form3 = sel.equals("m3") ? true : false;
		form4 = false;
		
		if (form1) {
			listaOfertas = ServiceLocator.getOfertaDAO().buscarAll();
			selOferta = new Oferta();
		} else if (form2) {
			selOferta = new Oferta();
		} else if (form3) {
			
		}
	}
	
	@Command
	@NotifyChange("*")
	public void crear(){
		
		if (validarCrear()) {
			msg = "Ingrese los campos obligatorios";
			return;
		}
		
		selOferta.setEstado(true);
		selOferta.setFecha(Converter.formatDate(new Date()));
		
		selOferta = ServiceLocator.getOfertaDAO().crear(selOferta);
		msg = "La oferta ha sido creada correctamente.";
		limpiar();
	}
	
	private boolean validarCrear() {
		boolean exit = false;
		exit = selOferta.getEmpresa() == null || selOferta.getEmpresa().isEmpty() ? true : exit;
		exit = selOferta.getCargo() == null || selOferta.getCargo().isEmpty() ? true : exit;
		exit = selOferta.getContacto() == null || selOferta.getContacto().isEmpty() ? true : exit;
		exit = selOferta.getCorreo() == null || selOferta.getCorreo().isEmpty() ? true : exit;
		exit = selOferta.getTelefono() == null || selOferta.getTelefono().isEmpty() ? true : exit;
		return exit;
	}

	@Command
	@NotifyChange("*")
	public void limpiar(){
		selOferta = new Oferta();
	}
	
	@Command
	@NotifyChange("*")
	public void verOferta(){
		form4 = true;
		form1 = !form4;
	}
	
	@Command
	@NotifyChange("*")
	public void modificar(){
		if (validarCrear()) {
			msg = "Ingrese los campos obligatorios";
			return;
		}
		
		selOferta = ServiceLocator.getOfertaDAO().editar(selOferta);
		msg = "La oferta ha sido modificada correctamente.";
	}
	
	@Command
	@NotifyChange("*")
	public void cancelar(){
		selectMenu("m1");
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

	public ArrayList<Oferta> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(ArrayList<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public Oferta getSelOferta() {
		return selOferta;
	}

	public void setSelOferta(Oferta selOferta) {
		this.selOferta = selOferta;
	}

	public boolean isForm4() {
		return form4;
	}

	public void setForm4(boolean form4) {
		this.form4 = form4;
	}

}
