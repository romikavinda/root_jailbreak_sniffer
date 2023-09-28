import 'package:flutter_test/flutter_test.dart';
import 'package:root_jailbreak_sniffer/rjsniffer.dart';
import 'package:root_jailbreak_sniffer/rjsniffer_platform_interface.dart';
import 'package:root_jailbreak_sniffer/rjsniffer_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockRjsnifferPlatform
    with MockPlatformInterfaceMixin
    implements RjsnifferPlatform {
  @override
  Future<bool?> amICompromised() {
    // TODO: implement amICompromised
    throw UnimplementedError();
  }

  @override
  Future<bool?> amIEmulator() {
    // TODO: implement amIEmulator
    throw UnimplementedError();
  }

  @override
  Future<bool?> amIDebugged() {
    // TODO: implement amIDebugged
    throw UnimplementedError();
  }
}

void main() {
  final RjsnifferPlatform initialPlatform = RjsnifferPlatform.instance;

  test('$MethodChannelRjsniffer is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelRjsniffer>());
  });

  test('getPlatformVersion', () async {
    //Rjsniffer rjsnifferPlugin = Rjsniffer();
    MockRjsnifferPlatform fakePlatform = MockRjsnifferPlatform();
    RjsnifferPlatform.instance = fakePlatform;

    expect(await Rjsniffer.amICompromised(), false);
  });
}
