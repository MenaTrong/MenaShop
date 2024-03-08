# Sử dụng hình ảnh OpenJDK 14
FROM openjdk:14

# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Biên dịch ứng dụng của bạn (ví dụ: sử dụng Maven)
RUN chmod +x mvnw
RUN ./mvnw -X clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "target/demo.jar"]
