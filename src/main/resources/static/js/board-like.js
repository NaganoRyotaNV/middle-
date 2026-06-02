"use strict";

$(() => {
  $(".like-button").on("click", (event) => {
    const button = $(event.currentTarget);
    const articleId = button.data("article-id");
    const count = $(".like-count[data-article-id='" + articleId + "']");

    // 課題5：投稿ごとのいいね数を非同期で増やす
    $.ajax({
      url: button.data("url"),
      type: "POST",
      dataType: "json",
      data: {
        articleId: articleId
      },
      async: true
    }).done((data) => {
      count.text(data.likeCount);
    }).fail((XMLHttpRequest, textStatus, errorThrown) => {
      console.log("XMLHttpRequest : " + XMLHttpRequest.status);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + (errorThrown && errorThrown.message ? errorThrown.message : errorThrown));
    });
  });
});
