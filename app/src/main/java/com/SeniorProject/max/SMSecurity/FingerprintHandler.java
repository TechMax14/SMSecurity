package com.SeniorProject.max.SMSecurity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler() {}

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null );
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an authentication error: " + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Authentication failed", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Woops: " + helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Door is now locked!", true);
    }

    private void update(String s, boolean b) {

        TextView promptLabel = (TextView) ((Activity)context).findViewById(R.id.promptLabel);
        ImageView FPimage = (ImageView) ((Activity)context).findViewById(R.id.FPimage);

        promptLabel.setText(s);

        if(b == false) {
            promptLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            promptLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary)); // makes text green
            FPimage.setImageResource(R.mipmap.action_done); // changes FP image to green check image
            // the intent that calls the lock option activity/class
            Intent lockOpt = new Intent(context, LockOptions.class);
            context.startActivity(lockOpt);
        }

    }

}
