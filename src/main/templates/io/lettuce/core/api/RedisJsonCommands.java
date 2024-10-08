/*
 * Copyright 2017-2024, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 */
package io.lettuce.core.api;

import io.lettuce.core.json.JsonType;
import io.lettuce.core.json.JsonValue;
import io.lettuce.core.json.arguments.JsonGetArgs;
import io.lettuce.core.json.arguments.JsonMsetArgs;
import io.lettuce.core.json.JsonPath;
import io.lettuce.core.json.arguments.JsonRangeArgs;
import io.lettuce.core.json.arguments.JsonSetArgs;

import java.util.List;

/**
 * ${intent} for JSON documents
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Tihomir Mateev
 * @see <a href="https://redis.io/docs/latest/develop/data-types/json/">Redis JSON</a>
 * @since 6.5
 */
public interface RedisJsonCommands<K, V> {

    /**
     * Append the JSON values into the array at a given {@link JsonPath} after the last element in a said array.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param values one or more {@link JsonValue} to be appended.
     * @return Long the resulting size of the arrays after the new data was appended, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrappend(K key, JsonPath jsonPath, JsonValue... values);

    /**
     * Append the JSON values into the array at the {@link JsonPath#ROOT_PATH} after the last element in a said array.
     *
     * @param key the key holding the JSON document.
     * @param values one or more {@link JsonValue} to be appended.
     * @return Long the resulting size of the arrays after the new data was appended, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrappend(K key, JsonValue... values);

    /**
     * Search for the first occurrence of a {@link JsonValue} in an array at a given {@link JsonPath} and return its index.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param value the {@link JsonValue} to search for.
     * @param range the {@link JsonRangeArgs} to search within.
     * @return Long the index hosting the searched element, -1 if not found or null if the specified path is not an array.
     * @since 6.5
     */
    List<Long> jsonArrindex(K key, JsonPath jsonPath, JsonValue value, JsonRangeArgs range);

    /**
     * Search for the first occurrence of a {@link JsonValue} in an array at a given {@link JsonPath} and return its index. This
     * method uses defaults for the start and end indexes, see {@link JsonRangeArgs#DEFAULT_START_INDEX} and
     * {@link JsonRangeArgs#DEFAULT_END_INDEX}.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param value the {@link JsonValue} to search for.
     * @return Long the index hosting the searched element, -1 if not found or null if the specified path is not an array.
     * @since 6.5
     */
    List<Long> jsonArrindex(K key, JsonPath jsonPath, JsonValue value);

    /**
     * Insert the {@link JsonValue}s into the array at a given {@link JsonPath} before the provided index, shifting the existing
     * elements to the right
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param index the index before which the new elements will be inserted.
     * @param values one or more {@link JsonValue}s to be inserted.
     * @return Long the resulting size of the arrays after the new data was inserted, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrinsert(K key, JsonPath jsonPath, int index, JsonValue... values);

    /**
     * Report the length of the JSON array at a given {@link JsonPath}
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @return the size of the arrays, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrlen(K key, JsonPath jsonPath);

    /**
     * Report the length of the JSON array at a the {@link JsonPath#ROOT_PATH}
     *
     * @param key the key holding the JSON document.
     * @return the size of the arrays, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrlen(K key);

    /**
     * Remove and return {@link JsonValue} at a given index in the array at a given {@link JsonPath}
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param index the index of the element to be removed. Default is -1, meaning the last element. Out-of-range indexes round
     *        to their respective array ends. Popping an empty array returns null.
     * @return List<JsonValue> the removed element, or null if the specified path is not an array.
     * @since 6.5
     */
    List<JsonValue> jsonArrpop(K key, JsonPath jsonPath, int index);

    /**
     * Remove and return {@link JsonValue} at index -1 (last element) in the array at a given {@link JsonPath}
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @return List<JsonValue> the removed element, or null if the specified path is not an array.
     * @since 6.5
     */
    List<JsonValue> jsonArrpop(K key, JsonPath jsonPath);

    /**
     * Remove and return {@link JsonValue} at index -1 (last element) in the array at the {@link JsonPath#ROOT_PATH}
     *
     * @param key the key holding the JSON document.
     * @return List<JsonValue> the removed element, or null if the specified path is not an array.
     * @since 6.5
     */
    List<JsonValue> jsonArrpop(K key);

    /**
     * Trim an array at a given {@link JsonPath} so that it contains only the specified inclusive range of elements. All
     * elements with indexes smaller than the start range and all elements with indexes bigger than the end range are trimmed.
     * <p>
     * Behavior as of RedisJSON v2.0:
     * <ul>
     * <li>If start is larger than the array's size or start > stop, returns 0 and an empty array.</li>
     * <li>If start is < 0, then start from the end of the array.</li>
     * <li>If stop is larger than the end of the array, it is treated like the last element.</li>
     * </ul>
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the array inside the document.
     * @param range the {@link JsonRangeArgs} to trim by.
     * @return Long the resulting size of the arrays after the trimming, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonArrtrim(K key, JsonPath jsonPath, JsonRangeArgs range);

    /**
     * Clear container values (arrays/objects) and set numeric values to 0
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value to clear.
     * @return Long the number of values removed plus all the matching JSON numerical values that are zeroed.
     * @since 6.5
     */
    Long jsonClear(K key, JsonPath jsonPath);

    /**
     * Clear container values (arrays/objects) and set numeric values to 0 at the {@link JsonPath#ROOT_PATH}
     *
     * @param key the key holding the JSON document.
     * @return Long the number of values removed plus all the matching JSON numerical values that are zeroed.
     * @since 6.5
     */
    Long jsonClear(K key);

    /**
     * Deletes a value inside the JSON document at a given {@link JsonPath}
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value to clear.
     * @return Long the number of values removed (0 or more).
     * @since 6.5
     */
    Long jsonDel(K key, JsonPath jsonPath);

    /**
     * Deletes a value inside the JSON document at the {@link JsonPath#ROOT_PATH}
     *
     * @param key the key holding the JSON document.
     * @return Long the number of values removed (0 or more).
     * @since 6.5
     */
    Long jsonDel(K key);

    /**
     * Return the value at the specified path in JSON serialized form.
     * <p>
     * When using a single JSONPath, the root of the matching values is a JSON string with a top-level array of serialized JSON
     * value. In contrast, a legacy path returns a single value.
     * <p>
     * When using multiple JSONPath arguments, the root of the matching values is a JSON string with a top-level object, with
     * each object value being a top-level array of serialized JSON value. In contrast, if all paths are legacy paths, each
     * object value is a single serialized JSON value. If there are multiple paths that include both legacy path and JSONPath,
     * the returned value conforms to the JSONPath version (an array of values).
     *
     * @param key the key holding the JSON document.
     * @param options the {@link JsonGetArgs} to use.
     * @param jsonPaths the {@link JsonPath}s to use to identify the values to get.
     * @return JsonValue the value at path in JSON serialized form, or null if the path does not exist.
     * @since 6.5
     */
    List<JsonValue> jsonGet(K key, JsonGetArgs options, JsonPath... jsonPaths);

    /**
     * Return the value at the specified path in JSON serialized form. Uses defaults for the {@link JsonGetArgs}.
     * <p>
     * When using a single JSONPath, the root of the matching values is a JSON string with a top-level array of serialized JSON
     * value. In contrast, a legacy path returns a single value.
     * <p>
     * When using multiple JSONPath arguments, the root of the matching values is a JSON string with a top-level object, with
     * each object value being a top-level array of serialized JSON value. In contrast, if all paths are legacy paths, each
     * object value is a single serialized JSON value. If there are multiple paths that include both legacy path and JSONPath,
     * the returned value conforms to the JSONPath version (an array of values).
     *
     * @param key the key holding the JSON document.
     * @param jsonPaths the {@link JsonPath}s to use to identify the values to get.
     * @return JsonValue the value at path in JSON serialized form, or null if the path does not exist.
     * @since 6.5
     */
    List<JsonValue> jsonGet(K key, JsonPath... jsonPaths);

    /**
     * Merge a given {@link JsonValue} with the value matching {@link JsonPath}. Consequently, JSON values at matching paths are
     * updated, deleted, or expanded with new children.
     * <p>
     * Merging is done according to the following rules per JSON value in the value argument while considering the corresponding
     * original value if it exists:
     * <ul>
     * <li>merging an existing object key with a null value deletes the key</li>
     * <li>merging an existing object key with non-null value updates the value</li>
     * <li>merging a non-existing object key adds the key and value</li>
     * <li>merging an existing array with any merged value, replaces the entire array with the value</li>
     * </ul>
     * <p>
     * This command complies with RFC7396 "Json Merge Patch"
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value to merge.
     * @param value the {@link JsonValue} to merge.
     * @return String "OK" if the set was successful, error if the operation failed.
     * @since 6.5
     * @see <A href="https://tools.ietf.org/html/rfc7396">RFC7396</a>
     */
    String jsonMerge(K key, JsonPath jsonPath, JsonValue value);

    /**
     * Return the values at the specified path from multiple key arguments.
     *
     * @param jsonPath the {@link JsonPath} pointing to the value to fetch.
     * @param keys the keys holding the {@link JsonValue}s to fetch.
     * @return List<JsonValue> the values at path, or null if the path does not exist.
     * @since 6.5
     */
    List<JsonValue> jsonMGet(JsonPath jsonPath, K... keys);

    /**
     * Set or update one or more JSON values according to the specified {@link JsonMsetArgs}
     * <p>
     * JSON.MSET is atomic, hence, all given additions or updates are either applied or not. It is not possible for clients to
     * see that some keys were updated while others are unchanged.
     * <p>
     * A JSON value is a hierarchical structure. If you change a value in a specific path - nested values are affected.
     *
     * @param arguments the {@link JsonMsetArgs} specifying the values to change.
     * @return "OK" if the operation was successful, error otherwise
     * @since 6.5
     */
    String jsonMSet(List<JsonMsetArgs<K, V>> arguments);

    /**
     * Increment the number value stored at the specified {@link JsonPath} in the JSON document by the provided increment.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value to increment.
     * @param number the increment value.
     * @return a {@link List} of the new values after the increment.
     * @since 6.5
     */
    List<Number> jsonNumincrby(K key, JsonPath jsonPath, Number number);

    /**
     * Return the keys in the JSON document that are referenced by the given {@link JsonPath}
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s) whose key(s) we want.
     * @return List<V> the keys in the JSON document that are referenced by the given {@link JsonPath}.
     * @since 6.5
     */
    List<V> jsonObjkeys(K key, JsonPath jsonPath);

    /**
     * Return the keys in the JSON document that are referenced by the {@link JsonPath#ROOT_PATH}
     *
     * @param key the key holding the JSON document.
     * @return List<V> the keys in the JSON document that are referenced by the given {@link JsonPath}.
     * @since 6.5
     */
    List<V> jsonObjkeys(K key);

    /**
     * Report the number of keys in the JSON object at the specified {@link JsonPath} and for the provided key
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s) whose key(s) we want to count
     * @return Long the number of keys in the JSON object at the specified path, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonObjlen(K key, JsonPath jsonPath);

    /**
     * Report the number of keys in the JSON object at the {@link JsonPath#ROOT_PATH} and for the provided key
     *
     * @param key the key holding the JSON document.
     * @return Long the number of keys in the JSON object at the specified path, or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonObjlen(K key);

    /**
     * Sets the JSON value at a given {@link JsonPath} in the JSON document.
     * <p>
     * For new Redis keys, the path must be the root. For existing keys, when the entire path exists, the value that it contains
     * is replaced with the JSON value. For existing keys, when the path exists, except for the last element, a new child is
     * added with the JSON value.
     * <p>
     * Adds a key (with its respective value) to a JSON Object (in a RedisJSON data type key) only if it is the last child in
     * the path, or it is the parent of a new child being added in the path. Optional arguments NX and XX modify this behavior
     * for both new RedisJSON data type keys and the JSON Object keys in them.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s) where we want to set the value.
     * @param value the {@link JsonValue} to set.
     * @param options the {@link JsonSetArgs} the options for setting the value.
     * @return String "OK" if the set was successful, null if the {@link JsonSetArgs} conditions are not met.
     * @since 6.5
     */
    String jsonSet(K key, JsonPath jsonPath, JsonValue value, JsonSetArgs options);

    /**
     * Sets the JSON value at a given {@link JsonPath} in the JSON document using defaults for the {@link JsonSetArgs}.
     * <p>
     * For new Redis keys the path must be the root. For existing keys, when the entire path exists, the value that it contains
     * is replaced with the JSON value. For existing keys, when the path exists, except for the last element, a new child is
     * added with the JSON value.
     * <p>
     * Adds a key (with its respective value) to a JSON Object (in a RedisJSON data type key) only if it is the last child in
     * the path, or it is the parent of a new child being added in the path. Optional arguments NX and XX modify this behavior
     * for both new RedisJSON data type keys and the JSON Object keys in them.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s) where we want to set the value.
     * @param value the {@link JsonValue} to set.
     * @return String "OK" if the set was successful, null if the {@link JsonSetArgs} conditions are not met.
     * @since 6.5
     */
    String jsonSet(K key, JsonPath jsonPath, JsonValue value);

    /**
     * Append the json-string values to the string at the provided {@link JsonPath} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s) where we want to append the value.
     * @param value the {@link JsonValue} to append.
     * @return Long the new length of the string, or null if the matching JSON value is not a string.
     * @since 6.5
     */
    List<Long> jsonStrappend(K key, JsonPath jsonPath, JsonValue value);

    /**
     * Append the json-string values to the string at the {@link JsonPath#ROOT_PATH} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @param value the {@link JsonValue} to append.
     * @return Long the new length of the string, or null if the matching JSON value is not a string.
     * @since 6.5
     */
    List<Long> jsonStrappend(K key, JsonValue value);

    /**
     * Report the length of the JSON String at the provided {@link JsonPath} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s).
     * @return Long (in recursive descent) the length of the JSON String at the provided {@link JsonPath}, or null if the value
     *         ath the desired path is not a string.
     * @since 6.5
     */
    List<Long> jsonStrlen(K key, JsonPath jsonPath);

    /**
     * Report the length of the JSON String at the {@link JsonPath#ROOT_PATH} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @return Long (in recursive descent) the length of the JSON String at the provided {@link JsonPath}, or null if the value
     *         ath the desired path is not a string.
     * @since 6.5
     */
    List<Long> jsonStrlen(K key);

    /**
     * Toggle a Boolean value stored at the provided {@link JsonPath} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s).
     * @return List<Long> the new value after the toggle, 0 for false, 1 for true or null if the path does not exist.
     * @since 6.5
     */
    List<Long> jsonToggle(K key, JsonPath jsonPath);

    /**
     * Report the type of JSON value at the provided {@link JsonPath} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @param jsonPath the {@link JsonPath} pointing to the value(s).
     * @return List<JsonType> the type of JSON value at the provided {@link JsonPath}
     * @since 6.5
     */
    List<JsonType> jsonType(K key, JsonPath jsonPath);

    /**
     * Report the type of JSON value at the {@link JsonPath#ROOT_PATH} in the JSON document.
     *
     * @param key the key holding the JSON document.
     * @return List<JsonType> the type of JSON value at the provided {@link JsonPath}
     * @since 6.5
     */
    List<JsonType> jsonType(K key);

}
