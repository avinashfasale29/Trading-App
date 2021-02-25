package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import net.javaguides.springboot.model.Trading;
import net.javaguides.springboot.repository.TradingRepository;


@Service
public class TradingServiceImpl implements TradingService{
	
	@Autowired
	private TradingRepository tradingRepository;

	@Override
	public List<Trading> getAllTrading() {
		return tradingRepository.findAll();
	}

	@Override
	public void saveEmployee(Trading employee) {
		this.tradingRepository.save(employee);
	}

	@Override
	public Trading getEmployeeById(long orderid) {
		Optional<Trading> optional = tradingRepository.findById(orderid);
		Trading employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + orderid);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long orderid) {
		this.tradingRepository.deleteById(orderid);
	}

	@Override
	public Page<Trading> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.tradingRepository.findAll(pageable);
	}


}
