package pt.park_at_home.parkathome.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.managers.LoggedUser;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TesteMessage;

public class MatriculasManagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_matriculas_manager, container, false);

        Button updateButton = VIEW.findViewById(R.id.updateMatriculasButton);
        Button removeMatricula1 = VIEW.findViewById(R.id.removeMatricula1);
        Button removeMatricula2 = VIEW.findViewById(R.id.removeMatricula2);
        Button removeMatricula3 = VIEW.findViewById(R.id.removeMatricula3);
        EditText matricula1EditText = VIEW.findViewById(R.id.matricula1EditText);
        EditText matricula2EditText = VIEW.findViewById(R.id.matricula2EditText);
        EditText matricula3EditText = VIEW.findViewById(R.id.matricula3EditText);
        EditText matricula4EditText = VIEW.findViewById(R.id.matricula4EditText);

        LoggedUser loggedUser = new LoggedUser(this.getActivity());

        ArrayList<String> matriculas = loggedUser.getMatriculasList();
        matricula1EditText.setText(matriculas.get(0));
        if (!matriculas.get(1).equalsIgnoreCase("")) {
            matricula2EditText.setText(matriculas.get(1));
        }
        if (!matriculas.get(2).equalsIgnoreCase("")) {
            matricula3EditText.setText(matriculas.get(2));
        }
        if (!matriculas.get(3).equalsIgnoreCase("")) {
            matricula4EditText.setText(matriculas.get(3));
        }

        removeMatricula1.setOnClickListener(v -> {
            if (!matricula2EditText.getText().toString().equalsIgnoreCase("")) {
                loggedUser.setMatricula(matricula2EditText.getText().toString());
                loggedUser.deleteMatricula2();
                matricula2EditText.setText("");
                TesteMessage.sendToastMessage(getActivity(), "Matricula apagada com sucesso!");
            } else {
                SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                simpleAlert.setMessage("Matricula não definida!");
                simpleAlert.show();
            }
        });

        removeMatricula2.setOnClickListener(v -> {
            if (!matricula3EditText.getText().toString().equalsIgnoreCase("")) {
                loggedUser.setMatricula(matricula3EditText.getText().toString());
                loggedUser.deleteMatricula3();
                matricula3EditText.setText("");
                TesteMessage.sendToastMessage(getActivity(), "Matricula apagada com sucesso!");
            } else {
                SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                simpleAlert.setMessage("Matricula não definida!");
                simpleAlert.show();
            }
        });

        removeMatricula3.setOnClickListener(v -> {
            if (!matricula4EditText.getText().toString().equalsIgnoreCase("")) {
                loggedUser.setMatricula(matricula4EditText.getText().toString());
                loggedUser.deleteMatricula4();
                matricula4EditText.setText("");
                TesteMessage.sendToastMessage(getActivity(), "Matricula apagada com sucesso!");
            } else {
                SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                simpleAlert.setMessage("Matricula não definida!");
                simpleAlert.show();
            }
        });

        updateButton.setOnClickListener(v -> {

            if (!(matricula1EditText.getText().toString().contains(" ")) || !(matricula2EditText.getText().toString().contains(" ")) || !(matricula3EditText.getText().toString().contains(" "))
                    || !(matricula4EditText.getText().toString().contains(" "))) {
                if (!matricula1EditText.getText().toString().isEmpty()) {
                    String matricula1 = matricula1EditText.getText().toString();
                    LoggedUser loggedUser1 = new LoggedUser(this.getActivity(), matricula1);
                    loggedUser1.updateMatricula1();
                } else {
                    SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                    simpleAlert.setMessage("Esta matricula é obrigatória!");
                    simpleAlert.show();
                }

                String matricula2 = matricula2EditText.getText().toString();
                LoggedUser loggedUser2 = new LoggedUser(this.getActivity(), matricula2);
                loggedUser2.updateMatricula2();

                String matricula3 = matricula3EditText.getText().toString();
                LoggedUser loggedUser3 = new LoggedUser(this.getActivity(), matricula3);
                loggedUser3.updateMatricula3();

                String matricula4 = matricula4EditText.getText().toString();
                LoggedUser loggedUser4 = new LoggedUser(this.getActivity(), matricula4);
                loggedUser4.updateMatricula4();
                TesteMessage.sendToastMessage(getActivity(), "Matriculas atualizadas com sucesso!");
            } else {
                SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                simpleAlert.setMessage("As matriculas não podem conter espaços!");
                simpleAlert.show();
            }
        });

        return VIEW;
    }
}
