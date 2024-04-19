package org.services.redis;

import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisService {

    private ReactiveKeyCommands<String> keyCommands;
    private ValueCommands<String, Object> valueCommands;

    public RedisService(RedisDataSource ds, ReactiveRedisDataSource reactive) {
        valueCommands = ds.value(Object.class);
        keyCommands = reactive.key();
    }

    public Object get(String key) {

        Object value = valueCommands.get(key);

        return value;

    }

    public void set(String key, Object value) {
        valueCommands.set(key, value);
    }

    public Uni<Integer> del(String key) {
        return keyCommands.del(key);
    }

}
