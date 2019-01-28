import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http; //have to add in PubSpec.yaml

Future<Post> fetchPost() async {
  final response =
      await http.get('http://khirulnizam.com/training/listing.php');

  if (response.statusCode == 200) {
    // If the call to the server was successful, parse the JSON
    return Post.fromJson(json.decode(response.body));
  } else {
    // If that call was not successful, throw an error.
    throw Exception('Failed to load post');
  }
}

//JSON Object class
class Post {
  final String id;
  final String trainingname;
  final String website;
  final String desc;

  Post({this.id, this.trainingname, this.website, this.desc});

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      id: json['id'],
      trainingname: json['trainingname'],
      website: json['website'],
      desc: json['desc'],
    );
  }
}

void main() => runApp(MyApp(post: fetchPost()));

class MyApp extends StatelessWidget {
  //variables declare
  final Future<Post> post;
  MyApp({Key key, this.post}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'JSON Fetch Data Example',
      theme: ThemeData(
        primarySwatch: Colors.purple,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('Fetch Data Example'),
        ),
        body: Center(
          child: FutureBuilder<Post>(
            future: post,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Text(snapshot.data.trainingname);
              } else if (snapshot.hasError) {
                return Text("${snapshot.error}");
              }

              // By default, show a loading spinner
              return CircularProgressIndicator();
            },
          ),
        ),
      ),
    );
  }
}