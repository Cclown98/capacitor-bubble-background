package com.cclown98.bubblebackground;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;


@NativePlugin(name = "BubbleBackground", requestCodes = {BubbleBackgroundPlugin.SYSTEM_ALERT_WINDOW, BubbleBackgroundPlugin.REQUEST_OVERLAY_PERMISSION})
public class BubbleBackgroundPlugin extends Plugin {
    protected static final int SYSTEM_ALERT_WINDOW = 57451;
    protected static final int REQUEST_OVERLAY_PERMISSION = 57452;
    private Context context;
    private BubbleBackground implementation;
    private BubblesManager bubblesManager;


    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", 123);
        call.resolve(ret);
    }

    @PluginMethod
    public void changeToBubbleMode(PluginCall call) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this.getContext())) {

                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getContext().getPackageName()));
                startActivityForResult(call, intent, REQUEST_OVERLAY_PERMISSION);
            } else {
                Log.d("Overlay setting", Settings.canDrawOverlays(this.getContext()) + "");
                this.genBubble();
            }
        }
//  bubblesManager.initialize();
        saveCall(call);
    }


    @Override
    protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleRequestPermissionsResult(requestCode, permissions, grantResults);


        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            Log.d("Test", "No stored plugin call for permissions request result");
            return;
        }

        for (int result : grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Log.d("Test", "User denied permission");
                return;
            }

        }
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            Log.d("Test", "Overlay got permission");

        }


        if (requestCode == SYSTEM_ALERT_WINDOW) {
            // We got the permission!
            Log.d("Test", "we got permission");

            this.context = getContext();
            this.bubblesManager = new BubblesManager.Builder(context).build();
            this.bubblesManager.initialize();


            String value = savedCall.getString("value");

            JSObject ret = new JSObject();
            ret.put("value", "test");
            BubbleLayout bubbleView = (BubbleLayout) LayoutInflater
                    .from(getContext()).inflate(R.layout.bubble_layout, null);
            bubblesManager.addBubble(bubbleView, 60, 20);
            savedCall.resolve(ret);
        }
    }

    public void genBubble() {

        this.context = getContext();
        this.bubblesManager = new BubblesManager.Builder(context).build();
        this.bubblesManager.initialize();



        JSObject ret = new JSObject();
        ret.put("value", "test");
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater
                .from(getContext()).inflate(R.layout.bubble_layout, null);
        bubblesManager.addBubble(bubbleView, 60, 20);
    }

}
