package com.azzarcher.sapcult;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by azzarcher on 20/10/2016.
 */

public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.main);

        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera, this);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    public static Camera getCameraInstance(){
        return Camera.open(); // returns null if camera is unavailable
    }

    public void toSettings(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void subjectDialog(View v) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Summary");

        final TextView editText = (TextView) dialog.findViewById(R.id.editText);
        editText.setText(R.string.text_subject_duomo);
        dialog.show();
    }

    public void archDialog(View v) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Architecture");

        final TextView editText = (TextView) dialog.findViewById(R.id.editText);
        editText.setText(R.string.text_arch_duomo);
        dialog.show();
    }

    public void infoDialog(View v) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Information");

        final TextView editText = (TextView) dialog.findViewById(R.id.editText);
        editText.setText(R.string.text_info_duomo);
        dialog.show();
    }
}