package com.robert.exam.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResponseTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T resource;

    private String message;

    private int code;
}
