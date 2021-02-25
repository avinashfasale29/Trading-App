package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Trading;

public interface TradingRepository extends JpaRepository<Trading, Long> {

}
