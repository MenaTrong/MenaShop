# Sử dụng một hình ảnh JDK để xây dựng ứng dụng Java của bạn
FROM openjdk:14

# Sao chép tất cả các tệp và thư mục cần thiết từ máy cục bộ vào container
COPY . /usr/src/demo

# Thiết lập thư mục làm việc
WORKDIR /usr/src/demo

# Tải xuống và cài đặt Apache Maven
RUN wget -q "https://apache.mirror.digitalpacific.com.au/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz" -O /tmp/apache-maven.tar.gz && \
    tar xzf /tmp/apache-maven.tar.gz -C /opt && \
    rm -f /tmp/apache-maven.tar.gz && \
    ln -s /opt/apache-maven-3.8.4 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

# Biên dịch ứng dụng của bạn (sử dụng Maven)
RUN mv
