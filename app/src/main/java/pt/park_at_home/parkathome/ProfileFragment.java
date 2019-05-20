package pt.park_at_home.parkathome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileFragment extends Fragment
{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View ROOTVIEW = inflater.inflate(R.layout.fragment_profile, container, false);

        Button updateButton = ROOTVIEW.findViewById(R.id.updateDataButton);
        EditText nome = ROOTVIEW.findViewById(R.id.editText1);
        EditText email = ROOTVIEW.findViewById(R.id.editText2);
        EditText noTelemovel = ROOTVIEW.findViewById(R.id.editText3);
        EditText nif = ROOTVIEW.findViewById(R.id.editText4);
        EditText nomeUtilizador = ROOTVIEW.findViewById(R.id.editText5);
        EditText pw = ROOTVIEW.findViewById(R.id.editText6);

        updateButton.setOnClickListener(v ->
        {
            Toast.makeText(getActivity(), "Teste", Toast.LENGTH_SHORT).show();
        });

        return ROOTVIEW;
    }
}
