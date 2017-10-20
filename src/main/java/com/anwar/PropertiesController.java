package com.anwar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * @author anwar
 */

@RestController
public class PropertiesController {

    @Autowired
    private RabbitProperties rabbitProperties;

    @Autowired(required = false)
    @Qualifier("cloudProperties")
    private Properties cloudProperties;

    @GetMapping("/rabbit")
    public RabbitProperties getRabbit() {
        return rabbitProperties;
    }

    @GetMapping("/properties")
    public String cloudProperties() {
        StringBuilder sb = new StringBuilder("{\n");
        for (Map.Entry entry : cloudProperties.entrySet()) {
            if (sb.length() > 5) {
                sb.append(",");
            }
            sb.append("\"").append(entry.getKey().toString())
                    .append("\"")
                    .append(" : ")
                    .append("\"")
                    .append(entry.getValue().toString())
                    .append("\"");
        }
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }
}
