import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:consoliadsplugin/consoliadsplugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('consoliadsplugin');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ConsoliAdsPlugin.platformVersion, '42');
  });
}
