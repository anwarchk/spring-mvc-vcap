package com.anwar;

/**
 * @author anwar
 */


public class RabbitProperties {

    private String rabbitHost;
    private String rabbitPort;

    public String getRabbitPort() {
        return rabbitPort;
    }

    public void setRabbitPort(String rabbitPort) {
        this.rabbitPort = rabbitPort;
    }

    public String getRabbitHost() {
        return rabbitHost;
    }

    public void setRabbitHost(String rabbitHost) {
        this.rabbitHost = rabbitHost;
    }
}
