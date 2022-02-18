import 'package:flutter/material.dart';
import 'package:consoliadsplugin/consoliadsplugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('ConsoliAds Plugin'),
        ),
        body: Center(
          child: Column(
            // Column is also a layout widget. It takes a list of children and
            // arranges them vertically. By default, it sizes itself to fit its
            // children horizontally, and tries to be as tall as its parent.
            //
            // Invoke "debug painting" (press "p" in the console, choose the
            // "Toggle Debug Paint" action from the Flutter Inspector in Android
            // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
            // to see the wireframe for each widget.
            //
            // Column has various properties to control how it sizes itself and
            // how it positions its children. Here we use mainAxisAlignment to
            // center the children vertically; the main axis here is the vertical
            // axis because Columns are vertical (the cross axis would be
            // horizontal).
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              ElevatedButton(
                child: Text('Int Consoliads'),
                onPressed: initConsoliAdsSdk,
              ),
              ElevatedButton(
                child: Text('Load Interstitial'),
                onPressed: loadInterstitial,
              ),
              ElevatedButton(
                child: Text('Show Interstitial'),
                onPressed: showInterstitial,
              ),
              ElevatedButton(
                child: Text('Load Rewarded'),
                onPressed: loadRewarded,
              ),
              ElevatedButton(
                child: Text('Show Rewarded'),
                onPressed: showRewarded,
              ),
            ],
          ),
        ),
      ),
    );
  }

  void initConsoliAdsSdk() {
    ConsoliAdsPlugin.initSDK(true, true, "567f2188d5d283f6fa4fccec99dc6677");
  }

  void loadInterstitial() {
    ConsoliAdsPlugin.loadInterstitial();
  }

  void showInterstitial() async {
    if (await ConsoliAdsPlugin.isInterstitialAvailable()) {
      ConsoliAdsPlugin.showInterstitial();
    }
  }

  void showRewarded() async {
    if (await ConsoliAdsPlugin.isRewardedAvailable()) {
      ConsoliAdsPlugin.showRewarded();
    }
  }

  void loadRewarded() async {
    ConsoliAdsPlugin.showRewarded();
  }
}
