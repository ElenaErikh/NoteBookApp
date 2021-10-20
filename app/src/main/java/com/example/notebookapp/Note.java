package com.example.notebookapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String noteTitle;
    private String noteDate;
    private int noteContentIndex;

    public int getNoteContentIndex() {
        return noteContentIndex;
    }

    public Note(String noteTitle, int noteIndex) {
        this.noteTitle = noteTitle;
        this.noteDate = null;
        this.noteContentIndex = noteIndex;
    }

    public Note() {
        this.noteTitle = "no title";
        this.noteDate = "no date";
        this.noteContentIndex = 0;
    }

    protected Note(Parcel in) {
        noteTitle = in.readString();
        noteDate = in.readString();
        noteContentIndex = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDate() {
        return noteDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(noteTitle);
        parcel.writeString(noteDate);
        parcel.writeInt(noteContentIndex);
    }
}
