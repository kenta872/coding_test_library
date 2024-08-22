# ベースイメージとしてOpenJDKを使用
FROM eclipse-temurin:17-jdk-alpine

# 作業ディレクトリを設定
WORKDIR /app

# ビルドされたJARファイルをコピー
COPY build/libs/*.jar app.jar

# アプリケーションを起動する
ENTRYPOINT ["java", "-jar", "/app/app.jar"]