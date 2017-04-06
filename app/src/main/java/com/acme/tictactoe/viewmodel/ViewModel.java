package com.acme.tictactoe.viewmodel;

import android.os.Bundle;

public interface ViewModel {

    void onCreate();
    void onPause();
    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void loadFromSavedInstanceState(Bundle bundle);

    void onDestroy();

}
