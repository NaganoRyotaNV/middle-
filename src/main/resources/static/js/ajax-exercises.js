"use strict";

$(() => {
  const statusNames = ["入金前", "入金済", "配送済", "完了"];
  let currentStatusValue = 0;

  const showAjaxError = (XMLHttpRequest, textStatus, errorThrown) => {
    console.log("XMLHttpRequest : " + XMLHttpRequest.status);
    console.log("textStatus : " + textStatus);
    console.log("errorThrown : " + (errorThrown && errorThrown.message ? errorThrown.message : errorThrown));
  };

  const splitAddress = (item) => {
    const prefecture = item.pref || item.prefecture || "";
    const city = item.city || "";
    const address = item.address || "";
    const street = address.replace(prefecture, "").replace(city, "");

    return {
      prefecture: prefecture,
      city: city,
      street: street
    };
  };

  // 課題1：郵便番号から住所を取得する
  $("#get_address_btn").on("click", () => {
    $.ajax({
      url: "https://zipcoda.net/api",
      type: "GET",
      dataType: "json",
      data: {
        zipcode: $("#zipcode").val()
      },
      async: true
    }).done((data) => {
      console.log(data);

      if (!data.items || data.items.length === 0) {
        alert("住所が見つかりませんでした。");
        return;
      }

      const address = splitAddress(data.items[0]);
      $("#prefecture").val(address.prefecture);
      $("#city").val(address.city);
      $("#street").val(address.street);
    }).fail((XMLHttpRequest, textStatus, errorThrown) => {
      alert("正しい結果を得られませんでした。");
      showAjaxError(XMLHttpRequest, textStatus, errorThrown);
    });
  });

  const checkPassword = () => {
    $.ajax({
      url: "http://153.127.48.168:8080/ex-js-api/checkpassword/check",
      type: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify({
        password: $("#password").val(),
        confirmationPassword: $("#confirmation_password").val()
      }),
      async: true
    }).done((data) => {
      $("#robustness_message").text(data.robustnessMessage);
      $("#disagreement_message").text(data.disagreementMessage);
    }).fail((XMLHttpRequest, textStatus, errorThrown) => {
      showAjaxError(XMLHttpRequest, textStatus, errorThrown);
    });
  };

  // 課題2：入力するたびに非同期でパスワードをチェックする
  $("#password, #confirmation_password").on("input", checkPassword);

  const renderStatus = () => {
    const nextStatusValue = (currentStatusValue + 1) % statusNames.length;
    $("#status_text").text(statusNames[currentStatusValue]);
    $("#update_status_btn").text(statusNames[nextStatusValue] + "へ変更");
  };

  // 課題3：現在のステータス番号を送信して次のステータスへ変更する
  $("#update_status_btn").on("click", () => {
    $.ajax({
      url: "http://153.127.48.168:8080/ex-js-api/updatestatus/update",
      type: "POST",
      dataType: "json",
      data: {
        previousStatusValue: currentStatusValue
      },
      async: true
    }).done((data) => {
      currentStatusValue = Number(data.statusValue ?? data.currentStatusValue ?? data.nextStatusValue ?? ((currentStatusValue + 1) % statusNames.length));
      renderStatus();
    }).fail((XMLHttpRequest, textStatus, errorThrown) => {
      showAjaxError(XMLHttpRequest, textStatus, errorThrown);
    });
  });

  // 課題4：従業員一覧を取得して表に表示する
  $("#get_employees_btn").on("click", () => {
    $.ajax({
      url: "http://153.127.48.168:8080/ex-emp-api/employee/employees",
      type: "GET",
      dataType: "json",
      async: true
    }).done((data) => {
      const employees = data.data.employees;
      $("#employee_count").text("従業員数：" + data.data.totalEmployeeCount + "名");
      $("#employee_table_body").empty();

      employees.forEach((employee) => {
        const imageUrl = "http://153.127.48.168:8080/ex-emp-api/img/" + employee.image;
        const row = $("<tr>");

        row.append($("<td>").text(employee.id));
        row.append($("<td>").append($("<img>").attr("src", imageUrl).attr("alt", employee.name).addClass("employee-image")));
        row.append($("<td>").text(employee.name));
        row.append($("<td>").text(employee.hireDate));
        $("#employee_table_body").append(row);
      });
    }).fail((XMLHttpRequest, textStatus, errorThrown) => {
      showAjaxError(XMLHttpRequest, textStatus, errorThrown);
    });
  });
});
