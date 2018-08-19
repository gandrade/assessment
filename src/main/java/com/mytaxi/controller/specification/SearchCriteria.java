package com.mytaxi.controller.specification;

public class SearchCriteria
{
    private String joinObject;
    private String key;
    private String operation;
    private Object value;


    public SearchCriteria(String joinObject, String key, String operation, Object value)
    {
        this(key, operation, value);
        this.joinObject = joinObject;
    }


    public SearchCriteria(String key, String operation, Object value)
    {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }


    public String getKey()
    {
        return key;
    }


    public void setKey(String key)
    {
        this.key = key;
    }


    public String getOperation()
    {
        return operation;
    }


    public void setOperation(String operation)
    {
        this.operation = operation;
    }


    public Object getValue()
    {
        return value;
    }


    public void setValue(Object value)
    {
        this.value = value;
    }


    public String getJoinObject()
    {
        return joinObject;
    }


    public void setJoinObject(String joinObject)
    {
        this.joinObject = joinObject;
    }
}
