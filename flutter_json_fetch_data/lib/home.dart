import 'package:flutter/material.dart';
import 'dart:async';
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;
import 'training.dart';
import 'listtrainings.dart';
 
Future<List<Training>> fetchTrainings(http.Client client) async {
  //fetch JSON data from server
  final response = await client.get('http://khirulnizam.com/training/searchflutter.php');
  //
  return compute(parseTrainings, response.body);//capture data body
}
 
List<Training> parseTrainings(String responseBody) {
  final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();
  //return data in linked list
  return parsed.map<Training>((json) => Training.fromJson(json)).toList();
}
 
class HomePage extends StatelessWidget {
  final String title;
 
  HomePage({Key key, this.title}) : super(key: key);
 
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),

      body: FutureBuilder<List<Training>>(
        future: fetchTrainings(http.Client()),
        builder: (context, snapshot) {
          if (snapshot.hasError) print(snapshot.error);
 
          return snapshot.hasData
              ? ListViewTrainings(trainings: snapshot.data)
              : Center(child: CircularProgressIndicator());
        },
      ),
    );
  }
}