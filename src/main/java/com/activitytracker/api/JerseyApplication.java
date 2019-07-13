package com.activitytracker.api;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        packages("com.activitytracker.api");
    }
}