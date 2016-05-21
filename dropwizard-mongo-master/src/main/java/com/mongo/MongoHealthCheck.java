package com.mongo;

import com.mongodb.Mongo;
import com.yammer.metrics.core.HealthCheck;

public class MongoHealthCheck extends HealthCheck {
//http://localhost:8081/healthcheck
    private final Mongo mongo;

    protected MongoHealthCheck(Mongo mongo) {
        super("MongoDBHealthCheck");
        this.mongo = mongo;
    }

    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }

}