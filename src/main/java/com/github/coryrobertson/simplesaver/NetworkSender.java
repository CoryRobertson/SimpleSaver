package com.github.coryrobertson.simplesaver;

public class NetworkSender
{
    public static void main(String[] args)
    {
        Serializer.sendSave(args,"localhost", 5000);
    }
}
