# Sử dụng một hình ảnh JDK để xây dựng ứng dụng Java của bạn
FROM openjdk:14

# Cài đặt Maven với quyền cao hơn
RUN apt-get update && \
    apt-get install -y && \
    apt-get install -y maven


# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Biên dịch ứng dụng của bạn (ví dụ: sử dụng Maven)
RUN mvn clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "target/demo.jar"]
