package net.sharksystem.sharkpki_gui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import net.sharksystem.SharkException;
import net.sharksystem.SharkPeer;
import net.sharksystem.SharkPeerFS;
import net.sharksystem.asap.ASAP;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.android.ASAPAndroid;
import net.sharksystem.asap.android.Util;
import net.sharksystem.asap.android.apps.ASAPAndroidPeer;
import net.sharksystem.pki.SharkPKIComponent;
import net.sharksystem.pki.SharkPKIComponentFactory;

import net.sharksystem.asap.fs.ExtraDataFS;

import java.io.File;
import java.io.IOException;

public class PkiApp {
    private SharkPeer sharkPeer;
    private SharkPKIComponent sharkPKI;

    public void init(Activity initialActivity) throws SharkException, IOException {
        String id = ASAP.createUniqueID();
        File rootDir = Util.getASAPRootDirectory(initialActivity, "web_pki_data", id);
        String rootPath = rootDir.getAbsolutePath();

        sharkPeer = new SharkPeerFS(id, rootPath);
        SharkPKIComponentFactory factory = new SharkPKIComponentFactory();
        // FIXME: The following commented code currently leads to an app crash!!!
        // Reason: The SharkPKI package expects a different directory structure for ASAPJava.
        // It doesn't match the structure of the ASAPJava library in this project
        // (more specifically, in the ASAPAndroid.aar).
        // SharkPKI expects net.sharksystem.fs, but in our project it is net.sharksystem.asap.fs
        // But changing it probably isn't trivial because it would break ASAPAndroid's logic.
        // We'll figure something out. I believe SharkNet2Android did it correctly.
        //sharkPeer.addComponent(factory, SharkPKIComponent.class);
        //sharkPKI = (SharkPKIComponent) sharkPeer.getComponent(SharkPKIComponent.class);

        ASAPAndroidPeer.initializePeer(
                id,
                sharkPeer.getFormats(),
                rootPath,
                initialActivity);
        ASAPAndroidPeer asapPeer = ASAPAndroidPeer.startPeer(initialActivity);
        sharkPeer.start(asapPeer);
    }
}
