package com.lambdaworks.redis;

import java.util.Date;
import java.util.List;

/**
 * Asynchronous executed commands for Server Control.
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 17.05.14 21:32
 */
public interface RedisServerAsyncConnection<K, V> {
    RedisFuture<String> bgrewriteaof();

    RedisFuture<String> bgsave();

    RedisFuture<K> clientGetname();

    RedisFuture<String> clientSetname(K name);

    RedisFuture<String> clientKill(String addr);

    RedisFuture<String> clientPause(long timeout);

    RedisFuture<String> clientList();

    RedisFuture<List<String>> configGet(String parameter);

    RedisFuture<String> configResetstat();

    RedisFuture<String> configRewrite();

    RedisFuture<String> configSet(String parameter, String value);

    RedisFuture<Long> dbsize();

    RedisFuture<String> debugObject(K key);

    RedisFuture<String> flushall();

    RedisFuture<String> flushdb();

    RedisFuture<String> info();

    RedisFuture<String> info(String section);

    RedisFuture<Date> lastsave();

    RedisFuture<String> save();

    void shutdown(boolean save);

    RedisFuture<String> slaveof(String host, int port);

    RedisFuture<String> slaveofNoOne();

    RedisFuture<List<Object>> slowlogGet();

    RedisFuture<List<Object>> slowlogGet(int count);

    RedisFuture<Long> slowlogLen();

    RedisFuture<String> slowlogReset();

    RedisFuture<String> sync();

    RedisFuture<List<V>> time();
}