package com.github.coryrobertson.simplesaver;

public class NetworkReceiver
{
    public static void main(String[] args)
    {
        SerializableSave save = Serializer.receiveSave(5000);

        for (String data: (String[]) save.getSaveData())
        {
            System.out.println(data);
        }
    }
}
