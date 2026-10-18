package net.sharksystem.sharkpki_gui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.sharksystem.SharkException;
import net.sharksystem.SharkPeer;
import net.sharksystem.SharkPeerFS;
import net.sharksystem.asap.ASAP;
import net.sharksystem.pki.SharkPKIComponent;
import net.sharksystem.pki.SharkPKIComponentFactory;

// TODO: Probably move service logic into separate ASAPActivity.
public class MainActivity extends AppCompatActivity {
    private boolean serviceActive = false;
    private SharkPeer sharkPeer;
    private SharkPKIComponent sharkPKI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        try {
            this.initPKI();
        } catch (SharkException e) {
            System.err.println("Failed to initialize PKI: " + e.getMessage());
            return;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initPKI() throws SharkException {
        String peerID = ASAP.createUniqueID();
        String peerRoot = ".shark_pki_web_gui_data";
        SharkPeer peer = new SharkPeerFS(peerID, peerRoot);
        peer.addComponent(new SharkPKIComponentFactory(), SharkPKIComponent.class);
        this.sharkPeer = peer;
        this.sharkPKI = (SharkPKIComponent) peer.getComponent(SharkPKIComponent.class);
    }

    public void serviceBtnHandler(View v) {
        serviceActive = !serviceActive;
        String btnText = serviceActive ? "On" : "Off";
        Button b = (Button)v;
        b.setText(btnText);
        // TODO: Here we will start/ stop the service
    }
}