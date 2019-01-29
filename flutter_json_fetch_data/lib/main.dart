import 'package:flutter/material.dart';
import 'home.dart';
 
void main() => runApp(MyApp());
 
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final appTitle = 'Flutter Training HTTP-JSON';
 
    return MaterialApp(
      title: appTitle,
      home: HomePage(title: appTitle),
    );
  }
}