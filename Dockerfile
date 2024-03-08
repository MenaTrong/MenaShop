# Sử dụng một hình ảnh Docker chứa Maven
FROM maven:3.8.4-openjdk-14

# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Biên dịch ứng dụng của bạn (sử dụng Maven)
RUN mvn -f /usr/src/demo/pom.xml clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "/usr/src/demo/target/demo.jar"]
