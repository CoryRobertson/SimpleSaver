package com.github.coryrobertson.simplesaver;

import java.io.*;

/**
 * A save file which can be given an array to save, or load an array from a file
 */
public class Save
{
    private String[] data;
    private char separator = ',';

    /**
     *
     * @param data an array of data
     */
    public Save(String[] data)
    {
        this.data = data;
    }

    /**
     * An empty constructor used for loading the data into this object
     */
    public Save() {}

    /**
     * Writes the data inside this object to the file given as a parameter
     * @param file a file object to save to
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

    /**
     *
     * @param file file object to read from
     * @return a save object
     */
    public String[] readFromSaveFile(File file)
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
        //int length = getCharCount(fileContents) + 1;

        return fileContents.split(",");
    }

    public String[] getData()
    {
        return this.data;
    }

    private int getCharCount(String str)
    {
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == separator)
            {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.data.length; i++)
        {
            if(i == this.data.length - 1)
                str.append(this.data[i]);
            else
                str.append(this.data[i]).append(",");

        }
        return str.toString();
    }
}
