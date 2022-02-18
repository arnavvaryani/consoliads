
import 'dart:async';

import 'package:flutter/services.dart';

class ConsoliAdsPlugin {
  static const MethodChannel _channel = MethodChannel('consoliadsplugin');

  static void initSDK(bool devMode , bool userConsent , String userSignature){
    _channel.invokeMethod('init' , [devMode,userConsent,userSignature ]);
    //_channel.invokeMethod('init' , [{"devMode":devMode} , {"userConsent":userConsent}  , {"userSignature":userSignature} ]);
  }

  static void loadInterstitial(){
    _channel.invokeMethod('loadInterstitial');
  }

  static void showInterstitial(){
    _channel.invokeMethod('showInterstitial');
  }

  static void loadRewarded(){
    _channel.invokeMethod('loadRewarded');
  }

  static void showRewarded(){
    _channel.invokeMethod('showRewarded');
  }
  static void hideAllAds(){
    _channel.invokeMethod('hideAllAds');
  }

  static Future<bool> isInterstitialAvailable() async {
   return await _channel.invokeMethod('isInterstitialAvailable');
  }

   static Future<bool> isRewardedAvailable() async {
   return await _channel.invokeMethod('isRewardedAvailable');
  }

}
