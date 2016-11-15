package application.ui.controllers;

import application.models.User;

public class BaseController {
	protected ApplicationController applicationController;

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public User getCurrentUser() {
		return applicationController.getCurrentUser();
	}
	
	public void onLoad() {}
}
