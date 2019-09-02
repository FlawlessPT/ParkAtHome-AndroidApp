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

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.managers.LoggedUser;
import pt.park_at_home.parkathome.utils.SimpleAlert;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_profile, container, false);

        Button updateButton = VIEW.findViewById(R.id.updateDataButton);
        EditText nomeEditText = VIEW.findViewById(R.id.editText1);
        EditText emailEditText = VIEW.findViewById(R.id.editText2);
        EditText contactoEditText = VIEW.findViewById(R.id.editText3);
        EditText nifEditText = VIEW.findViewById(R.id.editText4);
        EditText passwordEditText = VIEW.findViewById(R.id.editText6);

        LoggedUser loggedUser2 = new LoggedUser(this.getActivity());
        nomeEditText.setText(loggedUser2.getAllData()[1]);
        emailEditText.setText(loggedUser2.getAllData()[2]);
        contactoEditText.setText(loggedUser2.getAllData()[3]);
        nifEditText.setText(loggedUser2.getAllData()[4]);
        passwordEditText.setText(loggedUser2.getAllData()[6]);

        updateButton.setOnClickListener(v ->
        {
            String nome = nomeEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String contacto = contactoEditText.getText().toString();
            String nif = nifEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            LoggedUser loggedUser = new LoggedUser(this.getActivity(), nome, email, contacto, nif, password);
            LoggedUser loggedUser1 = new LoggedUser(this.getActivity());
            loggedUser1.setUserName(loggedUser.getUserName());
            loggedUser.updateUserData();
            loggedUser1.saveUserTempData();
            SimpleAlert alert2 = new SimpleAlert(this.getActivity());
            alert2.setMessage("Dados atualizados com sucesso!");
            alert2.show();
        });

        return VIEW;
    }
}
