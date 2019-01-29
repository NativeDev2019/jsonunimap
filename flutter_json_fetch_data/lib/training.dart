class Training {
  final String id;
  final String trainingname;
  final String website;
  final String trainingdesc;
  //this.website,
  //website: json['website'] as String,

  Training({this.id, this.trainingname, this.website, this.trainingdesc});

  factory Training.fromJson(Map<String, dynamic> json) {
    return Training(
      id: json['id'] as String,
      trainingname: json['trainingname'] as String,
      website: json['website'] as String,
      trainingdesc: json['trainingdesc'] as String,
    );
  }
}