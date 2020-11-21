package com.support.fst_android_base_lib.base;


import com.support.fst_android_base_lib.util.GsonUtil;

public interface WCallback {
    void completion(int ret, GsonUtil data);
}
