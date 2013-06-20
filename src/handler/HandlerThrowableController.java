package handler;

import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Init;

@Controller
public class HandlerThrowableController {
	
	@Init
	public void init(@BindingParam("exception") Exception exc) {
		System.out.println(exc);
	}
}