# Sử dụng một hình ảnh JDK để xây dựng ứng dụng Java của bạn
FROM openjdk:14

ç# Tải xuống và cài đặt Apache Maven
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL -o /tmp/apache-maven.tar.gz "https://apache.mirror.digitalpacific.com.au/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz" && \
    tar xzf /tmp/apache-maven.tar.gz -C /opt && \
    rm -f /tmp/apache-maven.tar.gz && \
    ln -s /opt/apache-maven-3.8.4 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn


# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Biên dịch ứng dụng của bạn (ví dụ: sử dụng Maven)
RUN mvn clean package

# Chạy ứng dụng khi container được khởi động
CMD ["java", "-jar", "target/demo.jar"]
