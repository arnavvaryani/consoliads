package com.consoliads.consoliadsplugin;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.consoliads.mediation.ConsoliAds;
import com.consoliads.mediation.constants.PlaceholderName;

import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

import com.consoliads.mediation.listeners.ConsoliAdsInterstitialListener;
import com.consoliads.mediation.listeners.ConsoliAdsListener;
import com.consoliads.mediation.listeners.ConsoliAdsRewardedListener;

/** ConsoliadspluginPlugin */
public class ConsoliadspluginPlugin implements FlutterPlugin, MethodCallHandler , ActivityAware {

  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  public static Activity activity;

  String TAG = "ConsoliAdsListeners";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "consoliadsplugin");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("init")) {
      List<Object> arguments = call.arguments();
      boolean devMode = (boolean) arguments.get(0);
      boolean userConsent =  (boolean) arguments.get(1);
      String userSignature =  (String) arguments.get(2);
      ConsoliAds.Instance().initialize(devMode, userConsent ,activity , userSignature);
      ConsoliAds.Instance().setConsoliAdsListener(new ConsoliAdsListener() {
        @Override
        public void onConsoliAdsInitializationSuccess() {
            Log.i(TAG, "onIconAdShownEvent");
        }
    });
    }
    else if (call.method.equals("loadInterstitial")) {
    //   ConsoliAds.Instance().setConsoliAdsInterstitialListener(new ConsoliAdsInterstitialListener() {
    //     @Override
    //     public void onInterstitialAdShownEvent(PlaceholderName placeholderName) {
    //         Log.i(TAG, "onInterstitialAdShownEvent");
    //     }
    //     @Override
    //     public void onInterstitialAdClickedEvent() {
    //         Log.i(TAG, "onInterstitialAdClickedEvent");
    //     }
    //     @Override
    //     public void onInterstitialAdClosedEvent() {
    //         Log.i(TAG, "onInterstitialAdClosedEvent");
    //     }
    //     @Override
    //     public void onInterstitialAdFailedToShowEvent(PlaceholderName placeholderName) {
    //         Log.i(TAG, "onInterstitialAdFailedToShowEvent");
    //     }
    //     @Override
    //     public void onInterstitialAdFailToLoadEvent() {
    //         Log.i(TAG, "onInterstitialAdFailToLoadEvent");
    //     }
    //     @Override
    //     public void onInterstitialAdLoadedEvent() {
    //         Log.i(TAG, "onInterstitialAdLoadedEvent");
    //     }
    // });
    ConsoliAds.Instance().LoadInterstitial();
    }
    else if (call.method.equals("showInterstitial")) {
      ConsoliAds.Instance().ShowInterstitial(activity);
    } else if (call.method.equals("isInterstitialAvailable")) {
      result.success(ConsoliAds.Instance().IsInterstitialAvailable());
    } else if(call.method.equals("loadRewarded")) {
    //   ConsoliAds.Instance().setConsoliAdsRewardedListener(new ConsoliAdsRewardedListener() {
    //     @Override
    //     public void onRewardedVideoAdLoadedEvent() {
    //         Log.i(TAG, "onRewardedVideoAdLoadedEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdShownEvent() {
    //         Log.i(TAG, "onRewardedVideoAdShownEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdCompletedEvent() {
    //         Log.i(TAG, "onRewardedVideoAdCompletedEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdClickEvent() {
    //         Log.i(TAG, "onRewardedVideoAdClickEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdFailToLoadEvent() {
    //         Log.i(TAG, "onRewardedVideoAdFailToLoadEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdFailToShowEvent() {
    //         Log.i(TAG, "onRewardedVideoAdFailToShowEvent");
    //     }
    //     @Override
    //     public void onRewardedVideoAdClosedEvent() {
    //         Log.i(TAG, "onRewardedVideoAdClosedEvent");
    //     }
    // });
      ConsoliAds.Instance().LoadRewarded();
    }  else if (call.method.equals("showRewarded")) {
      ConsoliAds.Instance().ShowRewardedVideo(activity);
    } else if (call.method.equals("isRewardedAvailable")) {
      result.success(ConsoliAds.Instance().IsRewardedVideoAvailable());
    } else if (call.method.equals("hideAllAds")) {
      ConsoliAds.Instance().hideAllAds();
    }
    else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    this.activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
