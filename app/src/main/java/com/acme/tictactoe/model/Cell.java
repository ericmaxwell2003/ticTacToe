package com.acme.tictactoe.model;

/**
 * Created by ericmaxwell on 1/19/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Cell implements Parcelable
{

    private Player value;

    protected Cell(Parcel in)
    {
        value = Player.fromInt(in.readInt());
    }

    public static final Creator<Cell> CREATOR = new Creator<Cell>()
    {
        @Override
        public Cell createFromParcel(Parcel in)
        {
            return new Cell(in);
        }

        @Override
        public Cell[] newArray(int size)
        {
            return new Cell[size];
        }
    };

    public Cell()
    {
    }

    public Player getValue() {
        return value;
    }

    public void setValue(Player value) {
        this.value = value;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(value.id);
    }
}