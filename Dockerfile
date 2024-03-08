# Sử dụng một hình ảnh JDK để xây dựng ứng dụng Java của bạn
FROM openjdk:14

# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Cài đặt SDKMAN! và Maven
RUN apt-get update && apt-get install -y curl zip unzip && \
    curl -s "https://get.sdkman.io" | bash && \
    source "$HOME/.sdkman/bin/sdkman-init.sh" && \
    sdk install maven && \
    rm -rf /var/lib/apt/lists/*

# Biên dịch ứng dụng của bạn (sử dụng Maven)
RUN mvn -f /usr/src/demo/pom.xml clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "/usr/src/demo/target/demo.jar"]
