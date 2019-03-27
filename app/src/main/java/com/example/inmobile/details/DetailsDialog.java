package com.example.inmobile.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobile.R;
import com.example.inmobile.core.db.entities.Data;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Hassan on 3/27/2019.
 **/
public class DetailsDialog extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_details, container);


        Data data = (Data) getArguments().getSerializable("data");


        AppCompatTextView descriptionView = view.findViewById(R.id.dialog_description);
        AppCompatTextView titleView = view.findViewById(R.id.dialog_title);
        AppCompatImageView imageView = view.findViewById(R.id.dialog_image);
        AppCompatTextView linkView = view.findViewById(R.id.dialog_link);


        descriptionView.setText(data.getDescription());
        titleView.setText(data.getTitle());
        linkView.setText(data.getLink());

        //load image
        Picasso.with(getContext()).load(data.getLink())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        return view;
    }
}
