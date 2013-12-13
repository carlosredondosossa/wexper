package vista.ofertas;

import java.util.ArrayList;

import locator.ServiceLocator;
import modelo.Estudiante;
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
import org.zkoss.util.media.Media;


@Controller
public class OfertasES {
	
	@RequestMapping(value = "/ofertasES.do", method = RequestMethod.GET)
	public String requestMappingServicios() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated() )
			return "ofertas/ofertasES";
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
	
	private Media foto;
	private Media documento;
	private String dirFoto;
	private String dirDoc;
	
	private Estudiante usuario;
	
	private ArrayList<Oferta> listaOfertas;
	private Oferta selOferta;
	
	
	@Init
	public void init(){
		usuario = (Estudiante) ServiceLocator.getPerfil("ES");
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
			
			selOferta = new Oferta();
		} else if (form2) {
			selOferta = new Oferta();
		} else if (form3) {
			
		}
	}
	
	@Command
	@NotifyChange("*")
	public void cargarFoto(@BindingParam("event") Media media){
		
		if (media instanceof org.zkoss.image.Image) {
            foto = media;
            dirFoto = media.getName();
        } else {
        	media = null;
        	foto = media;
        	dirFoto = new String();
            msg = "No selecciono un tipo de archivo valido.";
        }
	}
	
	@Command
	@NotifyChange("*")
	public void cargarDoc(@BindingParam("event") Media media){
		
		if (media != null) {
            documento = media;
            dirDoc = media.getName();
        } else {
        	media = null;
        	documento = media;
        	dirDoc = new String();
            msg = "No selecciono un tipo de archivo valido.";
        }
	}
	
//	if (media != null) {
//
//		if ("docx".equals(media.getFormat()) || "doc".equals(media.getFormat()) || "pdf".equals(media.getFormat())) {
//			
//			Date dateResult = new Date();
//			SimpleDateFormat formato = new SimpleDateFormat("ssmmHHddMMyyyy");
//			String nombreArchivo = "[" + selectedContrato.getDependenciaProyecto().getCliente().getNombre() + "][" + selectedContrato.getNombre() + "][" + formato.format(dateResult) + "]." + media.getFormat();
//			
//			BufferedInputStream in = null;
//			BufferedOutputStream out = null;
//
//			try {
//
//				InputStream fin = media.getStreamData();
//				in = new BufferedInputStream(fin);
//
//				File baseDir = new File(RUTA_CONTRATO);
//				File file = new File(RUTA_CONTRATO + nombreArchivo);
//
//
//				if (!baseDir.exists()) {
//					baseDir.mkdirs();
//				}
//				
//				if (file.exists()) {
//					file.delete();
//				}
//
//				OutputStream fout = new FileOutputStream(file);
//				out = new BufferedOutputStream(fout);
//				byte buffer[] = new byte[1024];
//				int ch = in.read(buffer);
//				while (ch != -1) {
//					out.write(buffer, 0, ch);
//					ch = in.read(buffer);
//				}
//				
//				Proyecto proyecto = selectedContrato;
//				proyecto.setFechaContrato(GeneraLog.obtenerFecha());
//				proyecto.setContrato(nombreArchivo);
//				
//				if("pdf".equals(media.getFormat())){
//					proyecto.setFormatoContrato("PDF");
//				}else{
//					proyecto.setFormatoContrato("WORD");
//				}
//				
//				ServiceLocator.getMgrProyecto().editarProyecto(proyecto);
//				
//				Messagebox.show(Labels.getLabel("client.msgBox.uploadSuccess"), Labels.getLabel("msgBox"), Messagebox.OK, Messagebox.INFORMATION);
//				
//				filtrarContrato();
//				selectedContrato = new Proyecto();
//				notSelected = true;
//				notExist = true;
//
//			} catch (IOException e) {
//				Messagebox.show(Labels.getLabel("client.msgBox.uploadError"), Labels.getLabel("msgBox"), Messagebox.OK, Messagebox.ERROR);
//			} catch (Exception e) {
//				Messagebox.show(Labels.getLabel("client.msgBox.uploadError"), Labels.getLabel("msgBox"), Messagebox.OK, Messagebox.ERROR);
//			} finally {
//				try {
//					if (out != null)
//						out.close();
//
//					if (in != null)
//						in.close();
//
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
//
//			} else {
//				Messagebox.show(Labels.getLabel("client.msgBox.invalidExt"), Labels.getLabel("msgBox"), Messagebox.OK, Messagebox.EXCLAMATION);
//			}
//		} else {
//			Messagebox.show(Labels.getLabel("client.msgBox.nullFile"), Labels.getLabel("msgBox"), Messagebox.OK, Messagebox.EXCLAMATION);
//		}
//	}
	
	
	
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

	public Media getFoto() {
		return foto;
	}

	public void setFoto(Media foto) {
		this.foto = foto;
	}

	public String getDirFoto() {
		return dirFoto;
	}

	public void setDirFoto(String dirFoto) {
		this.dirFoto = dirFoto;
	}

	public String getDirDoc() {
		return dirDoc;
	}

	public void setDirDoc(String dirDoc) {
		this.dirDoc = dirDoc;
	}

}
