package pt.park_at_home.parkathome.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import pt.park_at_home.parkathome.managers.Variables;

public class MaxNumberMatriculasFile
{
    private Context context;
    private File file;

    public MaxNumberMatriculasFile(Context context)
    {
        this.context = context;
    }

    public void create()
    {
        try
        {
            try
            {
                this.file = new File(context.getFilesDir(), Variables.FILE_MAX_NUMBER_MATRICULAS);
                //System.out.println("[MaxNumberMatriculasFile] Ficheiro criado com sucesso!");
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void write(String text)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getFile()));
            writer.write(text);
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//    public void writeLine(String text)
//    {
//        try
//        {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(getFile()));
//            writer.write("\n" + text);
//            writer.close();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public String read()
    {
        String line = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(getFile()));
            line = reader.readLine();
            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return line;
    }

    public void destroy()
    {
        if (this.getFile().exists())
        {
            this.file.delete();
        }
    }

    public File getFile()
    {
        if (file == null || !(file.exists()))
        {
            try
            {
                create();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return this.file;
    }

    public String getFileName()
    {
        return this.file.getName();
    }

    public Context getContext()
    {
        return context;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }


}
