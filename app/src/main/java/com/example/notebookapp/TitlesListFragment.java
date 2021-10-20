package com.example.notebookapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TitlesListFragment extends Fragment {

    public static final String CURRENT_NOTE_TITLE = "CurrentTitle";
    public static final int DEF_VALUE = 0;
    private Note currentNote;
    private boolean isLand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_titles_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE_TITLE);
        } else {
            currentNote = new Note(getResources().getStringArray(R.array.titles)[DEF_VALUE], DEF_VALUE);
        }

        if (isLand) {
            showNoteContentLand(currentNote);
        }

        initList(view);
    }

    private void initList(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] titles = getResources().getStringArray(R.array.titles);

        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            TextView textView = new TextView(getContext());
            textView.setText(title);
            textView.setTextSize(30);
            linearLayout.addView(textView);

            final int fi = i;
            textView.setOnClickListener(v -> {
                currentNote = new Note(getResources().getStringArray(R.array.titles)[fi], fi);
                showNoteContent(currentNote);
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE_TITLE, currentNote);
        super.onSaveInstanceState(outState);
    }

    private void showNoteContent(Note currentNote) {
        if(isLand){
            showNoteContentLand(currentNote);
        } else {
            showNoteContentPort(currentNote);
        }
    }

    private void showNoteContentLand(Note currentNote) {
        NoteContentFragment contentFragment = NoteContentFragment.newInstance(currentNote);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_land, contentFragment)
                .commit();
    }

    private void showNoteContentPort(Note currentNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ContentActivity.class);
        intent.putExtra(NoteContentFragment.ARG_TITLE, currentNote);
        startActivity(intent);
    }




}