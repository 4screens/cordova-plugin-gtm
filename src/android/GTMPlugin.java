package net.nopattern.cordova.gtm;

import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.TagManager;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.ContainerHolder;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.*;
import java.lang.Boolean;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.util.concurrent.TimeUnit;

import net.nopattern.cordova.gtm.GTMPluginConstant;

public class GTMPlugin extends CordovaPlugin {

  private String containerId;
  private static Container container;

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    try {
      // get from preferences (config.xml)
      this.containerId = preferences.getString(GTMPluginConstant.CONTAINER_ID_PREFERENCE, "");

      if (!containerId.isEmpty()) {
        Log.i(GTMPluginConstant.LOG_TAG, "initialize with containerId: " + this.containerId);

        TagManager tagManager = TagManager.getInstance(cordova.getActivity());

        PendingResult<ContainerHolder> pending = tagManager.loadContainerPreferNonDefault(
          this.containerId,
          -1
        );

        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
          @Override
          public void onResult(ContainerHolder containerHolder) {
            if (!containerHolder.getStatus().isSuccess()) {
              return;
            }

            container = containerHolder.getContainer();
          }
        }, 2, TimeUnit.SECONDS);

      }
    } catch(Exception e) {
      Log.e(GTMPluginConstant.LOG_TAG, "Exception: " + e.getMessage());
    }
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    try {
      if (action.equals("getContainerId")) {
        this.getContainerId(callbackContext);
        return true;
      } else if(action.equals("getBoolean")) {
        this.getBoolean(args.getString(0), callbackContext);
        return true;
      } else if(action.equals("getDouble")) {
        this.getDouble(args.getString(0), callbackContext);
        return true;
      } else if(action.equals("getLong")) {
        this.getLong(args.getString(0), callbackContext);
        return true;
      } else if (action.equals("getString")) {
        this.getString(args.getString(0), callbackContext);
        return true;
      }
      return false;
    } catch(Exception e) {
      callbackContext.error(e.getMessage());
      return false;
    }
  }

  private void getContainerId(CallbackContext callbackContext) {
    String containerId = container.getContainerId();

    callbackContext.success(containerId);
  }

  private void getBoolean(String key, CallbackContext callbackContext) {
    Boolean value = container.getBoolean(key);

    if (key != null && key.length() > 0) {
      callbackContext.success(Boolean.toString(value));
    } else {
      callbackContext.error("Expected one non-empty string argument.");
    }
  }

  private void getDouble(String key, CallbackContext callbackContext) {
    Double value = container.getDouble(key);

    if (key != null && key.length() > 0) {
      callbackContext.success(Double.toString(value));
    } else {
      callbackContext.error("Expected one non-empty string argument.");
    }
  }

  private void getLong(String key, CallbackContext callbackContext) {
    Long value = container.getLong(key);

    if (key != null && key.length() > 0) {
      callbackContext.success(Long.toString(value));
    } else {
      callbackContext.error("Expected one non-empty string argument.");
    }
  }

  private void getString(String key, CallbackContext callbackContext) {
    String value = container.getString(key);

    if (key != null && key.length() > 0) {
      callbackContext.success(value);
    } else {
      callbackContext.error("Expected one non-empty string argument.");
    }
  }

}
