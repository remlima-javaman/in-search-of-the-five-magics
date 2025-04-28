package rti.glitch.br.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rti.glitch.br.service.JsonNodeService;

@Controller
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {

    private final JsonNodeService jsonNodeService;

    @GetMapping("/conversation/{switch}")
    public String index(Model model, @PathVariable("switch") String parameter) {

        model.addAttribute("json", this.jsonNodeService.getJsonNode(parameter));
        return "device";
    }

}
