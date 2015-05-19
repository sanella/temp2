package controllers;

import java.util.List;

import models.*;
import play.mvc.Controller;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;


public class PostController extends Controller{

	static Form<Post> postForm = new Form<Post>(Post.class);

	
	public static Result create(){
				Form<Post> postForm = new Form<Post>(Post.class).bindFromRequest();
				if( postForm.hasErrors() )
					return ok(showUser.render(Session.getCurrentUser(ctx()), postForm));
				Post newPost = postForm.get();
				Post.create(newPost.content, Session.getCurrentUser(ctx()));
				return redirect("/user/"+Session.getCurrentUser(ctx()).id);
			}
	
	
	public static Result delete(long id){
		User user = Session.getCurrentUser(ctx());
		Logger.debug("" + user.email);
		Post p = Post.find(id);
		if(user.id == p.author.id){
		Post.delete(id);
		Logger.debug("proslo" );
		return redirect("/");
		} else {
			Logger.debug("Nije proslo");

		return redirect("/");	

		}
	}
	
	public static Result allPosts(){
	List<Post> posts = Post.findAll();
	Logger.debug("Proslo conroller");
	return ok(allPosts.render(posts));
	}
	 
}
