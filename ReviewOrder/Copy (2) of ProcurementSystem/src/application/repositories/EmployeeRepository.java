package application.repositories;

import java.util.List;

import application.models.Employee;
import application.models.User;

public interface EmployeeRepository {


		List<Employee> getTheEmployees();
		void queryEmployee();
		void Approved();
		void Rejected();

}
