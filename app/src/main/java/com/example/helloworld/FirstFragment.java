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

import java.util.Random;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView showCountTextView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        try{
            View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
            showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);


            binding = FragmentFirstBinding.inflate(inflater, container, false);
            return fragmentFirstLayout;
            //return binding.getRoot();

        } catch (Exception e){
            Log.e("TAG", "onCreateView", e);
            throw e;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
                //NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                displayToast();
                countfunction();
            }
            private void displayToast(){
                Toast mytoast = Toast.makeText(getActivity(), "Toastie :3", Toast.LENGTH_SHORT);
                mytoast.show();
            }
            private void countfunction(){
                String countstr = showCountTextView.getText().toString();
                Integer count = Integer.parseInt(countstr);
                count++;
                showCountTextView.setText(count.toString());
            }
        });

        view.findViewById(R.id.random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setrandomnumber();
            }
            public int GenerateRandomNumber(){

                String countstr = showCountTextView.getText().toString();
                Integer count = Integer.parseInt(countstr);

                Random random = new java.util.Random();
                int randomnumber = 0;
                if (count > 0){
                    randomnumber = random.nextInt(count + 1);
                }
                return randomnumber;
            }
            private void setrandomnumber(){
                int randnum = GenerateRandomNumber();
                String num = String.valueOf(randnum);
                showCountTextView.setText(num);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}