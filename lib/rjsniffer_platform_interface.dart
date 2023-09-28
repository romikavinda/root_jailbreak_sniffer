import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'rjsniffer_method_channel.dart';

abstract class RjsnifferPlatform extends PlatformInterface {
  /// Constructs a RjsnifferPlatform.
  RjsnifferPlatform() : super(token: _token);

  static final Object _token = Object();

  static RjsnifferPlatform _instance = MethodChannelRjsniffer();

  /// The default instance of [RjsnifferPlatform] to use.
  ///
  /// Defaults to [MethodChannelRjsniffer].
  static RjsnifferPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [RjsnifferPlatform] when
  /// they register themselves.
  static set instance(RjsnifferPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<bool?> amICompromised() {
    throw UnimplementedError('amICompromised() has not been implemented.');
  }

  Future<bool?> amIEmulator() {
    throw UnimplementedError('amIEmulator() has not been implemented.');
  }

  Future<bool?> amIDebugged() {
    throw UnimplementedError('amIDebugged() has not been implemented.');
  }
}
