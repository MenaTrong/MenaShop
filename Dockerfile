# Sử dụng một hình ảnh JDK để xây dựng ứng dụng Java của bạn
FROM openjdk:14

# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

RUN apt-get update && \
    apt-get install -y maven

# Biên dịch ứng dụng của bạn (ví dụ: sử dụng Maven)
RUN mvn -f demo/pom.xml clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "target/demo.jar"]
