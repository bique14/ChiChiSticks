package com.example.peerasorn.chichisticks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SensorInfo {
    private float x = 0;
    private float y = 0;
    private float z = 0;
    private int shake_threshold = 10;
    private int index;
    private boolean showDialog;
    Sticks stick = new Sticks();

    public void setSensor(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void showDialog(Context context) {
        if((Math.abs(x) > shake_threshold) || (Math.abs(y) > shake_threshold) || (Math.abs(z) > shake_threshold)) {
            if(!showDialog) {
                showDialog = true;
                index = (int)(Math.random() * (stick.sticks.length - 1));
                int checkSentiment = index + 1;

                final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
                if (checkSentiment == 1 || checkSentiment == 3 || checkSentiment == 5) { // Good
                    viewDialog.setIcon(R.drawable.stat_happy);
                } else if (checkSentiment == 2 || checkSentiment == 6 || checkSentiment == 9) { // Bad
                    viewDialog.setIcon(R.drawable.stat_sad);
                } else { // Normal (4,7,8)
                    viewDialog.setIcon(R.drawable.stat_neutral);
                }
                viewDialog.setTitle("ใบที่ " + String.valueOf(index + 1));
                viewDialog.setMessage(stick.getStick(index));
                viewDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDialog = false;

                    }
                });
                viewDialog.show();
            }
        }
    }
}
