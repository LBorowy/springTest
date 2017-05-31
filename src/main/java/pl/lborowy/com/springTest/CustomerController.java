package pl.lborowy.com.springTest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-05-30.
 */
@Controller
public class CustomerController {
    List<Customer> customers = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, value = "/customer")
    @ResponseBody
    public Customer customer() {
        return new Customer(0,"Adam", "Kowalski", "1234");
    }
    // localhost:9000/customer
    // zwracany jest automatycznie JSON

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    @ResponseBody
    public List<Customer> customers() {
        return customers;
    }
    // localhost:9000/customers
    // zwraca liste Customersów

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{id}")
    @ResponseBody
    public Customer findById(@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    // localhost:9000/customer + PUT
    // localhost:9000/customer/25

    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
    @ResponseBody
    public void deleteById(@PathVariable int id) {
        customers = customers
                .stream()
                .filter(customer -> customer.getId() != id)
                .collect(Collectors.toList());

        // LUB
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id){
                customers.remove(i);
                break;
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}")
    @ResponseBody
    public void update(@RequestBody Customer customer, @PathVariable int id) {
        Customer foundCustomer = findById(id);
        if(foundCustomer != null){
            foundCustomer.setName(customer.getName());
            foundCustomer.setPesel(customer.getPesel());
            foundCustomer.setSurname(customer.getSurname());
        }

    }


    // endpoint do tworzenia klienta (potrzebny toString() w klasie Customer)
    @RequestMapping(method = RequestMethod.POST, value = "/customer")
    @ResponseBody
    public void addCustomer (@RequestBody Customer customer) {
        System.out.println("Otrzymałem obiekt: " + customer);
        customers.add(customer);
    }
}
