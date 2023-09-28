import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'rjsniffer_platform_interface.dart';

/// An implementation of [RjsnifferPlatform] that uses method channels.
class MethodChannelRjsniffer extends RjsnifferPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('com.emrys.rjsniffer/epic');

  @override
  Future<bool?> amICompromised() async {
    final compromised = await methodChannel.invokeMethod<bool>('runprog');
    return compromised;
  }

  @override
  Future<bool?> amIEmulator() async {
    final emulator = await methodChannel.invokeMethod<bool>('runprog2');
    return emulator;
  }

  @override
  Future<bool?> amIDebugged() async {
    final debugged = await methodChannel.invokeMethod<bool>('runprog3');
    return debugged;
  }
}
