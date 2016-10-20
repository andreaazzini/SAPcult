package com.azzarcher.sapcult;

import android.app.Dialog;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

/**
 * Created by azzarcher on 20/10/2016.
 */

public class PictureTaker implements Camera.PictureCallback
{
    private Camera mCam;
    private SurfaceView surfaceView;
    private CameraActivity cameraActivity;

    public PictureTaker(SurfaceView surfaceView, CameraActivity cameraActivity)
    {
        this.surfaceView = surfaceView;
        this.cameraActivity = cameraActivity;
    }

    public void takePicture()
    {
        try
        {
            mCam = Camera.open();
        }
        catch (Exception e)
        {
            System.out.println("Problem opening camera! " + e);
            return;
        }

        if (mCam == null)
        {
            System.out.println("Camera is null!");
            return;
        }

        try
        {
            SurfaceView view = surfaceView; // my own fcn
            mCam.setPreviewDisplay(view.getHolder());
            mCam.startPreview();

            final PictureTaker mPicTaker = this;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCam.takePicture(null, null, mPicTaker);
                }
            }, 5000);
        }
        catch (Exception e)
        {
            System.out.println("Problem taking picture: " + e);
        }
    }

    public void onPictureTaken(byte[] data, Camera cam)
    {
        Log.d("POADSHGAKJDS", "sending to NN");
        sendToNeuralNetwork(data);
    }

    public void sendToNeuralNetwork(byte[] data) {
        Log.d("dlaksjgaò", "data received");
        mCam.startPreview();


        cameraActivity.findViewById(R.id.bubble1).setVisibility(View.VISIBLE);
        cameraActivity.findViewById(R.id.bubble2).setVisibility(View.VISIBLE);
        cameraActivity.findViewById(R.id.bubble3).setVisibility(View.VISIBLE);
        cameraActivity.findViewById(R.id.settings_button).setVisibility(View.GONE);
    }
}