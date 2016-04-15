package com.gruuf.model

import net.liftweb.http.js.JsCmd
import org.joda.time.DateTime

object Forum {

  private var comments: Seq[Comment] = Seq()

  def allComments: Seq[Comment] = {
    comments
  }

  def postComment(comment: String): Comment = {
    val newComment = Comment(DateTime.now, comment)

    comments = newComment +: comments

    newComment
  }

}
