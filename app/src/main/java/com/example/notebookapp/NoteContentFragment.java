package com.example.notebookapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteContentFragment extends Fragment {

    public static final String ARG_TITLE = "title";
    public static final int DEF_VAL = -1;
    private Note note;

    public static NoteContentFragment newInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TITLE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_content, container, false);
        EditText editText = view.findViewById(R.id.content_edit_text);
        TypedArray contents = getResources().obtainTypedArray(R.array.contents);
        editText.setText(contents.getResourceId(note.getNoteContentIndex(), DEF_VAL));
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText(note.getNoteTitle());
        contents.recycle();

        return view;
    }













}