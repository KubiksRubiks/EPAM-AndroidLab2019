package com.epam.imp;

public class Data {
    private String startPoint;
    private String endPoint;
    private int length;
    private int price;

    public Data(String startPoint, String endPoint, int length, int price)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
        this.price = price;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint(){

        return endPoint;
    }

    public int getLength() {

        return length;
    }

    public int getPrice() {

        return price;
    }
}
