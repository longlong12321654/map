package com.hndist.server.util;

import java.util.HashSet;
import java.util.Set;

public class CustomArrayUtils {

    //求两个数组差集
    public static Long[] intersection(Long[] nums1, Long[] nums2) {

        Set<Long> set1 = new HashSet<>(); // 第一个数组载体
        Set<Long> set2 = new HashSet<>(); // 存放交集元素

        // 先把nums1数组的所有元素放到set中
        for (long num : nums1) {
            set1.add(num);
        }

        // 遍历nums2数组，如果nums2的元素存在于set1中，放入set2中
        for (long num : nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }

        // set2转成数组
        Long[] ret = new Long[set2.size()];
        int i = 0;
        for (long num : set2) {
            ret[i++] = num;
        }
        return ret;
    }


    public static void main(String[] args) {
        Long[] nums1 = new Long[]{1l,2l,3l};
        Long[] nums2 = new Long[]{4l,5l,6l};
        System.out.println(intersection(nums1, nums2));
    }

}
