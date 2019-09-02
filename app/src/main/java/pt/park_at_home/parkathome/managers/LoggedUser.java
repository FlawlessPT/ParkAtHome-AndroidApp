package pt.park_at_home.parkathome.managers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;

import pt.park_at_home.parkathome.database.DBFunctions;
import pt.park_at_home.parkathome.utils.LoggedUserData;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TempFile;

public class LoggedUser {

    private Context context;
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String nif;
    private String username;
    private String password;
    private int isAdmin;
    private String matricula;
//    private String matricula1;
//    private String matricula2;
//    private String matricula3;
//    private String matricula4;

    public LoggedUser(Context context) {
        this.context = context;
    }

    public LoggedUser(Context context, String name, String email, String contacto, String nif, String password) {
        this.context = context;
        this.name = name;
        this.email = email;
        this.phoneNumber = contacto;
        this.nif = nif;
        this.password = password;
    }

    public LoggedUser(Context context, String matricula) {
        this.context = context;
        this.matricula = matricula;
    }

//    public LoggedUser(Context context, String matricula1, String matricula2) {
//        this.context = context;
//        this.matricula1 = matricula1;
//        this.matricula2 = matricula2;
//    }
//
//    public LoggedUser(Context context, String matricula1, String matricula2, String matricula3) {
//        this.context = context;
//        this.matricula1 = matricula1;
//        this.matricula2 = matricula2;
//        this.matricula3 = matricula3;
//    }
//
//    public LoggedUser(Context context, String matricula1, String matricula2, String matricula3, String matricula4) {
//        this.context = context;
//        this.matricula1 = matricula1;
//        this.matricula2 = matricula2;
//        this.matricula3 = matricula3;
//        this.matricula4 = matricula4;
//    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUserName() {
        TempFile tempFile = new TempFile(context);
        username = tempFile.read();
        return username;
    }

    public void setUserName(String userName) {
        TempFile tempFile = new TempFile(context);
        tempFile.write(userName);
        this.username = userName;
    }


    public String getNome() {
        String nome = "Nome-Perfil";
        DBFunctions functions = new DBFunctions(context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT Nome FROM utilizadores WHERE NomeUtilizador='" + getUserName() + "'");
            if (resultSet.next()) {
                nome = resultSet.getString("Nome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nome;
    }

    public String getMatriculas() {
        StringBuilder stringFinal = new StringBuilder();
        DBFunctions functions = new DBFunctions(this.context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM matriculas WHERE NomeUtilizador='" + getUserName() + "'");
            while (resultSet.next()) {

                String matriculaN1;
                String matriculaN2;
                String matriculaN3;
                String matriculaN4;

                String matricula1 = resultSet.getString("Matricula1");
                String matricula2 = resultSet.getString("Matricula2");
                String matricula3 = resultSet.getString("Matricula3");
                String matricula4 = resultSet.getString("Matricula4");

                if (!matricula1.equalsIgnoreCase("")) {
                    matriculaN1 = matricula1;
                    stringFinal.append(matriculaN1);
                }
                if (!matricula2.equalsIgnoreCase("")) {
                    matriculaN2 = matricula2;
                    stringFinal.append(", ").append(matriculaN2);
                }
                if (!matricula3.equalsIgnoreCase("")) {
                    matriculaN3 = matricula3;
                    stringFinal.append(", ").append(matriculaN3);
                }
                if (!matricula4.equalsIgnoreCase("")) {
                    matriculaN4 = matricula4;
                    stringFinal.append(", ").append(matriculaN4);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringFinal.toString();
    }

    public ArrayList<String> getMatriculasToArray() {
        DBFunctions functions = new DBFunctions(this.context);
        ArrayList<String> matriculasList = new ArrayList<>();
        matriculasList.add("Escolha uma matricula...");
        try {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM matriculas WHERE NomeUtilizador='" + getUserName() + "'");
            if (resultSet.next()) {
                if (!resultSet.getString("Matricula1").equalsIgnoreCase("")) {
                    matriculasList.add(resultSet.getString("Matricula1"));
                }
                if (!resultSet.getString("Matricula2").equalsIgnoreCase("")) {
                    matriculasList.add(resultSet.getString("Matricula2"));
                }
                if (!resultSet.getString("Matricula3").equalsIgnoreCase("")) {
                    matriculasList.add(resultSet.getString("Matricula3"));
                }
                if (!resultSet.getString("Matricula4").equalsIgnoreCase("")) {
                    matriculasList.add(resultSet.getString("Matricula4"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matriculasList;
    }

    public ArrayList<String> getMatriculasList() {
        DBFunctions functions = new DBFunctions(this.context);
        ArrayList<String> matriculasList = new ArrayList<>();
        matriculasList.add("");
        matriculasList.add("");
        matriculasList.add("");
        matriculasList.add("");
        try {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM matriculas WHERE NomeUtilizador='" + getUserName() + "'");
            if (resultSet.next()) {
                if (!resultSet.getString("Matricula1").equalsIgnoreCase(""))
                    matriculasList.set(0, resultSet.getString("Matricula1"));
                if (!resultSet.getString("Matricula2").equalsIgnoreCase(""))
                    matriculasList.set(1, resultSet.getString("Matricula2"));
                if (!resultSet.getString("Matricula3").equalsIgnoreCase(""))
                    matriculasList.set(2, resultSet.getString("Matricula3"));
                if (!resultSet.getString("Matricula4").equalsIgnoreCase(""))
                    matriculasList.set(3, resultSet.getString("Matricula4"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matriculasList;
    }

    public void updateMatricula1() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            String query = String.format("UPDATE matriculas SET Matricula1='%s' WHERE NomeUtilizador='" + getUserName() + "'", this.matricula);
            functions.runCommand(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMatricula2() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            String query = String.format("UPDATE matriculas SET Matricula2='%s' WHERE NomeUtilizador='" + getUserName() + "'", this.matricula);
            functions.runCommand(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMatricula3() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            String query = String.format("UPDATE matriculas SET Matricula3='%s' WHERE NomeUtilizador='" + getUserName() + "'", this.matricula);
            functions.runCommand(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMatricula4() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            String query = String.format("UPDATE matriculas SET Matricula4='%s' WHERE NomeUtilizador='" + getUserName() + "'", this.matricula);
            functions.runCommand(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMatricula2() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            functions.runCommand("UPDATE matriculas SET Matricula2='' WHERE NomeUtilizador='" + this.getUserName() + "'");
            //if (isOnUse(this.matricula) {
            //unSavePlace();
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMatricula3() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            functions.runCommand("UPDATE matriculas SET Matricula3='' WHERE NomeUtilizador='" + this.getUserName() + "'");
            //if (isOnUse(this.matricula) {
            //unSavePlace();
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMatricula4() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            functions.runCommand("UPDATE matriculas SET Matricula4='' WHERE NomeUtilizador='" + this.getUserName() + "'");
            //if (isOnUse(this.matricula) {
            //unSavePlace();
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserData() {
        DBFunctions functions = new DBFunctions(this.context);
        try {
            String query = String.format("UPDATE utilizadores SET Nome='%s', Email='%s', NrTelemovel='" + this.phoneNumber + "', NIF='%s', Password='%s' WHERE NomeUtilizador='" + getUserName() + "'", this.name, this.email, this.nif, this.password);
            functions.runCommand(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUserTempData() {
        DBFunctions functions = new DBFunctions(this.context);
        LoggedUserData loggedUserData = new LoggedUserData(context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT * FROM utilizadores WHERE NomeUtilizador='" + this.username + "'");
            if (resultSet.next()) {
                this.id = Integer.parseInt(resultSet.getString("Id"));
                this.name = resultSet.getString("Nome");
                this.email = resultSet.getString("Email");
                this.phoneNumber = resultSet.getString("NrTelemovel");
                this.nif = resultSet.getString("NIF");
                this.username = resultSet.getString("NomeUtilizador");
                this.password = resultSet.getString("Password");
                this.isAdmin = resultSet.getInt("isAdmin");
                loggedUserData.write(this.id + "\n" + this.name + "\n" + this.email + "\n" + this.phoneNumber + "\n" + this.nif + "\n" + this.username + "\n" + this.password + "\n" + this.isAdmin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getAllData() {
        String data[] = new String[8];

        LoggedUserData loggedUserData = new LoggedUserData(this.context);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loggedUserData.getFile()));
            String line = reader.readLine();

            for (int i = 0; i <= 7; i++) {
                data[i] = line;
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Boolean canSavePlace() {
        Boolean can = false;
        DBFunctions functions = new DBFunctions(this.context);
        try {
            ResultSet resultSet = functions.selectCommand("SELECT SavedSpaces FROM matriculas WHERE NomeUtilizador='" + getUserName() + "'");
            if (resultSet.next()) {
                if (resultSet.getInt("SavedSpaces") <= Variables.USER_MAX_SAVED_SPACES) {
                    can = true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return can;
    }

    public Boolean canUnSavePlace(String vaga) {
        Park park = new Park(this.context);
        //        SimpleAlert simpleAlert = new SimpleAlert(this.context);
        //        simpleAlert.setMessage(vaga);
        //        simpleAlert.show();
        if (park.getUsernameFromSavedSpace(vaga).equalsIgnoreCase(getUserName())) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getSavedPlaces() {
        int savedPlaces = 0;
        DBFunctions functions = new DBFunctions(this.context);

        try {
            ResultSet resultSet = functions.selectCommand("SELECT SavedSpaces FROM matriculas WHERE NomeUtilizador='" + getUserName() +"'");
            if (resultSet.next()) {
                savedPlaces = resultSet.getInt("SavedSpaces");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return savedPlaces;
    }
}
