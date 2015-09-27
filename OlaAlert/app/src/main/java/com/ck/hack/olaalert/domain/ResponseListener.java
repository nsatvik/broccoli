package com.ck.hack.olaalert.domain;

import com.android.volley.Response;

import org.codehaus.jackson.type.JavaType;

/**
 * Created by Satvik on 27/09/15.
 */
public abstract class ResponseListener<T> implements Response.Listener<T>, Response.ErrorListener {
    private JavaType returnType;

    public ResponseListener(JavaType returnType) {
        this.returnType = returnType;
    }

    public ResponseListener(Class<?> clazz) {
        //this.returnType = Serializer.getTypeFactory().constructType(clazz);
    }

    public JavaType getReturnType() {
        return this.returnType;
    }
}
