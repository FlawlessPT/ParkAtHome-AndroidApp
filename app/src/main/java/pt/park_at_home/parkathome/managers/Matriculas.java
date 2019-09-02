package pt.park_at_home.parkathome.managers;

import android.content.Context;

import pt.park_at_home.parkathome.utils.MaxNumberMatriculasFile;

public class Matriculas {

    private Context context;

    public Matriculas(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setMaxNumberMatriculas(int value) {
        MaxNumberMatriculasFile maxNumberMatriculasFile = new MaxNumberMatriculasFile(this.context);
        maxNumberMatriculasFile.write(String.valueOf(value));
    }

    public int getMaxNumberMatriculas() {
        int max;
        MaxNumberMatriculasFile maxNumberMatriculasFile = new MaxNumberMatriculasFile(this.context);
        max = Integer.parseInt(maxNumberMatriculasFile.read());
        return max;
    }
}
