package pt.park_at_home.parkathome.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.SimpleAlert;

public class Park {

    private Context context;
    private String vaga;
    private String selectedMatricula;

    public Park(Context context) {
        this.context = context;
    }

    public Park(Context context, String selectedMatricula) {
        this.context = context;
        this.selectedMatricula = selectedMatricula;
    }

    public Park(Context context, String vaga, String selectedMatricula) {
        this.vaga = vaga;
        this.context = context;
        this.selectedMatricula = selectedMatricula;
    }

    public void unSaveSpace(String vaga) {
        DBFunctions functions = new DBFunctions(this.context);
        LoggedUser loggedUser = new LoggedUser(this.context);
        try {
            functions.runCommand("DELETE FROM lugares WHERE Vaga='" + vaga + "'");
            functions.runCommand("UPDATE matriculas SET SavedSpaces=" + (loggedUser.getSavedPlaces() - 1) + " WHERE NomeUtilizador='" + loggedUser.getUserName() +"'");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SimpleDateFormat")
    public void saveSpace() {
        DBFunctions functions = new DBFunctions(this.context);
        LoggedUser loggedUser = new LoggedUser(this.context);
        Date date = new Date();
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String data = format.format(date).substring(0, 10);
        String hora = format.format(date).substring(11);
        try {
            functions.runCommand(String.format("INSERT INTO Lugares (Vaga, NomeUtilizador, Matricula, DataEntrada, HoraEntrada) VALUES ('%s', '%s', '%s', '%s', '%s')", this.vaga, loggedUser.getUserName(), this.selectedMatricula, data, hora));
            functions.runCommand("UPDATE matriculas SET SavedSpaces=" + (loggedUser.getSavedPlaces() + 1) + " WHERE NomeUtilizador='" + loggedUser.getUserName() +"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLugaresReservados() {
        int contar = 0;
        ArrayList<String> lugaresReservados = new ArrayList<>();
        DBFunctions functions = new DBFunctions(this.context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT Vaga FROM lugares");
            while (resultSet.next()) {
                lugaresReservados.add(resultSet.getString("Vaga"));
                //System.out.println(lugaresReservados.get(contar));
                contar++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lugaresReservados;
    }

    public Boolean isSaved() {
        Boolean saved = false;
        DBFunctions functions = new DBFunctions(this.context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT Matricula FROM lugares WHERE Vaga='" + this.vaga +"'");
            if (resultSet.next()) {
                saved = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }

    public int getSavedPlaces() {
        int savedPlaces = 0;
        DBFunctions functions = new DBFunctions(this.context);

        try {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM lugares");
            while (resultSet.next()) {
                savedPlaces++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return savedPlaces;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getSelectedMatricula() {
        return selectedMatricula;
    }

    public void setSelectedMatricula(String selectedMatricula) {
        this.selectedMatricula = selectedMatricula;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getMatriculaFromSavedPlace() {
        String matricula = "";
        DBFunctions functions = new DBFunctions(this.context);

        try {
            ResultSet resultSet = functions.selectCommand("SELECT Matricula FROM lugares WHERE Vaga='" + vaga + "'");
            if (resultSet.next()) {
                matricula = resultSet.getString("Matricula");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return matricula;
    }

    public String getUsernameFromSavedSpace(String vaga) {
        String username = "";
        DBFunctions functions = new DBFunctions(this.context);
//        SimpleAlert simpleAlert = new SimpleAlert(this.context);
//        simpleAlert.setMessage("Vaga: " + vaga);
//        simpleAlert.show();
        try {
            ResultSet resultSet = functions.selectCommand("SELECT NomeUtilizador FROM lugares WHERE Vaga='" + vaga +"'");
            if (resultSet.next()) {
                username = resultSet.getString("NomeUtilizador");
//                SimpleAlert simpleAlert2 = new SimpleAlert(this.context);
//                simpleAlert2.setMessage(username);
//                simpleAlert2.show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }
}
