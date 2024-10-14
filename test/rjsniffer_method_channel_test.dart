import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
//import 'package:root_jailbreak_sniffer/rjsniffer_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  //MethodChannelRjsniffer platform = MethodChannelRjsniffer();
  const MethodChannel channel = MethodChannel('tempnew');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
      channel,
          (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    //expect(await platform.getPlatformVersion(), '42');
  });
}
