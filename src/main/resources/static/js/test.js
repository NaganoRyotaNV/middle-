"use strict";

// 演習１
$("#q1_btn").on("click", () => {
  $("#q1").text("山田太郎");
});

// 演習２
$("#q2").css("color", "blue"); // 初期表示時は青
$("#q2_btn").on("click", () => {
  let targetText = $("#q2").css("color");
  console.log(targetText);
  if (targetText === "rgb(0, 0, 255)") {
    // 青なら赤へ、赤なら青へ
    $("#q2").css("color", "red");
  } else {
    $("#q2").css("color", "blue");
  }
});

// 演習３
$("#triathlon").hide(); // hide()関数でも非表示になる
$("#other").hide();

$("#q3_select").on("change", () => {
  // 選択肢変更時
  if ($("#q3_select").val() === "1") {
    $("#triathlon").show();
    $("#other").hide();
  } else if ($("#q3_select").val() === "2") {
    $("#triathlon").hide();
    $("#other").show();
  } else {
    $("#triathlon").hide();
    $("#other").hide();
  }
});

// 演習４
$("#q4_btn").on("click", () => {
  // ボタン押下時
  $("#q4_btn").prop("disabled", true);
});

// 演習５
$("#q5_btn").on("click", () => {
  // ボタン押下時
  let nameValue = $("#email").val().trim();
  if (nameValue === "") {
    alert("メールアドレスを入力してください！");
  } else {
    // 入力されていたらq5_formというidがつけられたフォームをサブミットする(サーバーへ送る)
    // 今回はaction属性が「#」なので同じ画面が表示される
    $("#q5_form").submit();
  }
});
