package com.reber.algorithm;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * test
 *
 * @author Tong yuan
 * @version 1.0, 2023-02-17 10:44:00
 */
public class SomeTest {

    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();


    /**
     * 是匹配路径
     *
     * @param path     路径
     * @param patterns 模式
     * @return boolean
     */
    public static boolean isMatchPath(String path, Collection<String> patterns) {
        if (path == null) {
            return false;
        }
        if (!CollectionUtils.isEmpty(patterns)) {
            for (String pattern : patterns) {
                if (ANT_PATH_MATCHER.match(pattern, path)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是匹配路径
     *
     * @param path     路径
     * @param pattern  匹配规则
     * @return boolean
     */
    public static boolean isMatchPath(String path, String pattern) {
        if (path == null) {
            return false;
        }
        return ANT_PATH_MATCHER.match(pattern, path);
    }
}
