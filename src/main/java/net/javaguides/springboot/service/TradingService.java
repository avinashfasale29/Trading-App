package net.javaguides.springboot.service;


import java.util.List;

import org.springframework.data.domain.Page;


import net.javaguides.springboot.model.Trading;

public interface TradingService {
	
	void saveEmployee(Trading employee);
	Trading getEmployeeById(long orderid);
	void deleteEmployeeById(long orderid);
	Page<Trading> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	List<Trading> getAllTrading();

}
