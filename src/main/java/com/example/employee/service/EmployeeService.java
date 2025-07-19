package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository repo) { this.repo = repo; }

    public List<Employee> getAll() { return repo.findAll(); }

    public Employee getById(Long id) { return repo.findById(id).orElse(null); }

    public Employee create(Employee emp) { return repo.save(emp); }

    public Employee update(Long id, Employee emp) {
        Optional<Employee> existing = repo.findById(id);
        if (existing.isPresent()) {
            Employee e = existing.get();
            e.setName(emp.getName());
            e.setRole(emp.getRole());
            return repo.save(e);
        }
        return null;
    }

    public void delete(Long id) { repo.deleteById(id); }
}