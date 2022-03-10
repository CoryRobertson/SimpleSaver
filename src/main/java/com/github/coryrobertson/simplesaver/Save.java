package com.github.coryrobertson.simplesaver;

import java.io.*;
import java.lang.reflect.Array;

public class Save<t>
{
    t[] data;
    char seperator = ',';
    public Save(t[] data)
    {
        this.data = data;
    }

    public Save() {}

    /**
     *
     * @return true if save successful
     */
    public boolean writeToSaveFile(File file)
    {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        for(int i = 0; i < data.length; i++)
        {
            if (i == data.length - 1)
                pw.print(data[i]);
            else
                pw.print(data[i] + ",");

        }
        pw.close();
        return true;
    }


    public Save<t> readFromSaveFile(File file)
    {

        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        BufferedReader br = new BufferedReader(fr);
        String fileContents = "";
        try {
            fileContents = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = getCharCount(fileContents) + 1;

        t[] data;

        data = (t[]) fileContents.split(",");


        return new Save<>(data);
    }

    public String[] getData()
    {
        return (String[]) this.data;
    }

    private int getCharCount(String str)
    {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == seperator)
            {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < this.data.length; i++)
        {
            if(i == this.data.length - 1)
                str += this.data[i];
            else
                str += this.data[i] + ",";

        }
        return str;
    }
}
