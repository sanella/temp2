package models;


import java.util.List;

import javax.persistence.*;

import play.Logger;
import play.data.validation.Constraints.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;


@Entity
public class Post extends Model {
	
		@Id
		public long id;
		
		@MinLength(1)
		@MaxLength(144)
		public String content;
		
		@ManyToOne
		public User author;
		
		public Post(String content, User author){
			this.content = content;
			this.author = author;
		}
		
		public static void create(String content, User author){
			new Post(content, author).save();
		}
		
		static Finder<Long, Post> find = new Finder<Long, Post>(Long.class,
				Post.class);
		
		public static Post find(long id){
			return find.byId(id);
		}
		
		public static void delete(long id){
			Post p = Post.find.byId(id);
			p.delete();
			Logger.debug("" + p.id);
		}
		
		public static List<Post> findAll(){
			List<Post> allPosts = find.all(); 
			return allPosts;
		}

}
