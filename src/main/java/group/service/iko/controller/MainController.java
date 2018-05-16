package group.service.iko.controller;

import group.service.iko.db.CustomerHibernateDAO;
import group.service.iko.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("/")
public class MainController {
    @Autowired
    CustomerHibernateDAO customerHibernateDAO;
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        Customer customer = customerHibernateDAO.getCustomerById(1);

        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

}
