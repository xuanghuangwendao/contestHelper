package com.bude.utils.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Result<T> {


    public boolean state;

    public T data;

    public Map<String, Object> info;

    public Result() {
        this.state = true;
        this.data = null;
        info = new HashMap<>();
    }
    public Result(T data) {
        this.state = true;
        this.data = data;
        info = new HashMap<>();
    }
    public Result(boolean state, T data) {
        this.state = state;
        this.data = data;
        info = new HashMap<>();
    }

    public Result(boolean state, T data, Map<String, Object> info) {
        this.state = state;
        this.data = data;
        this.info = info;
    }

    public static String ParamError() {
        return JSON.toJSONString( new Result<>(false, null, Map.of("e", "param error")));
    }

    public static String ServerError() {
        return JSON.toJSONString(new Result<>(false, null, Map.of("e", "server error")));
    }


}