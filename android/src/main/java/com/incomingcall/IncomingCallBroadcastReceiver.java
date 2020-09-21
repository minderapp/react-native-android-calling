package com.incomingcall;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

public class IncomingCallBroadcastReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Application applicationContext = (Application) context.getApplicationContext();

    IncomingCall incomingCall = new IncomingCall(context);
    Integer id = intent.getIntExtra("id", 0);
    String uuid = intent.getStringExtra("uuid");
    Log.d("BroadcastReceiver", intent.getAction());
    Log.d("BroadcastReceiver", context.getPackageName());

    switch (intent.getAction()){
      case "dismiss":
        incomingCall.clearNotification(id);
        break;
      case "answer":
        Intent startIntent = context
          .getPackageManager()
          .getLaunchIntentForPackage(context.getPackageName());
        startIntent.putExtras(intent);
        startIntent.setAction(intent.getAction());
        context.startActivity(startIntent);
        incomingCall.clearNotification(id);
        break;
      case "timeout":
        // rnVoipNotificationHelper.showMissCallNotification(intent.getStringExtra("missedCallTitle"), intent.getStringExtra("missedCallBody"), intent.getStringExtra("callerId"));
        break;
      default:
        break;
    }

  }
}