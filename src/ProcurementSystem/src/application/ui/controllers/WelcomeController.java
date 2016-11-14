package application.ui.controllers;

import application.models.User;
import application.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class WelcomeController extends BaseController {
	private UserRepository userRepository;
	
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@FXML
	private void login() {
		User user = userRepository.findUser(email.getText(), password.getText());
		System.out.println(user);
	}
}
