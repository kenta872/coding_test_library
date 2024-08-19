# ベースイメージとしてOpenJDKを使用
FROM eclipse-temurin:17-jdk-alpine

# 作業ディレクトリを設定
WORKDIR /app

# ビルドされたJARファイルをコピー
COPY build/libs/library-0.0.1-SNAPSHOT.jar app.jar

# アプリケーションを起動する
ENTRYPOINT ["java", "-jar", "/app/app.jar"]