package com.Domain.ShareSys;

public class Comment {
    private String comment_id;
    private String comment_content;
    private String comment_pic;
    private String  comment_owner;
    public Comment(String comment_id,String comment_content,String comment_pic,String comment_owner) {
        this.comment_id=comment_id;
        this.comment_content=comment_content;
        this.comment_pic=comment_pic;
        this.comment_owner=comment_owner;
    }

    public Comment(){}

    public String getID(){return comment_id;}
    public String getContent(){return comment_content;}
    public String getPic(){return comment_pic;}
    public String getOwner(){return comment_owner;}

}
