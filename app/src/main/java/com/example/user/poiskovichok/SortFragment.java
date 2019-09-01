package com.example.user.poiskovichok;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SortFragment extends Fragment {


    Button button;
    EditText editText;
    FragmentCallback mCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);


         editText = view.findViewById(R.id.edit_text);
         button = view.findViewById(R.id.button);


         button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.messageFromSortFragment(editText.getText());
            }
        });


        return view;

    }

    public void setFragmentCallback(FragmentCallback mCallback) {
        this.mCallback = mCallback;
    }

    public interface FragmentCallback{
        void messageFromSortFragment(CharSequence text);
    }




}

