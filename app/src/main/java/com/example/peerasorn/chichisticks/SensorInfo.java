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
                String sentiment = (checkSentiment == 1 || checkSentiment == 3 || checkSentiment == 5) ? "GOOD" :
                        (checkSentiment == 2 || checkSentiment == 6 || checkSentiment == 9) ? "BAD" : "NORMAL";

                final AlertDialog.Builder viewDialog = new AlertDialog.Builder(context);
                if (sentiment.equalsIgnoreCase("GOOD")) {
                    viewDialog.setIcon(R.drawable.stat_happy);
                } else if (sentiment.equalsIgnoreCase("BAD")) {
                    viewDialog.setIcon(R.drawable.stat_sad);
                } else {
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
