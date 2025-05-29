package infrastructure.repositories;

import domain.model.Customer;

import java.util.*;

public class CustomerRepository {
    private final Map<UUID, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(customers.get(id));
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}