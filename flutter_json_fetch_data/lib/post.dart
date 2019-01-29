class Post {
  final String id;
  final String trainingname;
  //final String website;
  final String trainingdesc;
  //this.website,
  //website: json['website'] as String,

  Post({this.id, this.trainingname,  this.trainingdesc});

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      id: json['id'] as String,
      trainingname: json['trainingname'] as String,
      trainingdesc: json['trainingdesc'] as String,
    );
  }
}