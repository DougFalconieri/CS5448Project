package application.repositories;

import application.models.User;

public interface UserRepository {
	User findUser(String email, String password);
}
