package com.example.user.poiskovichok;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CafeFragment extends Fragment  {


    TextView textView;
    CharSequence text;
    CharSequence mCurrentText = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe, container, false);
        //textView = view.findViewById(R.id.textErr);

       // Bundle bundle = getArguments();
       // if(bundle == null){

       // }else {
       //     String message = bundle.getCharSequence("message").toString();
       //     textView.setText(message);
       // }

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getCharSequence("message"));
        } else if (!mCurrentText.equals("")) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(text);
        }
    }

    public void updateArticleView(CharSequence text) {
        TextView article = (TextView) getActivity().findViewById(R.id.textErr);
        article.setText(text);
        mCurrentText = text;
    }

    public void setText(CharSequence message){
        textView = getView().findViewById(R.id.textErr);
        textView.setText(message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putCharSequence("message", text);
    }


}
