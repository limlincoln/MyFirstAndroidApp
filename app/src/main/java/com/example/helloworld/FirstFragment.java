package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.helloworld.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        try{
            //View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
            //showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);
            //return fragmentFirstLayout;

            binding = FragmentFirstBinding.inflate(inflater, container, false);
            return binding.getRoot();

        } catch (Exception e){
            Log.e("TAG", "onCreateView", e);
            throw e;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast mytoast = Toast.makeText(getActivity(), "Toastie :3", Toast.LENGTH_SHORT);
                mytoast.show();
            }
        });

        view.findViewById(R.id.random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countfunction(view);
            }
            private void countfunction(View view){
                String countstr = showCountTextView.getText().toString();
                Integer count = Integer.parseInt(countstr);
                count++;
                showCountTextView.setText(count.toString());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}