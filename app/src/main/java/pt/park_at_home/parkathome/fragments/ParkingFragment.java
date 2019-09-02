package pt.park_at_home.parkathome.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Objects;

import pt.park_at_home.parkathome.R;
import pt.park_at_home.parkathome.managers.LoggedUser;
import pt.park_at_home.parkathome.managers.Park;
import pt.park_at_home.parkathome.utils.SimpleAlert;
import pt.park_at_home.parkathome.utils.TesteMessage;

public class ParkingFragment extends Fragment {
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View VIEW = inflater.inflate(R.layout.fragment_parking, container, false);

        Button vaga1 = VIEW.findViewById(R.id.vaga1);
        Park parking = new Park(this.getActivity());
        ProgressBar progressBar = (ProgressBar) VIEW.findViewById(R.id.progressBar);
        progressBar.setProgress(parking.getSavedPlaces());
        vaga1.setOnClickListener(v -> {
            parking.setVaga("vaga1");
            if (!parking.isSaved()) {
                loadAlert(vaga1, "vaga1", progressBar);
            } else {
                unSaveSpace(vaga1, "vaga1", progressBar);
            }
        });

        Button vaga2 = VIEW.findViewById(R.id.vaga2);
        vaga2.setOnClickListener(v -> {
            parking.setVaga("vaga2");
            if (!parking.isSaved()) {
                loadAlert(vaga2, "vaga2", progressBar);
            } else {
                unSaveSpace(vaga2, "vaga2", progressBar);
            }

        });

        Button vaga3 = VIEW.findViewById(R.id.vaga3);
        vaga3.setOnClickListener(v -> {
            parking.setVaga("vaga3");
            if (!parking.isSaved()) {
                loadAlert(vaga3, "vaga3", progressBar);
            } else {
                unSaveSpace(vaga3, "vaga3", progressBar);
            }
        });

        Button vaga4 = VIEW.findViewById(R.id.vaga4);
        vaga4.setOnClickListener(v -> {
            parking.setVaga("vaga4");
            if (!parking.isSaved()) {
                loadAlert(vaga4, "vaga4", progressBar);
            } else {
                unSaveSpace(vaga4, "vaga4", progressBar);
            }
        });

        Button vaga5 = VIEW.findViewById(R.id.vaga5);
        vaga5.setOnClickListener(v -> {
            parking.setVaga("vaga5");
            if (!parking.isSaved()) {
                loadAlert(vaga5, "vaga5", progressBar);
            } else {
                unSaveSpace(vaga5, "vaga5", progressBar);
            }
        });

        Button vaga6 = VIEW.findViewById(R.id.vaga6);
        vaga6.setOnClickListener(v -> {
            parking.setVaga("vaga6");
            if (!parking.isSaved()) {
                loadAlert(vaga6, "vaga6", progressBar);
            } else {
                unSaveSpace(vaga6, "vaga6", progressBar);
            }
        });

        Button vaga7 = VIEW.findViewById(R.id.vaga7);
        vaga7.setOnClickListener(v -> {
            parking.setVaga("vaga7");
            if (!parking.isSaved()) {
                loadAlert(vaga7, "vaga7", progressBar);
            } else {
                unSaveSpace(vaga7, "vaga7", progressBar);
            }
        });

        Button vaga8 = VIEW.findViewById(R.id.vaga8);
        vaga8.setOnClickListener(v -> {
            parking.setVaga("vaga8");
            if (!parking.isSaved()) {
                loadAlert(vaga8, "vaga8", progressBar);
            } else {
                unSaveSpace(vaga8, "vaga8", progressBar);
            }
        });

        Button vaga9 = VIEW.findViewById(R.id.vaga9);
        vaga9.setOnClickListener(v -> {
            parking.setVaga("vaga9");
            if (!parking.isSaved()) {
                loadAlert(vaga9, "vaga9", progressBar);
            } else {
                unSaveSpace(vaga9, "vaga9", progressBar);
            }
        });

        Button vaga10 = VIEW.findViewById(R.id.vaga10);
        vaga10.setOnClickListener(v -> {
            parking.setVaga("vaga10");
            if (!parking.isSaved()) {
                loadAlert(vaga10, "vaga10", progressBar);
            } else {
                unSaveSpace(vaga10, "vaga10", progressBar);
            }
        });

        Button vaga11 = VIEW.findViewById(R.id.vaga11);
        vaga11.setOnClickListener(v -> {
            parking.setVaga("vaga11");
            if (!parking.isSaved()) {
                loadAlert(vaga11, "vaga11", progressBar);
            } else {
                unSaveSpace(vaga11, "vaga11", progressBar);
            }
        });

        Button vaga12 = VIEW.findViewById(R.id.vaga12);
        vaga12.setOnClickListener(v -> {
            parking.setVaga("vaga12");
            if (!parking.isSaved()) {
                loadAlert(vaga12, "vaga12", progressBar);
            } else {
                unSaveSpace(vaga12, "vaga12", progressBar);
            }
        });

        Button vaga13 = VIEW.findViewById(R.id.vaga13);
        vaga13.setOnClickListener(v -> {
            parking.setVaga("vaga13");
            if (!parking.isSaved()) {
                loadAlert(vaga13, "vaga13", progressBar);
            } else {
                unSaveSpace(vaga13, "vaga13", progressBar);
            }
        });

        Button vaga14 = VIEW.findViewById(R.id.vaga14);
        vaga14.setOnClickListener(v -> {
            parking.setVaga("vaga14");
            if (!parking.isSaved()) {
                loadAlert(vaga14, "vaga14", progressBar);
            } else {
                unSaveSpace(vaga14, "vaga14", progressBar);
            }
        });

        Button vaga15 = VIEW.findViewById(R.id.vaga15);
        vaga15.setOnClickListener(v -> {
            parking.setVaga("vaga15");
            if (!parking.isSaved()) {
                loadAlert(vaga15, "vaga15", progressBar);
            } else {
                unSaveSpace(vaga15, "vaga15", progressBar);
            }
        });

        Button vaga16 = VIEW.findViewById(R.id.vaga16);
        vaga16.setOnClickListener(v -> {
            parking.setVaga("vaga16");
            if (!parking.isSaved()) {
                loadAlert(vaga16, "vaga16", progressBar);
            } else {
                unSaveSpace(vaga16, "vaga16", progressBar);
            }
        });

        Button vaga17 = VIEW.findViewById(R.id.vaga17);
        vaga17.setOnClickListener(v -> {
            parking.setVaga("vaga17");
            if (!parking.isSaved()) {
                loadAlert(vaga17, "vaga17", progressBar);
            } else {
                unSaveSpace(vaga17, "vaga17", progressBar);
            }
        });

        Button vaga18 = VIEW.findViewById(R.id.vaga18);
        vaga18.setOnClickListener(v -> {
            parking.setVaga("vaga18");
            if (!parking.isSaved()) {
                loadAlert(vaga18, "vaga18", progressBar);
            } else {
                unSaveSpace(vaga18, "vaga18", progressBar);
            }
        });

        Park park = new Park(this.getActivity());
        ArrayList<String> lugaresReservados = park.getLugaresReservados();

        if (lugaresReservados.contains("vaga1")) {
            loadVaga(vaga1, "vaga1", parking);
        }
        if (lugaresReservados.contains("vaga2")) {
            loadVaga(vaga2, "vaga2", parking);
        }
        if (lugaresReservados.contains("vaga3")) {
            loadVaga(vaga3, "vaga3", parking);
        }
        if (lugaresReservados.contains("vaga4")) {
            loadVaga(vaga4, "vaga4", parking);
        }
        if (lugaresReservados.contains("vaga5")) {
            loadVaga(vaga5, "vaga5", parking);
        }
        if (lugaresReservados.contains("vaga6")) {
            loadVaga(vaga6, "vaga6", parking);
        }
        if (lugaresReservados.contains("vaga7")) {
            loadVaga(vaga7, "vaga7", parking);
        }
        if (lugaresReservados.contains("vaga8")) {
            loadVaga(vaga8, "vaga8", parking);
        }
        if (lugaresReservados.contains("vaga9")) {
            loadVaga(vaga9, "vaga9", parking);
        }
        if (lugaresReservados.contains("vaga10")) {
            loadVaga(vaga10, "vaga10", parking);
        }
        if (lugaresReservados.contains("vaga11")) {
            loadVaga(vaga11, "vaga11", parking);
        }
        if (lugaresReservados.contains("vaga12")) {
            loadVaga(vaga12, "vaga12", parking);
        }
        if (lugaresReservados.contains("vaga13")) {
            loadVaga(vaga13, "vaga13", parking);
        }
        if (lugaresReservados.contains("vaga14")) {
            loadVaga(vaga14, "vaga14", parking);
        }
        if (lugaresReservados.contains("vaga15")) {
            loadVaga(vaga15, "vaga15", parking);
        }
        if (lugaresReservados.contains("vaga16")) {
            loadVaga(vaga16, "vaga17", parking);
        }
        if (lugaresReservados.contains("vaga17")) {
            loadVaga(vaga17, "vaga17", parking);
        }
        if (lugaresReservados.contains("vaga18")) {
            loadVaga(vaga18, "vaga18", parking);
        }

        return VIEW;
    }

    @SuppressLint("SetTextI18n")
    private void loadAlert(Button button, String vaga, ProgressBar progressBar) {
        LoggedUser loggedUser = new LoggedUser(getActivity());
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        @SuppressLint("InflateParams") View mView = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
        alert.setTitle("Reserva de lugar: ");
        Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_spinner_item, loggedUser.getMatriculasToArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        alert.setPositiveButton("OK", (dialog, which) -> {
            if (!spinner.getSelectedItem().toString().equalsIgnoreCase("Escolha uma matricula...")) {
                if (loggedUser.canSavePlace()) {
                    Park park = new Park(getActivity(), vaga, spinner.getSelectedItem().toString());
                    park.saveSpace();
                    button.setTypeface(null, Typeface.BOLD);
                    button.setText("OCUPADO\n" + spinner.getSelectedItem().toString());
                    button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_border2));
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    TesteMessage.sendToastMessage(getActivity(), "Vaga reservada com sucesso!");
                } else {
                    SimpleAlert simpleAlert = new SimpleAlert(this.getActivity());
                    simpleAlert.setMessage("Atingiu o limite de reservas no parque!");
                    simpleAlert.show();
                }
            }
        });
        alert.setNegativeButton("CANCELAR", (dialog, which) -> dialog.dismiss());

        alert.setView(mView);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @SuppressLint("ResourceType")
    private void unSaveSpace(Button button, String vaga, ProgressBar progressBar) {
        LoggedUser loggedUser = new LoggedUser(getActivity());
        if (loggedUser.canUnSavePlace(vaga)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setTitle("Cancelar reserva: ");
            alert.setMessage("Tem a certeza que pretende eliminar a reserva desta vaga?");
            alert.setNegativeButton("CANCELAR", (dialog, which) -> dialog.dismiss());
            alert.setPositiveButton("CONFIRMAR", (dialog, which) -> {
                Park park = new Park(getActivity(), vaga);
                park.unSaveSpace(vaga);
                button.setTypeface(null, Typeface.NORMAL);
                button.setText(String.valueOf(vaga.substring(4)));
                button.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.button_border));
                progressBar.setProgress(progressBar.getProgress() - 1);
                TesteMessage.sendToastMessage(getActivity(), "Reserva removida com sucesso!");
            });
            AlertDialog dialog2 = alert.create();
            dialog2.show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadVaga(Button button, String vaga, Park parking) {
        button.setTypeface(null, Typeface.BOLD);
        parking.setVaga(vaga);
        button.setText("OCUPADO\n" + parking.getMatriculaFromSavedPlace());
        button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_border2));
    }
}
