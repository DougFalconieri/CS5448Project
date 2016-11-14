package application.ui.controllers;

import application.ui.views.ViewLoader;
import javafx.stage.Stage;

public class BaseController {
	protected ViewLoader viewLoader;

	public void setViewLoader(ViewLoader viewLoader) {
		this.viewLoader = viewLoader;
	}
}
