import 'package:flutter/material.dart';
import 'training.dart';
 
class ListViewTrainings extends StatelessWidget {
  final List<Training> trainings;
 
  ListViewTrainings({Key key, this.trainings}) : super(key: key);
 
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
          itemCount: trainings.length,
          padding: const EdgeInsets.all(15.0),
          itemBuilder: (context, position) {
            return Column(
              children: <Widget>[
                Divider(height: 5.0),
                ListTile(
                  title: Text(
                    '${trainings[position].trainingname}',
                    style: TextStyle(
                      fontSize: 22.0,
                      color: Colors.deepOrangeAccent,
                    ),
                  ),
                  subtitle: Text(
                    '${trainings[position].trainingdesc}',
                    style: new TextStyle(
                      fontSize: 18.0,
                      fontStyle: FontStyle.italic,
                    ),
                  ),
                  leading: Column(
                    children: <Widget>[
                      CircleAvatar(
                        backgroundColor: Colors.blueAccent,
                        radius: 35.0,
                        child: Text(
                          "ID ${trainings[position].id}",
                          style: TextStyle(
                            fontSize: 22.0,
                            color: Colors.white,
                          ),
                        ),
                      )
                    ],
                  ),
                  onTap: () => _onTapItem(context, trainings[position]),
                ),
              ],
            );
          }),
    );
  }
 
  void _onTapItem(BuildContext context, Training training) {
    Scaffold
        .of(context)
        .showSnackBar(
          new SnackBar(content: new Text(training.id + ' - ' + training.trainingname))
          );
  }
}