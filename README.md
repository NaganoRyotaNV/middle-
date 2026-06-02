# middle-

JavaScript / jQuery / Ajax 課題を実装した Spring Boot プロジェクトです。

## 実行方法

1. PostgreSQL を起動し、`application.yaml` の設定に合わせて DB を用意します。

   - DB 名：`test2`
   - ポート：`5433`
   - ユーザー：`postgres`
   - パスワード：`postgres123`

2. 掲示板を確認する場合は、`src/main/resources/sql/board.sql` を DB に流してテーブルと初期データを作成します。

3. プロジェクト直下で以下を実行します。

   ```bash
   ./gradlew bootRun
   ```

   Windows の PowerShell では以下でも実行できます。

   ```powershell
   .\gradlew.bat bootRun
   ```

4. ブラウザで以下を開きます。

   - Ajax 課題1〜4：`http://localhost:8080/ex-intermediate/ajax`
   - Ajax 課題5：`http://localhost:8080/ex-intermediate/board`

## 課題1の確認方法：郵便番号から住所を取得する

1. `http://localhost:8080/ex-intermediate/ajax` を開きます。
2. 「課題1：郵便番号から住所を取得する」の郵便番号欄に `2208111` を入力します。
3. 「住所取得」ボタンを押します。
4. 以下のように住所が3項目に分かれて表示されることを確認します。

   - 都道府県：`神奈川県`
   - 市区町村：`横浜市西区`
   - それ以降の住所：`みなとみらいランドマークタワー１１階`

## 課題2の確認方法：パスワードチェック

1. `http://localhost:8080/ex-intermediate/ajax` を開きます。
2. Password 欄に `aaaaaa` を入力します。
3. 「パスワードは8文字以上で入力してください」と表示されることを確認します。
4. Password 欄に `aaaaaaaa` を入力します。
5. Confirmation Password 欄に `bbbbbbbb` を入力します。
6. 「パスワード入力OK!」「パスワードが一致していません」と表示されることを確認します。
7. Confirmation Password 欄に `aaaaaaaa` を入力します。
8. 「パスワード入力OK!」「確認用パスワード入力OK!」と表示されることを確認します。

## 課題3の確認方法：ステータス変更

1. `http://localhost:8080/ex-intermediate/ajax` を開きます。
2. 「課題3：ステータスを非同期で変更する」で現在のステータスが「入金前」と表示されていることを確認します。
3. 「入金済へ変更」ボタンを押します。
4. ステータスが「入金済」、ボタンが「配送済へ変更」に変わることを確認します。
5. さらに押すたびに「配送済」「完了」「入金前」の順で切り替わることを確認します。

## 課題4の確認方法：従業員一覧取得

1. `http://localhost:8080/ex-intermediate/ajax` を開きます。
2. 「課題4：従業員一覧取得」の「従業員一覧取得」ボタンを押します。
3. 従業員数と、ID / 画像 / 名前 / 入社日の表が表示されることを確認します。
4. 画像が `http://153.127.48.168:8080/ex-emp-api/img/` 配下から表示されることを確認します。

## 課題5の確認方法：掲示板のいいね機能

1. `src/main/resources/sql/board.sql` を DB に流して掲示板用テーブルを作成します。
2. `http://localhost:8080/ex-intermediate/board` を開きます。
3. 各投稿の「いいね」横に `0` が表示されていることを確認します。
4. 「いいね」ボタンを押します。
5. 画面遷移せず、押した投稿の数字だけが `1`, `2`, `3` と増えることを確認します。
6. アプリを再起動すると、いいね数は `0` から始まります。

## 実装内容

- 課題1〜4の画面：`src/main/resources/templates/ajax/index.html`
- 課題1〜4の JavaScript：`src/main/resources/static/js/ajax-exercises.js`
- 課題1〜4の CSS：`src/main/resources/static/css/ajax-exercises.css`
- Ajax 課題画面 Controller：`src/main/java/com/example/middle/controller/AjaxExerciseController.java`
- 課題5の JavaScript：`src/main/resources/static/js/board-like.js`
- 課題5の Controller 処理：`src/main/java/com/example/middle/controller/BoardController.java`
