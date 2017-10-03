package eu.dazzled.dmitriiz.demo.controller;

import eu.dazzled.dmitriiz.demo.model.DemoRequest;
import eu.dazzled.dmitriiz.demo.model.DemoResponse;
import eu.dazzled.dmitriiz.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class DemoController {

    private final DemoService demoService;
    private final Random random;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
        this.random = new Random();
    }

    @GetMapping
    public String getHandler() {
        return "demo";
    }

    @PostMapping
    public String postHandler(Model model) {
        DemoRequest request = new DemoRequest();
        request.setId(random.nextInt());
        DemoResponse response = demoService.perform(request);
        model.addAttribute("demo", response);
        return "demo";
    }
}
