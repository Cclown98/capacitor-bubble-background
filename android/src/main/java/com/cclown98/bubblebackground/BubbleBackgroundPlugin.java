package com.cclown98.bubblebackground;

import android.view.LayoutInflater;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;


@NativePlugin(name = "BubbleBackground")
public class BubbleBackgroundPlugin extends Plugin {
        private BubbleBackground implementation = new BubbleBackground();
    private BubblesManager bubblesManager = new BubblesManager.Builder(this.getContext()).build();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void changeToBubbleMode(PluginCall call) {
        bubblesManager.initialize();
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater
                                            .from(this.getContext()).inflate(R.layout.bubble_layout, null);
        bubblesManager.addBubble(bubbleView, 60, 20);
        call.resolve(ret);
    }
}
