package com.acme.tictactoe.model;

import android.util.SparseArray;

public enum Player {
    X(0),
    O(1),
    NO(2);

    int id;

    Player(int id)
    {
        this.id = id;
    }

    static SparseArray<Player> cache;

    static Player fromInt(int id) {
        if (cache == null) {
            cache = new SparseArray<>();
            Player[] players = Player.values();
            for (Player value : players) {
                cache.put(value.id, value);
            }
        }
        return cache.get(id);
    }
}
