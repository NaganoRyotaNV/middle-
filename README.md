# board-springboot

掲示板サイトだけを切り出した Spring Boot プロジェクトです。

元リポジトリに含まれていた Ajax 課題、服検索、ホテル、野球チーム、スライド、スニペット類は含めていません。

## 含まれている機能

- 通常版掲示板: `/board`
- 中級版掲示板: `/board/intermediate`
- 上級 JPA 版掲示板: `/board/advanced-jpa`
- 記事投稿
- コメント投稿
- 記事削除
- 通常版掲示板の Ajax いいね機能

## 実行前の準備

PostgreSQL を起動し、`src/main/resources/application.yaml` の設定に合わせて DB を用意してください。

- DB 名: `test2`
- ポート: `5433`
- ユーザー: `postgres`
- パスワード: `postgres123`

掲示板用テーブルと初期データは以下の SQL を流して作成します。

```text
src/main/resources/sql/board.sql
```

## 起動方法

Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

macOS / Linux:

```bash
./gradlew bootRun
```

起動後、ブラウザで以下を開きます。

```text
http://localhost:8080/board
http://localhost:8080/board/intermediate
http://localhost:8080/board/advanced-jpa
```

## 主なファイル

- `src/main/java/com/example/middle/controller/BoardController.java`
- `src/main/java/com/example/middle/controller/BoardIntermediateController.java`
- `src/main/java/com/example/middle/controller/BoardAdvancedJpaController.java`
- `src/main/resources/templates/board/`
- `src/main/resources/static/js/board-like.js`
- `src/main/resources/sql/board.sql`
