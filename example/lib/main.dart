import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:root_jailbreak_sniffer/rjsniffer.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> with WidgetsBindingObserver {
  bool _amICompromised = false;
  bool _amIEmulator = false;
  bool _amIDebugged = false;

  @override
  void initState() {
    super.initState();
    initPlatformState();
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    switch (state) {
      case AppLifecycleState.resumed:
        initPlatformState();
        break;
      case AppLifecycleState.paused:
        break;
      case AppLifecycleState.inactive:
        break;
      case AppLifecycleState.detached:
        break;
      case AppLifecycleState.hidden:
        break;
    }
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    bool amICompromised = false;
    bool amIEmulator = false;
    bool amIDebugged = false;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      amICompromised = await Rjsniffer.amICompromised() ?? false;
      amIEmulator = await Rjsniffer.amIEmulator() ?? false;
      amIDebugged = await Rjsniffer.amIDebugged() ?? false;
    } on PlatformException {
      //platform call failed
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _amICompromised = amICompromised;
      _amIEmulator = amIEmulator;
      _amIDebugged = amIDebugged;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(children: [
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                const SizedBox(
                  height: 150,
                ),
                Text('Am I Compromised: $_amICompromised\n'),
                Text('Am I Emulator: $_amIEmulator\n'),
                Text('Am I Debugged: $_amIDebugged\n'),
              ],
            ),
          )
        ]),
      ),
    );
  }
}
