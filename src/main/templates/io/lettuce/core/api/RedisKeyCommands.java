/*
 * Copyright 2017-Present, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 *
 * This file contains contributions from third-party contributors
 * licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core.api;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import io.lettuce.core.*;
import io.lettuce.core.output.KeyStreamingChannel;
import io.lettuce.core.output.ValueStreamingChannel;

/**
 * ${intent} for Keys (Key manipulation/querying).
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 */
public interface RedisKeyCommands<K, V> {

    /**
     * Copy the value stored at the source key to the destination key.
     *
     * @param source the source.
     * @param destination the destination.
     * @return Boolean integer-reply specifically: {@code true} if source was copied. {@code false} if source was not copied.
     * @since 6.1
     */
    Boolean copy(K source, K destination);

    /**
     * Copy the value stored at the source key to the destination key.
     *
     * @param source the source.
     * @param destination the destination.
     * @param copyArgs the copyArgs.
     * @return Boolean integer-reply specifically: {@code true} if source was copied. {@code false} if source was not copied.
     * @since 6.1
     */
    Boolean copy(K source, K destination, CopyArgs copyArgs);

    /**
     * Delete one or more keys.
     *
     * @param keys the keys.
     * @return Long integer-reply The number of keys that were removed.
     */
    Long del(K... keys);

    /**
     * Unlink one or more keys (non blocking DEL).
     *
     * @param keys the keys.
     * @return Long integer-reply The number of keys that were removed.
     */
    Long unlink(K... keys);

    /**
     * Return a serialized version of the value stored at the specified key.
     *
     * @param key the key.
     * @return byte[] bulk-string-reply the serialized value.
     */
    byte[] dump(K key);

    /**
     * Determine how many keys exist.
     *
     * @param keys the keys.
     * @return Long integer-reply specifically: Number of existing keys.
     */
    Long exists(K... keys);

    /**
     * Set a key's time to live in seconds.
     *
     * @param key the key.
     * @param seconds the seconds type: long.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set.
     */
    Boolean expire(K key, long seconds);

    /**
     * Set a key's time to live in seconds.
     *
     * @param key the key.
     * @param seconds the seconds type: long.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set.
     * @since 6.2
     */
    Boolean expire(K key, long seconds, ExpireArgs expireArgs);

    /**
     * Set a key's time to live in seconds.
     *
     * @param key the key.
     * @param seconds the seconds.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set.
     * @since 6.1
     */
    Boolean expire(K key, Duration seconds);

    /**
     * Set a key's time to live in seconds.
     *
     * @param key the key.
     * @param seconds the seconds.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set.
     * @since 6.2
     */
    Boolean expire(K key, Duration seconds, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     */
    Boolean expireat(K key, long timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean expireat(K key, long timestamp, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     */
    Boolean expireat(K key, Date timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean expireat(K key, Date timestamp, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.1
     */
    Boolean expireat(K key, Instant timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean expireat(K key, Instant timestamp, ExpireArgs expireArgs);

    /**
     * Get the time to live for a key in as unix timestamp in seconds.
     *
     * @param key the key.
     * @return Long integer-reply in seconds, or a negative value in order to signal an error. The command returns {@code -1} if
     *         the key exists but has no associated expiration time. The command returns {@code -2} if the key does not exist.
     * @since 6.2
     */
    Long expiretime(K key);

    /**
     * Find all keys matching the given pattern.
     *
     * @param pattern the pattern type: patternkey (pattern).
     * @return List&lt;K&gt; array-reply list of keys matching {@code pattern}.
     */
    List<K> keys(K pattern);

    /**
     * Find all keys matching the given pattern.
     *
     * @param channel the channel.
     * @param pattern the pattern.
     * @return Long array-reply list of keys matching {@code pattern}.
     */
    Long keys(KeyStreamingChannel<K> channel, K pattern);

    /**
     * Atomically transfer a key from a Redis instance to another one.
     *
     * @param host the host.
     * @param port the port.
     * @param key the key.
     * @param db the database.
     * @param timeout the timeout in milliseconds.
     * @return String simple-string-reply The command returns OK on success.
     */
    String migrate(String host, int port, K key, int db, long timeout);

    /**
     * Atomically transfer one or more keys from a Redis instance to another one.
     *
     * @param host the host.
     * @param port the port.
     * @param db the database.
     * @param timeout the timeout in milliseconds.
     * @param migrateArgs migrate args that allow to configure further options.
     * @return String simple-string-reply The command returns OK on success.
     */
    String migrate(String host, int port, int db, long timeout, MigrateArgs<K> migrateArgs);

    /**
     * Move a key to another database.
     *
     * @param key the key.
     * @param db the db type: long.
     * @return Boolean integer-reply specifically:.
     */
    Boolean move(K key, int db);

    /**
     * Returns the kind of internal representation used in order to store the value associated with the {@code key}.
     *
     * @param key the key.
     * @return String.
     */
    String objectEncoding(K key);

    /**
     * Returns the logarithmic access frequency counter of the object stored at the specified {@code key}.
     *
     * @param key the key.
     * @return Long.
     * @since 6.1
     */
    Long objectFreq(K key);

    /**
     * Returns the number of seconds since the object stored at the specified key is idle (not requested by read or write
     * operations).
     *
     * @param key the key.
     * @return number of seconds since the object stored at the specified key is idle.
     */
    Long objectIdletime(K key);

    /**
     * Returns the number of references of the value associated with the specified key.
     *
     * @param key the key.
     * @return Long.
     */
    Long objectRefcount(K key);

    /**
     * Remove the expiration from a key.
     *
     * @param key the key.
     * @return Boolean integer-reply specifically:
     *
     *         {@code true} if the timeout was removed. {@code false} if {@code key} does not exist or does not have an
     *         associated timeout.
     */
    Boolean persist(K key);

    /**
     * Set a key's time to live in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds type: long.
     * @return integer-reply, specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not exist or
     *         the timeout could not be set.
     */
    Boolean pexpire(K key, long milliseconds);

    /**
     * Set a key's time to live in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds type: long.
     * @param expireArgs the expiry arguments.
     * @return integer-reply, specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not exist or
     *         the timeout could not be set.
     * @since 6.2
     */
    Boolean pexpire(K key, long milliseconds, ExpireArgs expireArgs);

    /**
     * Set a key's time to live in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds.
     * @return integer-reply, specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not exist or
     *         the timeout could not be set.
     * @since 6.1
     */
    Boolean pexpire(K key, Duration milliseconds);

    /**
     * Set a key's time to live in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds.
     * @param expireArgs the expiry arguments.
     * @return integer-reply, specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not exist or
     *         the timeout could not be set.
     * @since 6.2
     */
    Boolean pexpire(K key, Duration milliseconds, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     */
    Boolean pexpireat(K key, long timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean pexpireat(K key, long timestamp, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     */
    Boolean pexpireat(K key, Date timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean pexpireat(K key, Date timestamp, ExpireArgs expireArgs);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     */
    Boolean pexpireat(K key, Instant timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expiry arguments.
     * @return Boolean integer-reply specifically: {@code true} if the timeout was set. {@code false} if {@code key} does not
     *         exist or the timeout could not be set (see: {@code EXPIRE}).
     * @since 6.2
     */
    Boolean pexpireat(K key, Instant timestamp, ExpireArgs expireArgs);

    /**
     * Get the time to live for a key in as unix timestamp in milliseconds.
     *
     * @param key the key.
     * @return Long integer-reply in milliseconds, or a negative value in order to signal an error. The command returns
     *         {@code -1} if the key exists but has no associated expiration time. The command returns {@code -2} if the key
     *         does not exist.
     * @since 6.2
     */
    Long pexpiretime(K key);

    /**
     * Get the time to live for a key in milliseconds.
     *
     * @param key the key.
     * @return Long integer-reply in milliseconds, or a negative value in order to signal an error. The command returns
     *         {@code -1} if the key exists but has no associated expiration time. The command returns {@code -2} if the key
     *         does not exist.
     */
    Long pttl(K key);

    /**
     * Return a random key from the keyspace.
     *
     * @return K bulk-string-reply the random key, or {@code null} when the database is empty.
     */
    K randomkey();

    /**
     * Rename a key.
     *
     * @param key the key.
     * @param newKey the newkey type: key.
     * @return String simple-string-reply.
     */
    String rename(K key, K newKey);

    /**
     * Rename a key, only if the new key does not exist.
     *
     * @param key the key.
     * @param newKey the newkey type: key.
     * @return Boolean integer-reply specifically:
     *
     *         {@code true} if {@code key} was renamed to {@code newkey}. {@code false} if {@code newkey} already exists.
     */
    Boolean renamenx(K key, K newKey);

    /**
     * Create a key using the provided serialized value, previously obtained using DUMP.
     *
     * @param key the key.
     * @param ttl the ttl type: long.
     * @param value the serialized-value type: string.
     * @return String simple-string-reply The command returns OK on success.
     */
    String restore(K key, long ttl, byte[] value);

    /**
     * Create a key using the provided serialized value, previously obtained using DUMP.
     *
     * @param key the key.
     * @param value the serialized-value type: string.
     * @param args the {@link RestoreArgs}, must not be {@code null}.
     * @return String simple-string-reply The command returns OK on success.
     * @since 5.1
     */
    String restore(K key, byte[] value, RestoreArgs args);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param key the key.
     * @return List&lt;V&gt; array-reply list of sorted elements.
     */
    List<V> sort(K key);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @return Long number of values.
     */
    Long sort(ValueStreamingChannel<V> channel, K key);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param key the key.
     * @param sortArgs sort arguments.
     * @return List&lt;V&gt; array-reply list of sorted elements.
     */
    List<V> sort(K key, SortArgs sortArgs);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param sortArgs sort arguments.
     * @return Long number of values.
     */
    Long sort(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param key the key.
     * @return List&lt;V&gt; array-reply list of sorted elements.
     * @since 6.2
     */
    List<V> sortReadOnly(K key);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @return Long number of values.
     * @since 6.2
     */
    Long sortReadOnly(ValueStreamingChannel<V> channel, K key);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param key the key.
     * @param sortArgs sort arguments.
     * @return List&lt;V&gt; array-reply list of sorted elements.
     * @since 6.2
     */
    List<V> sortReadOnly(K key, SortArgs sortArgs);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param channel streaming channel that receives a call for every value.
     * @param key the key.
     * @param sortArgs sort arguments.
     * @return Long number of values.
     * @since 6.2
     */
    Long sortReadOnly(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs);

    /**
     * Sort the elements in a list, set or sorted set.
     *
     * @param key the key.
     * @param sortArgs sort arguments.
     * @param destination the destination key to store sort results.
     * @return Long number of values.
     */
    Long sortStore(K key, SortArgs sortArgs, K destination);

    /**
     * Touch one or more keys. Touch sets the last accessed time for a key. Non-exsitent keys wont get created.
     *
     * @param keys the keys.
     * @return Long integer-reply the number of found keys.
     */
    Long touch(K... keys);

    /**
     * Get the time to live for a key.
     *
     * @param key the key.
     * @return Long integer-reply TTL in seconds, or a negative value in order to signal an error. The command returns
     *         {@code -1} if the key exists but has no associated expiration time. The command returns {@code -2} if the key
     *         does not exist.
     */
    Long ttl(K key);

    /**
     * Determine the type stored at key.
     *
     * @param key the key.
     * @return String simple-string-reply type of {@code key}, or {@code none} when {@code key} does not exist.
     */
    String type(K key);

    /**
     * Incrementally iterate the keys space.
     *
     * @return KeyScanCursor&lt;K&gt; scan cursor.
     */
    KeyScanCursor<K> scan();

    /**
     * Incrementally iterate the keys space. Use {@link KeyScanArgs} to specify {@code SCAN}-specific arguments.
     *
     * @param scanArgs scan arguments.
     * @return KeyScanCursor&lt;K&gt; scan cursor.
     * @see KeyScanArgs
     */
    KeyScanCursor<K> scan(ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space. Use {@link KeyScanArgs} to specify {@code SCAN}-specific arguments.
     *
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @param scanArgs scan arguments.
     * @return KeyScanCursor&lt;K&gt; scan cursor.
     * @see KeyScanArgs
     */
    KeyScanCursor<K> scan(ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     *
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @return KeyScanCursor&lt;K&gt; scan cursor.
     */
    KeyScanCursor<K> scan(ScanCursor scanCursor);

    /**
     * Incrementally iterate the keys space.
     *
     * @param channel streaming channel that receives a call for every key.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor scan(KeyStreamingChannel<K> channel);

    /**
     * Incrementally iterate the keys space. Use {@link KeyScanArgs} to specify {@code SCAN}-specific arguments.
     *
     * @param channel streaming channel that receives a call for every key.
     * @param scanArgs scan arguments.
     * @return StreamScanCursor scan cursor.
     * @see KeyScanArgs
     */
    StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space. Use {@link KeyScanArgs} to specify {@code SCAN}-specific arguments.
     *
     * @param channel streaming channel that receives a call for every key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @param scanArgs scan arguments.
     * @return StreamScanCursor scan cursor.
     * @see KeyScanArgs
     */
    StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     *
     * @param channel streaming channel that receives a call for every key.
     * @param scanCursor cursor to resume from a previous scan, must not be {@code null}.
     * @return StreamScanCursor scan cursor.
     */
    StreamScanCursor scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor);

}
