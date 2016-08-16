package com.osgzz.myweather.util;

/**
 * Created by Focus on 2016/8/15.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
