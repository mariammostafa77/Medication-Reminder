package com.example.medicationreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class MedicationsListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medications_list, container, false);
        Button AddMedBtn = view.findViewById(R.id.btnAddMed);


        AddMedBtn.setOnClickListener(this::onClick);
        return view;
    }
    private void onClick(View AddMedBtnView) {
        if (getActivity() != null) {
            NavController navController = Navigation.findNavController(AddMedBtnView);
            MedicationsListFragmentDirections.actionMedicationsListFragmentToActiveMedicationsFragment();
            NavDirections navDirections =MedicationsListFragmentDirections.actionMedicationsListFragmentToActiveMedicationsFragment();
            //NavDirections navDirections = MedicationsListFragmentDirections.actionMedicationsListFragmentToAddMedicationsFragment();
           navController.navigate(navDirections);
        }
    }
}
