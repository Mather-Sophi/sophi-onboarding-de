FROM hseeberger/scala-sbt

RUN sbt test dist

EXPOSE 9000

COPY ./target/universal/$APP_NAME-$APP_VERSION.zip .

RUN unzip $APP_NAME-$APP_VERSION.zip

WORKDIR $APP_NAME-$APP_VERSION

ENTRYPOINT $APP_NAME-$APP_VERSION/bin/$APP_NAME

