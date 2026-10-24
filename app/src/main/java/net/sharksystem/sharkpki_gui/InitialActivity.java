package net.sharksystem.sharkpki_gui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.sharksystem.SharkException;
import net.sharksystem.SharkPeer;
import net.sharksystem.SharkPeerFS;
import net.sharksystem.asap.ASAP;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.android.ASAPAndroid;
import net.sharksystem.asap.android.apps.ASAPAndroidPeer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class InitialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        PkiApp app = new PkiApp();
        try {
            app.init(this);
        } catch (Exception e) {
            System.err.println("Failed to initialize app: " + e.getMessage());
        }

        this.finish();
        Intent intent = new Intent(this, MainASAPActivity.class);
        this.startActivity(intent);
    }
}