import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:no_phone/data/native_service_bridge.dart';


class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: .fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final NativeServiceBridge _nativeService = NativeServiceBridge();
  String message = "Connection not tested";

  Future<void> testNativeConnection() async {
    try {
      final String response = await _nativeService.testConnection();
      
      if (!mounted) return;

      setState(() {
        message = response;
      });

    } on PlatformException catch (error) {
      if (!mounted) return;

      setState(() {
        message = "platform error: ${error.message ?? error.code}";
      });
    }

  }

  @override
  void initState() {
    super.initState();
  }


  @override
  void dispose() {
    super.dispose();
  }


  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: .center,
          children: [
            Text(message),
            ElevatedButton(
              onPressed: testNativeConnection,
              child: Text('Test connection')
            ),
          
          ],
        ),
      ),

    );
  }
}
