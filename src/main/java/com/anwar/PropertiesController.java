package com.anwar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author anwar
 */

@RestController
public class PropertiesController {

    @Autowired
    private RabbitPropertiesBean rabbitProperties;

    @Autowired(required = false)
    private Properties cloudProperties;

    @GetMapping("/host")
    public String getRabbitHost() {
        return null == rabbitProperties.getRabbitHost() ? "Not available" : rabbitProperties.getRabbitHost();
    }

    @GetMapping("/properties")
    public Properties cloudProperties() {
        return cloudProperties;
    }
}
