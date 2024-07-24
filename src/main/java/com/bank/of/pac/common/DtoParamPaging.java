package com.bank.of.pac.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class DtoParamPaging {
    private int offset;
    private int limit;
    // field yang akan diurutkan
    private String sort;

    //descending atau ascending
    private String order;
    private Map<String,Object> search;

}
