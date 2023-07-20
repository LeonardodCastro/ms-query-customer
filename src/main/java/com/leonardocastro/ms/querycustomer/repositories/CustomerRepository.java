package com.leonardocastro.ms.querycustomer.repositories;

import com.leonardocastro.ms.querycustomer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
