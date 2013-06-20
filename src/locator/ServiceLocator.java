package locator;

import org.zkoss.zkplus.spring.SpringUtil;

public class ServiceLocator {
	
	
	public ServiceLocator() {}
	
	public static String getBean(String key) {
		return (String) SpringUtil.getBean(key, String.class);
	}

//	public static IMgrCliente getMgrCliente() {
//		return (IMgrCliente) SpringUtil.getBean("mgrCliente", IMgrCliente.class);
//	}
}
